package project.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.demo.beans.UserBean;
import project.demo.service.UserService;
import project.demo.validator.UserValidator;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/join")
	public String join(@ModelAttribute("user") UserBean userBean) {
		return "user/join";
	}

	@PostMapping("/join")
	public String joinUser(@Valid  @ModelAttribute("user") UserBean userBean, BindingResult result){
		//번외
		//@ModelAttribute Item item -> model.addAttribute("item", item); 자동으로 들어간다

		if(result.hasErrors()){
			return "user/join";
		}

		userService.addUserInfo(userBean);
		return "user/join_success";

	}
	
	@GetMapping("/modify")
	public String modify() {
		return "user/modify";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "user/logout";
	}


	//validator(비번확인) 등록
	@InitBinder
	public void initBinder(WebDataBinder binder){
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}


}








