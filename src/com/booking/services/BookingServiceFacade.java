package com.booking.services;

import java.util.ArrayList;

import com.booking.Object.ObjBooking;
import com.booking.Object.ObjGenericResult;

public interface BookingServiceFacade {

	public ObjGenericResult createBooking(ObjBooking objBooking); 
	public ArrayList<ObjBooking> getAllbooking();
}
