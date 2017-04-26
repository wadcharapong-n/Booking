package com.booking.dao;

import org.springframework.stereotype.Repository;

import com.booking.Object.ObjActor;
import com.booking.Object.ObjUser;

@Repository("securityDAO")
public class SecurityDAOImpl implements SecurityDAO{
	
	public Object getUser(ObjUser userForm){
		ObjActor actor = null;
		if("menkung".equals(userForm.getUserName()) && "password".equals(userForm.getPassword())){
			
			actor.setId("1");
			actor.setName("Menkung");
			actor.setUserName("Menkung");
			actor.setAboutYou("Admin");
			actor.setCountry("Thailand");
			actor.setGender("Male");
			actor.setRole(new Byte("1")); // admin
		}
		return actor;
	}
}
