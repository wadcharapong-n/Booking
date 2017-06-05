package com.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.booking.Object.ObjUser;
import com.booking.ObjectDAO.TblUser;

@Repository("securityDAO")
public class SecurityDAOImpl extends BaseDAO implements SecurityDAO {
	private final static String getUserSQL = "select * from tbluser where username = ?";
	
	public TblUser getUser(ObjUser userForm){
		TblUser tblUser = null;
		Connection conn = null;
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
	
}
