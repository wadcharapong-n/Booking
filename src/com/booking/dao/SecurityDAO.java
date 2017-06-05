package com.booking.dao;

import com.booking.Object.ObjUser;
import com.booking.ObjectDAO.TblUser;

public interface SecurityDAO {

	public TblUser getUser(ObjUser userForm);
	
}
