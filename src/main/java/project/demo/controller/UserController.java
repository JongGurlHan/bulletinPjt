package project.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import project.demo.beans.UserBean;
import project.demo.service.UserService;
import project.demo.validator.UserValidator;

import javax.annotation.Resource;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginUserBean")UserBean tempLoginUserBean,
						@RequestParam(value = "fail", defaultValue = "false") boolean fail,
						Model model){

		//fail이란 이름의 파라미터에 true가 들어간다면 로그인 실패
		// fail이 들어간다면 로그인 성공

		model.addAttribute("fail", fail);

		return "user/login";
	}

	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("tempLoginUserBean")UserBean tempLoginUserBean, BindingResult result){
		if(result.hasErrors()){
			return "user/login";
		}

		userService.getLoginUserInfo(tempLoginUserBean);

		if(loginUserBean.isUserLogin() == true){
			return "user/login_success";
		}else{
			return "user/login_fail";
		}

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

		loginUserBean.setUserLogin(false);

		return "user/logout";
	}

	@GetMapping("/not_login")
	public String not_login(){
		return "user/not_login";
	}


	//validator(비번확인) 등록
	@InitBinder
	public void initBinder(WebDataBinder binder){
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}


}








