package com.booking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.booking.Object.ObjMessage;
import com.booking.Object.User;


public class BaseController {
	
	
 

	
    public ModelAndView errorPage(String message,String linkButton) {
		ObjMessage objMessage = new ObjMessage();
		objMessage.setResultMessage(message);
		objMessage.setLinkButton(linkButton);
        return new ModelAndView("errorPage", "objMessage", objMessage);
    }
}
