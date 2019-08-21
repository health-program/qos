package com.paladin.qos.service.infectioncomplication;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.session.UserSession;

import com.paladin.qos.mapper.infectioncomplication.OperationComplicationMapper;
import com.paladin.qos.model.infectioncomplication.OperationComplication;
import com.paladin.qos.service.infectioncomplication.dto.OperationComplicationDTO;
import com.paladin.qos.service.infectioncomplication.dto.OperationComplicationQueryDTO;
import com.paladin.qos.service.infectioncomplication.vo.OperationComplicationVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;


@Service
public class OperationComplicationService extends ServiceSupport<OperationComplication> {

	@Autowired
	OperationComplicationMapper operationComplicationMapper;
       public PageResult<OperationComplicationVO> searchFindPage(OperationComplicationQueryDTO query){
	       Page<OperationComplicationVO> page = PageHelper.offsetPage(query.getOffset(), query.getLimit()); 
	       operationComplicationMapper.infectIndicationAll(query);
	       return new PageResult<>(page);
	   }
     
     
       @Transactional
	   public OperationComplicationVO queryById(String id) {
		// TODO Auto-generated method stub
		return operationComplicationMapper.queryById(id);
	   }


       @Transactional
		public int updates(OperationComplicationDTO dto) {
			// TODO Auto-generated method stub
    	   dto.setUpdateTime(new Date());
			return operationComplicationMapper.updates(dto);
		}

       @Transactional
		public int delete(String id) {
			// TODO Auto-generated method stub
			return operationComplicationMapper.deletes(id);
		}

	    @Transactional
		public Object insertInto(OperationComplicationVO vo) {
			// TODO Auto-generated method stub
	    	UserSession userSession = UserSession.getCurrentUserSession();
			String uid = userSession == null ? "" : userSession.getUserId();
			vo.setCreateUserId(uid);
	    	vo.setCreateTime(new Date());
			return operationComplicationMapper.insertInto(vo);
		}
	    
	    //判断半年后新增
	    @Transactional
		public Boolean canAdd() {
			OperationComplicationVO infectIndication = operationComplicationMapper.findRecentlyRecord();
  
		        if (null != infectIndication) {
		            Date recordDate = infectIndication.getCreateTime();
		            Calendar c = Calendar.getInstance();
		            c.setTime(recordDate);
		            c.add(Calendar.MONTH, 6);
		            Date currentTime = new Date();
		            if (c.getTime().after(currentTime)) {
		                return false;
		            }
		        }
		        return true;
		}

}

