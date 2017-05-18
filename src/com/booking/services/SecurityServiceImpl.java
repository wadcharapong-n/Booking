package com.booking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.Object.ObjActor;
import com.booking.Object.ObjGenericResult;
import com.booking.Object.ObjMessage;
import com.booking.Object.ObjUser;
import com.booking.constant.BaseConstant;
import com.booking.dao.SecurityDAO;

@Service("securityService")
public class SecurityServiceImpl implements SecurityServiceFacade{
	
	@Autowired
	SecurityDAO securityDao;

	public ObjGenericResult loginUser(ObjUser userForm){
		ObjGenericResult result = new ObjGenericResult();
		ObjActor actor = new ObjActor();
		ObjMessage objMessage = new ObjMessage();
		try{
			//call dao
			Object daoResult = securityDao.getUser(userForm);
			
			
			//validate user login			
			if(daoResult != null){				
				actor = (ObjActor) daoResult;
				
				objMessage.setResultMessage("Login Success!!!!");
				objMessage.setResultStatus(BaseConstant.STATUS_SUCCESS);
				
				result.setObjActor(actor);
				result.setObjMessage(objMessage);
				
	    	}else{
	    		objMessage.setResultMessage("Login Fail!!!!");
	    		objMessage.setResultStatus(BaseConstant.STATUS_FAIL);
	    		result.setObjMessage(objMessage);
	    	}
	    			
		}catch(Exception e){
			e.printStackTrace();
			objMessage.setResultStatus("Login Fail!!!!");
			objMessage.setResultStatus(BaseConstant.STATUS_FAIL);			
			result.setObjMessage(objMessage);
			
		}

		return result;
		
	}
}
