package com.booking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.booking.Object.ObjUser;

@Controller("ServiceController")
public class ServiceController {

	@RequestMapping(value="service", method = RequestMethod.GET)
    public ModelAndView onInitService() {
        
        return new ModelAndView("mainContent/service", "userForm", new ObjUser());
    }
}
