package com.booking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.booking.Object.User;

@Controller
public class LoginController {
	@RequestMapping(value="init", method = RequestMethod.GET)
    public ModelAndView onInitLogin() {
        
        return new ModelAndView("login/userForm", "userForm", new User());
    }
 
    @RequestMapping(value="loginSubmit", method = RequestMethod.POST)
    public ModelAndView loginSubmit(@ModelAttribute("userForm") User userForm,BindingResult result, Model model) {
    	System.out.println("login Success");
        model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
        return new ModelAndView("login/userSuccess", "user", userForm);
       
    }
}
