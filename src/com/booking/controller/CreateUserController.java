package com.booking.controller;

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

import com.booking.Object.ObjCreateUser;
import com.booking.Object.ObjGenericResult;
import com.booking.Object.ObjUser;
import com.booking.constant.BaseConstant;
import com.booking.form.CreateUserForm;
import com.booking.form.LoginForm;
import com.booking.services.SecurityServiceFacade;

@Controller("createUserController")
public class CreateUserController extends BaseController{
	@Autowired
	SecurityServiceFacade  securityServiceFacade;
	
	private static final Logger logger = Logger.getLogger(CreateUserController.class);
	
	@RequestMapping(value="initRegister", method = RequestMethod.GET)
    public ModelAndView onInitMain(HttpServletRequest request) {

        return new ModelAndView("login/registerUser", "createForm", new CreateUserForm());
    }
	
	@RequestMapping(value="createUserAdmin", method = RequestMethod.POST)
    public ModelAndView loginSubmit(@ModelAttribute("createForm") CreateUserForm createForm,BindingResult result, Model model,HttpServletRequest request ) {
    	
    	ObjGenericResult objGenericResult = new ObjGenericResult();
    	
    	//prepare criteria
    	ObjCreateUser obj = new ObjCreateUser();
    	obj.setUsername(createForm.getUsername());
    	obj.setPassword(encypt(createForm.getPassword()));
    	obj.setGender(createForm.getGender());
    	obj.setFullname(createForm.getFullname());
    	obj.setEmail(createForm.getEmail());
    	
    	//call Service
        objGenericResult = securityServiceFacade.createUser(obj);
    	if(BaseConstant.STATUS_FAIL.equals(objGenericResult.getObjMessage().getResultStatus())){
    		return errorPage(objGenericResult.getObjMessage().getResultMessage(), "main.do");
    	}
    	
    	System.out.println(createForm.getEmail());
        return new ModelAndView("mainContent/main", "user", objGenericResult);
       
    }
	

}
