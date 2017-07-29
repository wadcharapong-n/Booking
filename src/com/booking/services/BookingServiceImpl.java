package com.booking.services;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.Object.ObjActor;
import com.booking.Object.ObjBooking;
import com.booking.Object.ObjGenericResult;
import com.booking.Object.ObjMessage;
import com.booking.ObjectDAO.TblBooking;
import com.booking.constant.BaseConstant;
import com.booking.dao.TblBookingDAO;

@Service("bookingService")
public class BookingServiceImpl implements BookingServiceFacade {

	@Autowired
	TblBookingDAO tblBookingDAO;
	
	public ObjGenericResult createBooking(ObjBooking objBooking){
		ObjGenericResult result = new ObjGenericResult();
		ObjActor actor = new ObjActor();
		ObjMessage objMessage = new ObjMessage();
		try{
			TblBooking tblBooking = new TblBooking();
			tblBooking.setTitle(objBooking.getTitle());
			tblBooking.setName(objBooking.getName());
			tblBooking.setPhone(objBooking.getPhone());
			tblBooking.setStartDate(Date.valueOf(objBooking.getStartDate()));
			tblBooking.setEndDate(Date.valueOf(objBooking.getEndDate()));
			
			//call DAO
			tblBookingDAO.createBooking(tblBooking);
			
			objMessage.setResultMessage("Booking Success!!!!");
			objMessage.setResultStatus(BaseConstant.STATUS_SUCCESS);

			result.setObjMessage(objMessage);
		}catch(Exception e){
			e.printStackTrace();
			objMessage.setResultStatus("Booking Fail!!!!");
			objMessage.setResultStatus(BaseConstant.STATUS_FAIL);			
			result.setObjMessage(objMessage);
			
		}

		return result;
	}
	
	
	public ArrayList<ObjBooking> getAllbooking(){
		ArrayList<ObjBooking> objBookings = new ArrayList<ObjBooking>();
		ArrayList<TblBooking> tblBookings = new ArrayList<TblBooking>();
		try{
			tblBookings =  tblBookingDAO.getAllBooking();
			for(TblBooking tbl : tblBookings){
				ObjBooking obj = new ObjBooking();
				obj.setId(tbl.getId());
				obj.setTitle(tbl.getTitle());
				obj.setName(tbl.getName());
				obj.setPhone(tbl.getPhone());
				obj.setStartDate(tbl.getStartDate().toString());
				obj.setEndDate(tbl.getEndDate().toString());
				objBookings.add(obj);
			}
	
		}catch(Exception e){
			e.printStackTrace();
//			objMessage.setResultStatus("Booking Fail!!!!");
//			objMessage.setResultStatus(BaseConstant.STATUS_FAIL);			
//			result.setObjMessage(objMessage);
			
		}
		return objBookings;
	}
}
