package com.springboot.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.springboot.model.User;
import com.springboot.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private IUserService userService;
	
	@ResponseBody
	@RequestMapping("showUser")
	public User toIndex(HttpServletRequest request, Model model){
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		//model.addAttribute("user", user);
		return user;
	}
}