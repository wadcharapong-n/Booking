package com.booking.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.Object.ObjActor;
import com.booking.Object.ObjCreateUser;
import com.booking.Object.ObjGenericResult;
import com.booking.Object.ObjMessage;
import com.booking.ObjectDAO.TblUser;
import com.booking.constant.BaseConstant;
import com.booking.dao.SecurityDAO;
import com.booking.dao.TblUserDAO;

@Service("userService")
public class UserServiceImpl implements UserServiceFacade {
	
	@Autowired
	SecurityDAO securityDao;
	
	@Autowired 
	TblUserDAO tblUserDao;

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
			tblUser.setRoleid(createUser.getRole());
			//call dao
			tblUserDao.createUser(tblUser);
			
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
	
	public ObjGenericResult editUser(ObjCreateUser createUser){
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
			tblUser.setRoleid(createUser.getRole());
			//call dao
			tblUserDao.createUser(tblUser);
			
			objMessage.setResultMessage("Edit user Success!!!!");
			objMessage.setResultStatus(BaseConstant.STATUS_SUCCESS);

			result.setObjMessage(objMessage);
			
		}catch(Exception e){
			e.printStackTrace();
			objMessage.setResultStatus("Edit user Fail!!!!");
			objMessage.setResultStatus(BaseConstant.STATUS_FAIL);			
			result.setObjMessage(objMessage);
			
		}

		return result;
	}
	
	public ObjCreateUser getUserById(Integer userid){
		ObjCreateUser result = new ObjCreateUser();

		TblUser tbl = tblUserDao.getUserById(userid);
		result.setUserId(tbl.getUserId());
		result.setUsername(tbl.getUsername());
		result.setEmail(tbl.getEmail());
		result.setGender(tbl.getGender());
		result.setRole(tbl.getRoleid());
		result.setFullname(tbl.getFullname());
		return result;
	}
	
	public ArrayList<ObjCreateUser> getAllUser(){
		ArrayList<ObjCreateUser> result = new ArrayList<ObjCreateUser>();
		ArrayList<TblUser> tblUsers = new ArrayList<TblUser>();
		tblUsers = tblUserDao.getAllUser();
		
		if(tblUsers != null){
			for(TblUser tbl : tblUsers){
				ObjCreateUser obj = new ObjCreateUser();
				obj.setUserId(tbl.getUserId());
				obj.setUsername(tbl.getUsername());
				obj.setEmail(tbl.getEmail());
				obj.setGender(tbl.getGender());
				obj.setRole(tbl.getRoleid());
				obj.setFullname(tbl.getFullname());
				result.add(obj);
			}
			
		}
		
		
		return result;
	}
	
	public ObjGenericResult updateUser(ObjCreateUser createUser,ObjActor actor){
		
		ObjGenericResult result = new ObjGenericResult();
		ObjMessage objMessage = new ObjMessage();
		try{
			//prepare criteria
			TblUser tblUser = new TblUser();
			tblUser.setUserId(createUser.getUserId());
			tblUser.setUsername(createUser.getUsername());
			tblUser.setGender(createUser.getGender());
			tblUser.setFullname(createUser.getFullname());
			tblUser.setEmail(createUser.getEmail());
			tblUser.setRoleid(createUser.getRole());
			//call dao
			tblUserDao.updateUser(tblUser, actor);
			
			objMessage.setResultMessage("update user Success!!!!");
			objMessage.setResultStatus(BaseConstant.STATUS_SUCCESS);

			result.setObjMessage(objMessage);
			
		}catch(Exception e){
			e.printStackTrace();
			objMessage.setResultStatus("update user Fail!!!!");
			objMessage.setResultStatus(BaseConstant.STATUS_FAIL);			
			result.setObjMessage(objMessage);
			
		}

		return result;
	}
	
	public ObjCreateUser getUserByUsername(String username){
		ObjCreateUser result = null;

		TblUser tbl = tblUserDao.getUserByUsername(username);
		if(tbl != null){
			result = new ObjCreateUser();
			result.setUserId(tbl.getUserId());
			result.setUsername(tbl.getUsername());
			result.setEmail(tbl.getEmail());
			result.setGender(tbl.getGender());
			result.setRole(tbl.getRoleid());
			result.setFullname(tbl.getFullname());
		}
		return result;
	}
	
	public ObjGenericResult changePassword(ObjCreateUser createUser){
		ObjGenericResult result = new ObjGenericResult();
		ObjMessage objMessage = new ObjMessage();
		try{
			//prepare criteria
			TblUser tblUser = new TblUser();
			tblUser.setUserId(createUser.getUserId());
			tblUser.setUsername(createUser.getUsername());
			tblUser.setPassword(createUser.getPassword());		
			//call dao
			tblUserDao.updatePassword(tblUser);
			
			objMessage.setResultMessage("update user Success!!!!");
			objMessage.setResultStatus(BaseConstant.STATUS_SUCCESS);

			result.setObjMessage(objMessage);
			
		}catch(Exception e){
			e.printStackTrace();
			objMessage.setResultStatus("update user Fail!!!!");
			objMessage.setResultStatus(BaseConstant.STATUS_FAIL);			
			result.setObjMessage(objMessage);
			
		}

		return result;
		
	}
}
