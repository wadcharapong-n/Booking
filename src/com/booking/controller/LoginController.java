package com.booking.controller;

import java.util.ArrayList;
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

import com.booking.Object.ObjActor;
import com.booking.Object.ObjCreateUser;
import com.booking.Object.ObjGenericResult;
import com.booking.Object.ObjMenu;
import com.booking.Object.ObjUser;
import com.booking.constant.BaseConstant;
import com.booking.form.CreateUserForm;
import com.booking.form.LoginForm;
import com.booking.services.MenuServiceFacade;
import com.booking.services.SecurityServiceFacade;
import com.booking.services.UserServiceFacade;

@Controller("loginController")
public class LoginController extends BaseController {
	
	@Autowired
	SecurityServiceFacade  securityServiceFacade;
	
	@Autowired
	MenuServiceFacade  menuServiceFacade;
	
	@Autowired
	UserServiceFacade  userServiceFacade;
	
	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@RequestMapping(value="main", method = RequestMethod.GET)
    public ModelAndView onInitMain(HttpServletRequest request) {
		logger.info("Access from : " + request.getLocalAddr());
		logger.info("Access from : " + request.getRemoteAddr());
		System.out.println("session id "+request.getSession().getId());
		System.out.println("session Create "+new Date(request.getSession().getCreationTime()).toString());
		System.out.println("session LastAcces "+new Date(request.getSession().getLastAccessedTime()).toString());
		System.out.println("session time expire "+request.getSession().getMaxInactiveInterval());
		
		ArrayList<ObjMenu> objMenus = menuServiceFacade.getAllMenu();
		request.getSession().setAttribute("menu", objMenus);

        return new ModelAndView("mainContent/main", "userForm", new ObjUser());
    }
	
	@RequestMapping(value="initLogin", method = RequestMethod.GET)
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
    	
    	ObjActor actor = objGenericResult.getObjActor();
    	request.getSession().setAttribute("actor", actor);
    	
    	System.out.println(objGenericResult.getObjActor().getUserId());
        return new ModelAndView("mainContent/main", "user", objGenericResult);
       
    }
    
    @RequestMapping(value="initForgot", method = RequestMethod.GET)
    public ModelAndView onInitForgot() {
        
        return new ModelAndView("login/forgotStep1", "userForm", new LoginForm());
    }
    
    @RequestMapping(value="forgetStep1", method = RequestMethod.POST)
    public ModelAndView forgotSubmitUsername(@ModelAttribute("userForm") LoginForm userForm) {
    	ObjCreateUser objCreateUser = new ObjCreateUser();
    	//call Service
    	objCreateUser = userServiceFacade.getUserByUsername(userForm.getUserName());
    	if(objCreateUser == null){
    		return errorPage("not found username", "initForgot.do");
    	}
    	CreateUserForm createUserForm = new CreateUserForm();
    	createUserForm.setUserid(objCreateUser.getUserId());
    	createUserForm.setUsername(objCreateUser.getUsername());
    	
        return new ModelAndView("login/forgotStep2", "forgotForm", createUserForm);
    }
    
    @RequestMapping(value="forgetStep2", method = RequestMethod.POST)
    public ModelAndView forgotSubmitPassword(@ModelAttribute("userForm") CreateUserForm userForm) {
    	ObjCreateUser objCreateUser = new ObjCreateUser();
    	ObjGenericResult objGenericResult = new ObjGenericResult();
    	//prepare criteria
    	ObjCreateUser criteria = new  ObjCreateUser();
    	criteria.setUserId(userForm.getUserid());
    	criteria.setUsername(userForm.getUsername());
    	criteria.setPassword(encypt(userForm.getPassword()));
    	
    	
    	//call Service
    	objGenericResult = userServiceFacade.changePassword(criteria);
    	if(BaseConstant.STATUS_FAIL.equals(objGenericResult.getObjMessage().getResultStatus())){
    		return errorPage("error", "initForgot.do");
    	}
    	CreateUserForm createUserForm = new CreateUserForm();
    	createUserForm.setUserid(objCreateUser.getUserId());
    	createUserForm.setUsername(objCreateUser.getUsername());
    	
    	return new ModelAndView("mainContent/main", "userForm", new ObjUser());
    }
}
