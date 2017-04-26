package com.booking.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import com.booking.Object.ObjMessage;


public class BaseController {

    public ModelAndView errorPage(String message,String linkButton) {
		ObjMessage objMessage = new ObjMessage();
		objMessage.setResultMessage(message);
		objMessage.setLinkButton(linkButton);
        return new ModelAndView("errorPage", "objMessage", objMessage);
    }
    
    public void refreshSession(HttpSession session){
    	
    }
}
