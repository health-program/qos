package com.paladin.qos.service.exhibition;

import com.paladin.common.util.DateFormatUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

/**
 * <获取数据公用方法>
 *
 * @author Huangguochen
 * @create 2019/8/29 15:38
 */
public  abstract class BaseExhibitionDataAcquire {
    protected  List <Date> getSearchTimeList(Date startTime, Date endTime, boolean isBtimeDefault) {
        List <Date> list = new ArrayList<>(12);
        LocalDate sTime = dateToLocalDate(startTime,true, isBtimeDefault);
        LocalDate eTime = dateToLocalDate(endTime,false, false);
        long distance = ChronoUnit.MONTHS.between(sTime, eTime);
        if ( distance < 1) {
            if ( endTime == null) {
                distance = 12;
                Stream.iterate(sTime, s -> s.plusMonths(1)).limit(distance).forEach(f -> list.add(localDateToDate(f)));
            } else {
                list.add(endTime);
                return  list;
            }
        } else if (distance > 12 && startTime == null) {
            distance = 12;
            Stream.iterate(eTime, s -> s.minusMonths(1)).limit(distance).forEach(f -> list.add(localDateToDate(f)));
        }else if (distance > 12 && endTime == null) {
            distance = 12;
            Stream.iterate(sTime, s -> s.plusMonths(1)).limit(distance).forEach(f -> list.add(localDateToDate(f)));
        }else{
            Stream.iterate(sTime, s -> s.plusMonths(1)).limit(distance).forEach(f -> list.add(localDateToDate(f)));
        }
        return  list;
    }

    protected LocalDate dateToLocalDate(Date d, boolean isStart, boolean isDefault) {
        if (isStart && d == null && isDefault) {
            return   LocalDate.of(2018, Month.JANUARY,1);
        }
        Date date = d == null ? new Date() : d;
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
        if (isStart && d == null) {
            return   localDateTime.toLocalDate().minusYears(1);
        }else {
            return  localDateTime.toLocalDate();
        }
    }

    protected Date localDateToDate(LocalDate l) {
        LocalDate localDate = l == null ? LocalDate.now() : l;
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return  Date.from(instant);
    }

    protected Date checkStartTime(Date date) {
        SimpleDateFormat format = DateFormatUtil.getThreadSafeFormat("yyyy-MM-dd");
        try {
            return  date == null ? format.parse("2018-01-01") : date;
        } catch (ParseException e) {
            throw new RuntimeException("生成开始时间出错");
        }
    }


/*    public List<DataDemonstrationVO> getDataDemonstrationByMonth(Date startTime, Date endTime, String suffixsql, boolean havePreSql, boolean isBtimeDefault) {
        SimpleDateFormat format = DateFormatUtil.getThreadSafeFormat("yyyy-MM");
        List<Date> dateList = getSearchTimeList(startTime,endTime,isBtimeDefault);
        List<DataDemonstrationVO> list = new ArrayList<>(12);
        DataDemonstrationVO dataDemonstrationVO;
        for (Date date : dateList) {
            long total = dataCollectionFromRemoteServlet.searchRemoteInfo(havePreSql, suffixsql, new java.sql.Date(date.getTime()));
            dataDemonstrationVO = new DataDemonstrationVO();
            dataDemonstrationVO.setSearchTime(format.format(date));
            dataDemonstrationVO.setTotal(total);
            list.add(dataDemonstrationVO);
        }
        return list;
    }*/

}
