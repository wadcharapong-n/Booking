package com.booking.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.booking.Object.ObjActor;
import com.booking.ObjectDAO.TblUser;

public interface TblUserDAO {

	public Object createUser(TblUser createUser) throws SQLException;
	public Object updateUser(TblUser updateUser,ObjActor actor) throws SQLException;
	public TblUser getUserById(Integer userId);
	public ArrayList<TblUser> getAllUser();
	public int getCountAllUser();
	public TblUser getUserByUsername(String username);
	public void updatePassword(TblUser updateUser) throws SQLException;
}
