package com.booking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.Object.ObjActor;
import com.booking.Object.ObjCreateUser;
import com.booking.Object.ObjGenericResult;
import com.booking.Object.ObjMessage;
import com.booking.Object.ObjUser;
import com.booking.ObjectDAO.TblUser;
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
			//call dao get user
			TblUser daoResult = securityDao.getUser(userForm);
			
			//validate user login			
			if(daoResult != null){		
				//check password
				if(!userForm.getPassword().equals(daoResult.getPassword())){
					throw new Exception("wrong password!!");
				}
				
				//set actor
				actor.setUserId(daoResult.getUserId());
				actor.setName(daoResult.getFullname());
				actor.setUserName(daoResult.getUsername());
				actor.setGender(daoResult.getGender());
				actor.setRole(new Byte("1")); // admin
				
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
			objMessage.setResultStatus(e.getMessage());
			objMessage.setResultStatus(BaseConstant.STATUS_FAIL);			
			result.setObjMessage(objMessage);
			
		}

		return result;
		
	}
	public ObjGenericResult createUser(ObjCreateUser createUser){
		ObjGenericResult result = new ObjGenericResult();
		ObjActor actor = new ObjActor();
		ObjMessage objMessage = new ObjMessage();
		try{
			//prepare criteria
			TblUser tblUser = new TblUser();
			tblUser.setUsername(createUser.getUsername());
			tblUser.setPassword(createUser.getPassword());
			tblUser.setGender(createUser.getGender());
			tblUser.setFullname(createUser.getFullname());
			tblUser.setEmail(createUser.getFullname());
			
			//call dao
			securityDao.createUser(tblUser);
			
			objMessage.setResultMessage("Create user Success!!!!");
			objMessage.setResultStatus(BaseConstant.STATUS_SUCCESS);

			result.setObjMessage(objMessage);
			
		}catch(Exception e){
			e.printStackTrace();
			objMessage.setResultStatus("Create user Fail!!!!");
			objMessage.setResultStatus(BaseConstant.STATUS_FAIL);			
			result.setObjMessage(objMessage);
			
		}

		return result;
	}
}
