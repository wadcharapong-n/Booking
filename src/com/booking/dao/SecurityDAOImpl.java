package com.booking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.booking.Object.ObjActor;
import com.booking.Object.ObjCreateUser;
import com.booking.Object.ObjUser;
import com.booking.ObjectDAO.TblUser;

@Repository("securityDAO")
public class SecurityDAOImpl extends BaseDAO implements SecurityDAO {
	private final static String getUserSQL = "select * from tbluser where username = ?";
	
	public TblUser getUser(ObjUser userForm){
		TblUser tblUser = null;
		Connection conn = null;
//		if("menkung".equals(userForm.getUserName()) && "password".equals(userForm.getPassword())){
//			actor = new ObjActor();
//			actor.setId("1");
//			actor.setName("Menkung");
//			actor.setUserName("Menkung");
//			actor.setAboutYou("Admin");
//			actor.setCountry("Thailand");
//			actor.setGender("Male");
//			actor.setRole(new Byte("1")); // admin
//		}
		//return actor;
		
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(getUserSQL);
			
			conn = BaseDAO.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, userForm.getUserName());
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
	private final String createUserSQL = "insert into tbluser (username, password, email,gender, fullname, createDate, createBy, updateDate, updateBy) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
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
			ps.setDate(6, getCurrentTime());
			if(createUser.getCreateBy() != null){
				ps.setInt(7, createUser.getCreateBy());
			}else{
				ps.setInt(7, 1);
			}
			ps.setDate(8, getCurrentTime());
			if(createUser.getCreateBy() != null){
				ps.setInt(9, createUser.getUpdateBy());
			}else{
				ps.setInt(9, 1);
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
}
