package com.booking.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.booking.Object.ObjUser;

import facebook4j.Account;
import facebook4j.Comment;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.PagableList;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.ResponseList;

@Controller("ServiceController")
public class ServiceController {

	@RequestMapping(value="service", method = RequestMethod.GET)
    public ModelAndView onInitService(HttpServletRequest request ,HttpServletResponse response) {
		String appId = "102392716992720";
		String appSecret = "ce3f9f0362bbe5ab01dfc8ee565e4372";		
		Facebook facebook = new FacebookFactory().getInstance();
		facebook.setOAuthAppId(appId, appSecret);
		facebook.setOAuthPermissions("email,user_about_me,user_birthday,user_friends,publish_actions");
        request.getSession().setAttribute("facebook", facebook);
        StringBuffer callbackURL = request.getRequestURL();
        int index = callbackURL.lastIndexOf("/");
        callbackURL.replace(index, callbackURL.length(), "").append("/callback.do");
//        response.sendRedirect(facebook.getOAuthAuthorizationURL(callbackURL.toString()));        
        String url = facebook.getOAuthAuthorizationURL(callbackURL.toString());
        
        
        return new ModelAndView("redirect:" + url);
    }
	
	@RequestMapping(value="callback", method = RequestMethod.GET)
    public ModelAndView callBack(HttpServletRequest request ,HttpServletResponse response) throws ServletException {
		Facebook facebook = (Facebook) request.getSession().getAttribute("facebook");
        String oauthCode = request.getParameter("code");
        try {
            facebook.getOAuthAccessToken(oauthCode);
            ResponseList<Post> feeds = facebook.getFeed("10209474152230776",
                    new Reading().limit(5));

                // For all 25 feeds...
                for (int i = 0; i < feeds.size(); i++) {
                    // Get post.
                    Post post = feeds.get(i);
                    // Get (string) message.
                    String message = post.getMessage();
                                    // Print out the message.
                    System.out.println(message);

                    // Get more stuff...
                    PagableList<Comment> comments = post.getComments();
                    String date = post.getCreatedTime().toString();
//                    String name = post.getFrom().getName();
                    String id = post.getId();
                }           
            
            //facebook.getOAuthAccessToken(pageAccessToken);
            System.out.println(facebook.getId());
            System.out.println(facebook.getName());
//            facebook.postStatusMessage("Hello World from Developer");
        } catch (FacebookException e) {
            throw new ServletException(e);
        }		
		
		return new ModelAndView("mainContent/main", "userForm", new ObjUser());
	}
	
}
