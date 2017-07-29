package com.booking.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.booking.Object.ObjActor;
import com.booking.ObjectDAO.TblBooking;
import com.booking.ObjectDAO.TblUser;

@Repository("tblBookingDAO")
public class TblBookingDAOImpl extends BaseDAO implements TblBookingDAO {

	private final String createBookingSQL = "insert into tblbooking ( title, name, phone, startDate, endDate, createDate, createBy, updateDate, updateBy) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	public Object createBooking(TblBooking tblBooking) throws SQLException{
		ObjActor actor = null;
		Connection conn = null;	
		PreparedStatement ps = null;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(createBookingSQL);
			conn = BaseDAO.getConnection();
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, tblBooking.getTitle());
			ps.setString(2, tblBooking.getName());
			ps.setString(3, tblBooking.getPhone());
			ps.setDate(4, tblBooking.getStartDate());
			ps.setDate(5, tblBooking.getEndDate());
			ps.setDate(6, getCurrentTime());
			if(tblBooking.getCreateBy() != null){
				ps.setInt(7, tblBooking.getCreateBy());
			}else{
				ps.setInt(7, 1);
			}
			ps.setDate(8, getCurrentTime());
			if(tblBooking.getUpdateBy() != null){
				ps.setInt(9, tblBooking.getUpdateBy());
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
	private final String getAllBookingSQL = "select * from tblbooking";
	public ArrayList<TblBooking> getAllBooking(){
		ArrayList<TblBooking> result = new ArrayList<TblBooking>();
		TblBooking tblBooking = null;
		Connection conn = null;	
		PreparedStatement ps = null;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(getAllBookingSQL);
			
			conn = BaseDAO.getConnection();
			ps = conn.prepareStatement(sql.toString());
//			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// get data from column
				tblBooking = new TblBooking();
				int column = 1;
				tblBooking.setId(rs.getInt(column++));
				tblBooking.setTitle(rs.getString(column++));
				tblBooking.setName(rs.getString(column++));
				tblBooking.setPhone(rs.getString(column++));
				tblBooking.setStartDate(rs.getDate(column++));
				tblBooking.setEndDate(rs.getDate(column++));				
				tblBooking.setCreateDate(rs.getDate(column++));
				tblBooking.setCreateBy(rs.getInt(column++));
				tblBooking.setUpdateDate(rs.getDate(column++));
				tblBooking.setUpdateBy(rs.getInt(column++));
				result.add(tblBooking);
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
}
