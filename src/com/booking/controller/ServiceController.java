package com.booking.controller;

import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.booking.Object.ObjBooking;
import com.booking.Object.ObjGenericResult;
import com.booking.Object.ObjMenu;
import com.booking.Object.ObjUser;
import com.booking.constant.BaseConstant;
import com.booking.form.BookingForm;
import com.booking.form.ServiceForm;
import com.booking.services.BookingServiceFacade;
import com.booking.services.MenuServiceFacade;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Friendlist;
import facebook4j.ResponseList;

@Controller("ServiceController")
public class ServiceController extends BaseController{	
	
	@Autowired
	MenuServiceFacade  menuServiceFacade;
	
	@Autowired
	BookingServiceFacade bookingServiceFacade;
	
	@RequestMapping(value="serviceInit", method = RequestMethod.GET)
    public ModelAndView onInit(HttpServletRequest request ,HttpServletResponse response) {
		ServiceForm serviceForm = new ServiceForm(); 
		Facebook facebook = new FacebookFactory().getInstance();
		request.getSession().removeAttribute("facebook");
		facebook.setOAuthAppId(BaseConstant.FACEBOOK_APP_ID, BaseConstant.FACEBOOK_APP_SECRET);
		facebook.setOAuthPermissions("email,user_about_me,user_birthday,user_friends,publish_actions");
		request.getSession().setAttribute("facebook", facebook);
		if(request.getSession().getAttribute("menu") == null){
			ArrayList<ObjMenu> objMenus = menuServiceFacade.getAllMenu();
			request.getSession().setAttribute("menu", objMenus);
		}
		ArrayList<ObjBooking> objBookings = new ArrayList<ObjBooking>();
		objBookings = bookingServiceFacade.getAllbooking();
		request.setAttribute("objBookings", objBookings);
		
		return new ModelAndView("booking/service","serviceForm",serviceForm);
	}
	
	@RequestMapping(value="service", method = RequestMethod.POST)
    public ModelAndView onInitService(HttpServletRequest request ,HttpServletResponse response,@ModelAttribute("serviceForm") ServiceForm serviceForm) {
		
//		facebook.setOAuthAppId(appId, appSecret);
//		facebook.setOAuthPermissions("email,user_about_me,user_birthday,user_friends,publish_actions");
		Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
      
        StringBuffer callbackURL = request.getRequestURL();
        int index = callbackURL.lastIndexOf("/");
        callbackURL.replace(index, callbackURL.length(), "").append("/callback.do");
//        response.sendRedirect(facebook.getOAuthAuthorizationURL(callbackURL.toString()));        
        String url = facebook.getOAuthAuthorizationURL(callbackURL.toString());
        request.getSession().removeAttribute("textPost");
        request.getSession().setAttribute("textPost", serviceForm.getTextPost());
        
        return new ModelAndView("redirect:" + url);
    }
	
	@RequestMapping(value="fetchFacebook", method = RequestMethod.GET)
    public ModelAndView fetchFacebook(HttpServletRequest request ,HttpServletResponse response,@ModelAttribute("serviceForm") ServiceForm serviceForm) {
		Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
		
        request.getSession().setAttribute("facebook", facebook);
        StringBuffer callbackURL = request.getRequestURL();
        int index = callbackURL.lastIndexOf("/");
        callbackURL.replace(index, callbackURL.length(), "").append("/callback.do");
//        response.sendRedirect(facebook.getOAuthAuthorizationURL(callbackURL.toString()));        
        String url = facebook.getOAuthAuthorizationURL(callbackURL.toString());
        request.getSession().removeAttribute("textPost");
        request.getSession().setAttribute("textPost", serviceForm.getTextPost());
        
        return new ModelAndView("redirect:" + url);
    }
	
	@RequestMapping(value="callback", method = RequestMethod.GET)
    public ModelAndView callBack(HttpServletRequest request ,HttpServletResponse response) throws ServletException {
		Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        String oauthCode = request.getParameter("code");
        try {
            facebook.getOAuthAccessToken(oauthCode);
            ResponseList<Friendlist>rs = facebook.getFriendlists();
//            ResponseList<Friend> results = facebook.getFriends();
//            ResponseList<Post> feed = facebook.getHome();
//
//                // For all 25 feeds...
//                for (int i = 0; i < feeds.size(); i++) {
//                    // Get post.
//                    Post post = feeds.get(i);
//                    // Get (string) message.
//                    String message = post.getMessage();
//                                    // Print out the message.
//                    System.out.println(message);
//
//                    // Get more stuff...
//                    PagableList<Comment> comments = post.getComments();
//                    String date = post.getCreatedTime().toString();
////                    String name = post.getFrom().getName();
//                    String id = post.getId();
//                }           
            
            //facebook.getOAuthAccessToken(pageAccessToken);
            System.out.println(facebook.getId());
            System.out.println(facebook.getName());
//            String textPost = request.getSession().getAttribute("textPost").toString();
//            facebook.postStatusMessage(textPost);
//            request.getSession().removeAttribute("textPost");
            
        } catch (FacebookException e) {
            throw new ServletException(e);
        }		
		
		return new ModelAndView("mainContent/main", "userForm", new ObjUser());
	}
	
	@RequestMapping(value="bookingInit", method = RequestMethod.GET)
    public ModelAndView onBookingInit(HttpServletRequest request ,HttpServletResponse response) {
		
		
		return new ModelAndView("booking/bookingMgnt","bookingForm",new BookingForm());
	}
	
	@RequestMapping(value="bookingSubmit", method = RequestMethod.POST)
    public ModelAndView onBookingSubmit(HttpServletRequest request ,HttpServletResponse response,@ModelAttribute("bookingForm") BookingForm bookingForm) {
		ObjGenericResult objGenericResult = new ObjGenericResult();
		
		//prepaer parameter
		ObjBooking objBooking = new ObjBooking();
		objBooking.setTitle(bookingForm.getTitle());
		objBooking.setName(bookingForm.getName());
		objBooking.setPhone(bookingForm.getPhone());
		java.sql.Date startDate = new java.sql.Date(bookingForm.getStartDate().getTime());
		objBooking.setStartDate(startDate.toString());
		java.sql.Date endDate = new java.sql.Date(bookingForm.getEndDate().getTime());
		objBooking.setEndDate(endDate.toString());
		
		//call service 
		objGenericResult = bookingServiceFacade.createBooking(objBooking);
		
		if(BaseConstant.STATUS_FAIL.equals(objGenericResult.getObjMessage().getResultStatus())){
    		return errorPage(objGenericResult.getObjMessage().getResultMessage(), "main.do");
    	}
		
		return new ModelAndView("booking/bookingMgnt","bookingForm",new BookingForm());
	}
	
}
