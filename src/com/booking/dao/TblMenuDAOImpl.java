package com.booking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.booking.ObjectDAO.TblMenu;

@Repository("tblMenuDAO")
public class TblMenuDAOImpl implements TblMenuDAO{

	private final static String getAllMenu = "select * from tblmenu ";
	public ArrayList<TblMenu> getAllMenu(){
		ArrayList<TblMenu> result = new ArrayList<TblMenu>();
		Connection conn = null;
		try{
			StringBuffer sql = new StringBuffer();
			sql.append(getAllMenu);
			
			conn = BaseDAO.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql.toString());			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// get data from column
				TblMenu tbl = new TblMenu();
				int column = 1;
				tbl.setMenuId(rs.getInt(column++));
				tbl.setMenuName(rs.getString(column++));
				tbl.setMenuAction(rs.getString(column++));
				tbl.setDescription(rs.getString(column++));
				tbl.setRoleId(rs.getInt(column++));
				result.add(tbl);
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
