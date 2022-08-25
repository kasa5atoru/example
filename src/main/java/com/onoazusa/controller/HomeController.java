package com.onoazusa.controller;


import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onoazusa.model.User;
import com.onoazusa.service.UserService;

@Controller
public class HomeController {
	

	@Autowired(required=true)
	private UserService userService;
	
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String user(@Validated User user, Model model) {
		System.out.println("User Page Requested");
		model.addAttribute("userName", user.getUserName());
		
		User u = new User();
		u.setId(1);
		u.setUserName(user.getUserName());
		
		this.userService.addUser(u);
		
		return "user";
	}
		
}
