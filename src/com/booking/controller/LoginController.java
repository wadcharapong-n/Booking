package com.booking.controller;

import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

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
import com.booking.services.SecurityServiceFacade;

@Controller("loginController")
public class LoginController extends BaseController {
	
	@Autowired
	SecurityServiceFacade  securityServiceFacade;
	
	@RequestMapping(value="main", method = RequestMethod.GET)
    public ModelAndView onInitMain() {
        
        return new ModelAndView("mainContent/main", "userForm", new ObjUser());
    }
	
	@RequestMapping(value="init", method = RequestMethod.GET)
    public ModelAndView onInitLogin() {
        
        return new ModelAndView("login/userForm", "userForm", new ObjUser());
    }
	
    @RequestMapping(value="loginSubmit", method = RequestMethod.POST)
    public ModelAndView loginSubmit(@ModelAttribute("userForm") ObjUser userForm,BindingResult result, Model model ) {
    	
    	ObjGenericResult objGenericResult = new ObjGenericResult();
    	//call Service
        objGenericResult = securityServiceFacade.loginUser(userForm);
    	if(BaseConstant.STATUS_FAIL.equals(objGenericResult.getObjMessage().getResultStatus())){
    		return errorPage(objGenericResult.getObjMessage().getResultMessage(), "main.do");
    	}
        return new ModelAndView("mainContent/main", "user", objGenericResult);
       
    }
    
}
