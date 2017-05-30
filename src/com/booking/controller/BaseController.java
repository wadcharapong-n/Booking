package com.booking.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    
    public String encypt(String str){
		String result = new String();

        MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");		
	        md.update(str.getBytes());
	
	        byte byteData[] = md.digest();
	
	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }	
	        //System.out.println("Hex format : " + sb.toString());
	        result = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
}
