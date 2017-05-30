package com.booking.dao;

import java.sql.SQLException;

import com.booking.Object.ObjUser;
import com.booking.ObjectDAO.TblUser;

public interface SecurityDAO {

	public TblUser getUser(ObjUser userForm);
	public Object createUser(TblUser createUser) throws SQLException;
}
