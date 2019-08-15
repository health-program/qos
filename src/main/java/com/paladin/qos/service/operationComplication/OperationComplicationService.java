package com.paladin.qos.service.operationComplication;

import com.paladin.qos.service.operationComplication.dto.OperationComplicationQueryDTO;
import com.paladin.qos.service.operationComplication.vo.OperationComplicationVO;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.paladin.framework.common.PageResult;
import com.paladin.framework.core.ServiceSupport;
import com.paladin.framework.core.session.UserSession;
import com.paladin.qos.mapper.operationComplication.OperationComplicationMapper;
import com.paladin.qos.model.operationComplication.OperationComplication;




@Service
public class OperationComplicationService  extends ServiceSupport<OperationComplication> {

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
		public int updates(OperationComplicationVO InfectIndicationDTO) {
			// TODO Auto-generated method stub
    	   InfectIndicationDTO.setUpdateTime(new Date());
			return operationComplicationMapper.updates(InfectIndicationDTO);
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

