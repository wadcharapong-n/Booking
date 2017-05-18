package com.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.booking.Object.ObjActor;
import com.booking.Object.ObjUser;

@Repository("securityDAO")
public class SecurityDAOImpl implements SecurityDAO{
	
	public Object getUser(ObjUser userForm){
		ObjActor actor = null;
		Connection conn = null;
		if("menkung".equals(userForm.getUserName()) && "password".equals(userForm.getPassword())){
			actor = new ObjActor();
			actor.setId("1");
			actor.setName("Menkung");
			actor.setUserName("Menkung");
			actor.setAboutYou("Admin");
			actor.setCountry("Thailand");
			actor.setGender("Male");
			actor.setRole(new Byte("1")); // admin
		}
		return actor;
		
//		try{
//			StringBuffer sql = new StringBuffer();
//			sql.append(" select * from table ");
//			sql.append(" where ");
//			
//			
//			conn = BaseDAO.getConnection();
//			PreparedStatement ps = conn.prepareStatement(sql.toString());
//			ResultSet rs = ps.executeQuery();
//			if (rs.next()) {
//				// get data from column
//			}
//			rs.close();
//			ps.close();
//			
//		}catch(Exception ex){
//			ex.printStackTrace();
//		}finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {}
//			}
//		}
//		
//		return actor;
		
	}
}
