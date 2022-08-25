package com.onoazusa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onoazusa.model.User;
import com.onoazusa.service.UserService;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Controller
public class UserController {
	
	private UserService UserService;
	
	@Autowired(required=true)
	@Qualifier(value="UserService")
	public void setUserService(UserService ps){
		this.UserService = ps;
	}
	
	@RequestMapping(value = "/Users", method = RequestMethod.GET)
	public String listUsers(Model model) {
		model.addAttribute("User", new User());
		model.addAttribute("listUsers", this.UserService.listUsers());
		return "User";
	}
	
	//For add and update User both
	@RequestMapping(value= "/User/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("User") User p){
		
		if(p.getId() == 0){
			//new User, add it
			this.UserService.addUser(p);
		}else{
			//existing User, call update
			this.UserService.updateUser(p);
		}
		
		return "redirect:/Users";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id){
		
        this.UserService.removeUser(id);
        return "redirect:/Users";
    }
 
    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
        model.addAttribute("User", this.UserService.getUserById(id));
        model.addAttribute("listUsers", this.UserService.listUsers());
        return "User";
    }
	
}
