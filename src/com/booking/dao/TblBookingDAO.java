package com.booking.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.booking.ObjectDAO.TblBooking;

public interface TblBookingDAO {

	public Object createBooking(TblBooking tblBooking) throws SQLException;
	public ArrayList<TblBooking> getAllBooking() throws SQLException;
}
