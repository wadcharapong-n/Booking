package com.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.booking.Object.ObjActor;
import com.booking.ObjectDAO.TblUser;

@Repository("tblUserDAO")
public class TblUserDAOImpl extends BaseDAO implements TblUserDAO {

	private final String createUserSQL = "insert into tbluser (username, password, email,gender, fullname,roleid, createDate, createBy, updateDate, updateBy) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	public Object createUser(TblUser createUser) throws SQLException{
		ObjActor actor = null;
		Connection conn = null;	
		PreparedStatement ps = null;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(createUserSQL);
			conn = BaseDAO.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, createUser.getUsername());
			ps.setString(2, createUser.getPassword());
			ps.setString(3, createUser.getEmail());
			ps.setString(4, createUser.getGender());
			ps.setString(5, createUser.getFullname());
			ps.setInt(6, createUser.getRoleid());
			ps.setDate(7, getCurrentTime());
			if(createUser.getCreateBy() != null){
				ps.setInt(8, createUser.getCreateBy());
			}else{
				ps.setInt(8, 1);
			}
			ps.setDate(9, getCurrentTime());
			if(createUser.getCreateBy() != null){
				ps.setInt(10, createUser.getUpdateBy());
			}else{
				ps.setInt(10, 1);
			}			
			ps.executeUpdate();
			ps.close();
			
		}finally {
			if (conn != null) {				
				conn.close();				
			}
			if (ps != null) {				
				ps.close();
			}
		}		
		return actor;
		
	}
	
	private final static String getUserByIdSQL = "select * from tbluser where userId = ?";
	
	public TblUser getUserById(Integer userId){
		TblUser tblUser = null;
		Connection conn = null;	
		PreparedStatement ps = null;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(getUserByIdSQL);
			
			conn = BaseDAO.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// get data from column
				tblUser = new TblUser();
				int column = 1;
				tblUser.setUserId(rs.getInt(column++));
				tblUser.setUsername(rs.getString(column++));
				tblUser.setPassword(rs.getString(column++));
				tblUser.setEmail(rs.getString(column++));
				tblUser.setGender(rs.getString(column++));
				tblUser.setFullname(rs.getString(column++));
				tblUser.setRoleid(rs.getInt(column++));
				tblUser.setCreateDate(rs.getDate(column++));
				tblUser.setCreateBy(rs.getInt(column++));
				tblUser.setUpdateDate(rs.getDate(column++));
				tblUser.setUpdateBy(rs.getInt(column++));
				
			}
			rs.close();
			ps.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return tblUser;
		
	}
	private final static String getAllUserSQL = "select * from tbluser ";
	public ArrayList<TblUser> getAllUser(){
		ArrayList<TblUser> result = new ArrayList<TblUser>();
		TblUser tblUser = null;
		Connection conn = null;	
		PreparedStatement ps = null;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(getAllUserSQL);
			
			conn = BaseDAO.getConnection();
			ps = conn.prepareStatement(sql.toString());
//			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// get data from column
				tblUser = new TblUser();
				int column = 1;
				tblUser.setUserId(rs.getInt(column++));
				tblUser.setUsername(rs.getString(column++));
				tblUser.setPassword(rs.getString(column++));
				tblUser.setEmail(rs.getString(column++));
				tblUser.setGender(rs.getString(column++));
				tblUser.setFullname(rs.getString(column++));
				tblUser.setRoleid(rs.getInt(column++));
				tblUser.setCreateDate(rs.getDate(column++));
				tblUser.setCreateBy(rs.getInt(column++));
				tblUser.setUpdateDate(rs.getDate(column++));
				tblUser.setUpdateBy(rs.getInt(column++));
				result.add(tblUser);
			}
			rs.close();
			ps.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return result;
		
	}
	
	private final String updateUserSQL = "update tbluser set email = ?,gender = ?, fullname = ?,roleid = ?,updateDate = now(), updateBy = ? where userid = ? ";
		
	public Object updateUser(TblUser updateUser,ObjActor actor) throws SQLException{
		Connection conn = null;	
		PreparedStatement ps = null;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(updateUserSQL);
			conn = BaseDAO.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, updateUser.getEmail());
			ps.setString(2, updateUser.getGender());
			ps.setString(3, updateUser.getFullname());
			ps.setInt(4, updateUser.getRoleid());
			//ps.setDate(5, getCurrentTime());
			ps.setInt(5, actor.getUserId());
			ps.setInt(6, updateUser.getUserId());
//			if(createUser.getCreateBy() != null){
//				ps.setInt(8, createUser.getCreateBy());
//			}else{
//				ps.setInt(8, 1);
//			}
//			ps.setDate(9, getCurrentTime());
//			if(createUser.getCreateBy() != null){
//				ps.setInt(10, createUser.getUpdateBy());
//			}else{
//				ps.setInt(10, 1);
//			}			
			ps.executeUpdate();
			ps.close();
			
		}finally {
			if (conn != null) {				
				conn.close();				
			}
			if (ps != null) {				
				ps.close();
			}
		}		
		return actor;
	}
	
	private final static String getUserByUsernameSQL = "select * from tbluser where username = ?";
	
	public TblUser getUserByUsername(String username){
		TblUser tblUser = null;
		Connection conn = null;	
		PreparedStatement ps = null;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(getUserByUsernameSQL);
			
			conn = BaseDAO.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				// get data from column
				tblUser = new TblUser();
				int column = 1;
				tblUser.setUserId(rs.getInt(column++));
				tblUser.setUsername(rs.getString(column++));
				tblUser.setPassword(rs.getString(column++));
				tblUser.setEmail(rs.getString(column++));
				tblUser.setGender(rs.getString(column++));
				tblUser.setFullname(rs.getString(column++));
				tblUser.setRoleid(rs.getInt(column++));
				tblUser.setCreateDate(rs.getDate(column++));
				tblUser.setCreateBy(rs.getInt(column++));
				tblUser.setUpdateDate(rs.getDate(column++));
				tblUser.setUpdateBy(rs.getInt(column++));
				
			}
			rs.close();
			ps.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		
		return tblUser;
		
	}
	
	private final String updatePasswordSQL = "update tbluser set password = ? ,updateDate = now(), updateBy = ? where userid = ? ";
	public void updatePassword(TblUser updateUser) throws SQLException{
		Connection conn = null;	
		PreparedStatement ps = null;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(updatePasswordSQL);
			conn = BaseDAO.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, updateUser.getPassword());
			ps.setInt(2, updateUser.getUserId());
			ps.setInt(3, updateUser.getUserId());
			
			ps.executeUpdate();
			ps.close();
			
		}finally {
			if (conn != null) {				
				conn.close();				
			}
			if (ps != null) {				
				ps.close();
			}
		}		
	}
	
}
