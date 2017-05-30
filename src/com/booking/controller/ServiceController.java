package com.booking.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.booking.Object.ObjUser;
import com.booking.form.LoginForm;
import com.booking.form.ServiceForm;

import facebook4j.Account;
import facebook4j.Comment;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Friend;
import facebook4j.Friendlist;
import facebook4j.PagableList;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.User;

@Controller("ServiceController")
public class ServiceController {

	String appId = "102392716992720";
	String appSecret = "ce3f9f0362bbe5ab01dfc8ee565e4372";		
	
	
	
	@RequestMapping(value="serviceInit", method = RequestMethod.GET)
    public ModelAndView onInit(HttpServletRequest request ,HttpServletResponse response) {
		ServiceForm serviceForm = new ServiceForm(); 
		Facebook facebook = new FacebookFactory().getInstance();
		request.getSession().removeAttribute("facebook");
		facebook.setOAuthAppId(appId, appSecret);
		facebook.setOAuthPermissions("email,user_about_me,user_birthday,user_friends,publish_actions");
		request.getSession().setAttribute("facebook", facebook);
		return new ModelAndView("mainContent/service","serviceForm",serviceForm);
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
	
}
