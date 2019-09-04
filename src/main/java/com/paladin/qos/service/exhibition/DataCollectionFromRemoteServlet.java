package com.paladin.qos.service.exhibition;

import com.alibaba.druid.pool.DruidDataSource;
import com.paladin.framework.core.exception.BusinessException;
import com.paladin.framework.spring.SpringContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

/**
 * <从 sql server 数据库获取信息>
 * @author Huangguochen
 * @create 2019/8/23 16:37
 */
@Component
public class DataCollectionFromRemoteServlet implements SpringContainer {
    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(DataCollectionFromRemoteServlet.class);

    @Value("${sqlserver.jdbc.url}")
    private String url;

    @Value("${sqlserver.jdbc.username}")
    private String username;

    @Value("${sqlserver.jdbc.password}")
    private String password;

    @Value("${sqlserver.jdbc.name}")
    private String name;

    private static DruidDataSource dataSource;

    @Override
    public boolean initialize() {
        initDataSource();
        return true;
    }

    private void  initDataSource() {
        LOGGER.info("<-----------------------初始化sqlserver数据源开始------------------------->");
        dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setPassword(password);
        dataSource.setUsername(username);
        dataSource.setName(name);
        dataSource.setMaxWait(10000);
        LOGGER.info("<-----------------------初始化换sqlserver数据源结束------------------------->");
    }

    /**获取数据库连接*/
    private static Connection getConn() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            if(conn == null) {
                throw new SQLException("无法获取到Connection，是否数据库已经启动或者配置错误！");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**释放连接资源*/
    public static void close(ResultSet rs) {
        close(null, null, rs);
    }

    /**释放连接资源*/
    public static void close(Connection conn) {
        close(conn, null, null);
    }

    /**释放连接资源*/
    public static void close(Connection conn, Statement st) {
        close(conn, st, null);
    }

    /**释放连接资源*/
    public static void close(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static class RS {
        private Connection conn = null;
        private Statement st = null;
        private ResultSet rs;

        public RS(Connection conn, Statement st, ResultSet rs) {
            this.conn = conn;
            this.st = st;
            this.rs = rs;
        }

        public ResultSet getRs() {
            return this.rs;
        }

        public void close() {
            DataCollectionFromRemoteServlet.close(conn, st, rs);
        }

    }

    /**执行查询语句方法*/
    private  static RS executeQuery(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DataCollectionFromRemoteServlet.getConn();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                if (params[i] != null) {
                    ps.setObject(i + 1, params[i]);
                }
            }
            ResultSet rs = ps.executeQuery();
            return new RS(conn, ps, rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**执行更新语句方法*/
    private   int executeUpdate(String sql, Object... params) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DataCollectionFromRemoteServlet.getConn();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(conn, ps);
        }
        return 0;
    }


    /**查询前置库数据*/
    public long searchRemoteInfo(boolean havePreSql, String suffixSql, Object... params) {
        String countPrefixSql = " SELECT  COUNT(1)  AS total FROM ";
        RS rs = null;
        if (havePreSql) {
            rs = executeQuery(suffixSql, params);
        }else {
            rs = executeQuery(countPrefixSql + suffixSql, params);
        }
        if (rs == null) {
            throw new BusinessException("获取sql server数据库对应数据失败");
        }
        ResultSet resultSet = rs.getRs();
        long count = 0;
        try {
            if (resultSet.next()) {
                count = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new BusinessException("获取sql server数据库对应数据出错",e.getCause());
        } finally {
            rs.close();
        }

        return count;
    }

}
