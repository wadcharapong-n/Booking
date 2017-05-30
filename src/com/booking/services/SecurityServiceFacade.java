package com.booking.services;

import com.booking.Object.ObjCreateUser;
import com.booking.Object.ObjGenericResult;
import com.booking.Object.ObjUser;

public interface SecurityServiceFacade {
	public ObjGenericResult loginUser(ObjUser userForm);
	public ObjGenericResult createUser(ObjCreateUser createUser);
}
