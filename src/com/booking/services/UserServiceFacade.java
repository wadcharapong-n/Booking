package com.booking.services;

import java.util.ArrayList;

import com.booking.Object.ObjActor;
import com.booking.Object.ObjCreateUser;
import com.booking.Object.ObjGenericResult;

public interface UserServiceFacade {

	public ObjGenericResult createUser(ObjCreateUser createUser);
	public ObjGenericResult updateUser(ObjCreateUser createUser,ObjActor actor);
	public ArrayList<ObjCreateUser> getAllUser();
	public ObjCreateUser getUserById(Integer userid);
	public ObjCreateUser getUserByUsername(String username);
	public ObjGenericResult changePassword(ObjCreateUser createUser);
	
}
