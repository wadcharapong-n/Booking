package com.booking.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.booking.Object.ObjGenericResult;
import com.booking.Object.ObjUser;
import com.booking.constant.BaseConstant;
import com.booking.form.LoginForm;
import com.booking.services.SecurityServiceFacade;

@Controller("loginController")
public class LoginController extends BaseController {
	
	@Autowired
	SecurityServiceFacade  securityServiceFacade;
	
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping(value="main", method = RequestMethod.GET)
    public ModelAndView onInitMain(HttpServletRequest request) {
		logger.info("Access from : " + request.getLocalAddr());
		logger.info("Access from : " + request.getRemoteAddr());
		System.out.println("session id "+request.getSession().getId());
		System.out.println("session Create "+new Date(request.getSession().getCreationTime()).toString());
		System.out.println("session LastAcces "+new Date(request.getSession().getLastAccessedTime()).toString());
		System.out.println("session time expire "+request.getSession().getMaxInactiveInterval());
        return new ModelAndView("mainContent/main", "userForm", new ObjUser());
    }
	
	@RequestMapping(value="init", method = RequestMethod.GET)
    public ModelAndView onInitLogin() {
        
        return new ModelAndView("login/userForm", "userForm", new LoginForm());
    }
    @RequestMapping(value="loginSubmit", method = RequestMethod.POST)
    public ModelAndView loginSubmit(@ModelAttribute("userForm") LoginForm userForm,BindingResult result, Model model,HttpServletRequest request ) {
    	
    	ObjGenericResult objGenericResult = new ObjGenericResult();
    	
    	//prepare criteria
    	ObjUser objUser = new ObjUser();
    	objUser.setUserName(userForm.getUserName());
    	objUser.setPassword(encypt(userForm.getPassword()));
    	
    	//call Service
        objGenericResult = securityServiceFacade.loginUser(objUser);
    	if(BaseConstant.STATUS_FAIL.equals(objGenericResult.getObjMessage().getResultStatus())){
    		return errorPage(objGenericResult.getObjMessage().getResultMessage(), "main.do");
    	}
    	
    	System.out.println(objGenericResult.getObjActor().getUserId());
        return new ModelAndView("mainContent/main", "user", objGenericResult);
       
    }
    
    
}
