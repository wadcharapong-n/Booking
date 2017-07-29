package com.booking.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.booking.Object.ObjActor;
import com.booking.Object.ObjCreateUser;
import com.booking.Object.ObjGenericResult;
import com.booking.Object.ObjMenu;
import com.booking.constant.BaseConstant;
import com.booking.form.CreateUserForm;
import com.booking.services.MenuServiceFacade;
import com.booking.services.SecurityServiceFacade;
import com.booking.services.UserServiceFacade;

@Controller("createUserController")
public class CreateUserController extends BaseController{
	@Autowired
	SecurityServiceFacade  securityServiceFacade;
	
	@Autowired
	UserServiceFacade userServiceFacade;
	
	@Autowired
	MenuServiceFacade  menuServiceFacade;
	
	private static final Logger logger = Logger.getLogger(CreateUserController.class);
	
	@RequestMapping(value="initRegister", method = RequestMethod.GET)
    public ModelAndView onInitMain(HttpServletRequest request) {
		
		if(request.getSession().getAttribute("menu") == null){
			ArrayList<ObjMenu> objMenus = menuServiceFacade.getAllMenu();
			request.getSession().setAttribute("menu", objMenus);
		}
        return new ModelAndView("login/registerUser", "createForm", new CreateUserForm());
    }
	
	@RequestMapping(value="createUserAdmin", method = RequestMethod.POST)
    public ModelAndView createSubmit(@ModelAttribute("createForm") CreateUserForm createForm,BindingResult result, Model model,HttpServletRequest request ) {
    	
    	ObjGenericResult objGenericResult = new ObjGenericResult();
    	
    	//prepare criteria
    	ObjCreateUser obj = new ObjCreateUser();
    	obj.setUsername(createForm.getUsername());
    	obj.setPassword(encypt(createForm.getPassword()));
    	obj.setGender(createForm.getGender());
    	obj.setFullname(createForm.getFullname());
    	obj.setEmail(createForm.getEmail());
    	obj.setRole(createForm.getRole());
    	
    	//call Service
        objGenericResult = userServiceFacade.createUser(obj);
    	if(BaseConstant.STATUS_FAIL.equals(objGenericResult.getObjMessage().getResultStatus())){
    		return errorPage(objGenericResult.getObjMessage().getResultMessage(), "main.do");
    	}
    	
    	System.out.println(createForm.getEmail());
        return new ModelAndView("mainContent/main", "user", objGenericResult);
       
    }
	
	@RequestMapping(value="initListUser", method = RequestMethod.GET)
    public ModelAndView onInitListUser(HttpServletRequest request) {
		ArrayList<ObjCreateUser> objCreateUser = new ArrayList<ObjCreateUser>();
		
		//call Service
		objCreateUser = userServiceFacade.getAllUser();
		request.setAttribute("CountUser", objCreateUser.get(0).getCountUser());
        return new ModelAndView("login/listUser", "listUser", objCreateUser);
    }
	
	@RequestMapping(value="initMgntUser", method = RequestMethod.GET)
    public ModelAndView onInitMgntUser(HttpServletRequest request,@RequestParam() int userid) {
		CreateUserForm mghtUser = new CreateUserForm();
		
		//prepare parameter 
		//Integer userid = (Integer) request.getAttribute("userid");
		
		//call Service
		ObjCreateUser obj = userServiceFacade.getUserById(userid);
		mghtUser.setUsername(obj.getUsername());
		//mghtUser.setPassword(encypt(obj.getPassword()));
		mghtUser.setGender(obj.getGender());
		mghtUser.setFullname(obj.getFullname());
		mghtUser.setEmail(obj.getEmail());
		mghtUser.setRole(obj.getRole());
		mghtUser.setUserid(obj.getUserId());
			
        return new ModelAndView("login/mghtUser", "mghtUser", mghtUser);
    }
	
	@RequestMapping(value="editUserAdmin", method = RequestMethod.POST)
    public ModelAndView editSubmit(@ModelAttribute("mghtUser") CreateUserForm updateForm,BindingResult result, Model model,HttpServletRequest request ) {
    	
    	ObjGenericResult objGenericResult = new ObjGenericResult();
    	ObjActor actor =	(ObjActor) request.getSession().getAttribute("actor");
    	
    	//prepare criteria
    	ObjCreateUser obj = new ObjCreateUser();
    	obj.setUsername(updateForm.getUsername());
    	//obj.setPassword(encypt(updateForm.getPassword()));
    	obj.setGender(updateForm.getGender());
    	obj.setFullname(updateForm.getFullname());
    	obj.setEmail(updateForm.getEmail());
    	obj.setRole(updateForm.getRole());
    	obj.setUserId(updateForm.getUserid());
    	
    	
    	//call Service
        objGenericResult = userServiceFacade.updateUser(obj, actor);
    	if(BaseConstant.STATUS_FAIL.equals(objGenericResult.getObjMessage().getResultStatus())){
    		return errorPage(objGenericResult.getObjMessage().getResultMessage(), "initMgntUser.do?userid="+updateForm.getUserid());
    	}

    	//call Service
    	ArrayList<ObjCreateUser> objCreateUser = new ArrayList<ObjCreateUser>();
		objCreateUser = userServiceFacade.getAllUser();
			
        return new ModelAndView("login/listUser", "listUser", objCreateUser);
       
    }
	

}
