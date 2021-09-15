package project.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.demo.beans.UserBean;
import project.validator.UserValidator;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/login")
	public String login() {
		return "user/login";
	}
	
	@GetMapping("/join")
	public String join(Model model) {
		model.addAttribute("user" , new UserBean());
		return "user/join";
	}

	@PostMapping("/join")
	public String joinUser(@ModelAttribute UserBean userBean, RedirectAttributes redirectAttributes, Model model){


		//번외
		//@ModelAttribute Item item -> model.addAttribute("item", item); 자동으로 들어간다.

		//검증에 실패한 데이터도  Model에 담아야 한다.
		//검증 오류 결과를 보관
		Map<String,String> errors = new HashMap<>();

		//검증 로직(필드 룰)
		if(!StringUtils.hasText(userBean.getUser_name())){
			errors.put("userName", "이름을 입력해주세요");
		}
		if(!StringUtils.hasText(userBean.getUser_id())){
			errors.put("userId", "아이디는 필수입니다.");
		}
		if(!StringUtils.hasText(userBean.getUser_pw())){
			errors.put("userPw", "비밀번호는 필수입니다.");
		}
		if(!StringUtils.hasText(userBean.getUser_pw())){
			errors.put("userPw2", "비밀번호 확인은 필수입니다.");
		}

		//검증에 실패하면 다시 입력 폼으로
		if(!errors.isEmpty()){
			log.info("errors = {}", errors);
			model.addAttribute("errors", errors);
			return  "/user/join";

		}

		//성공 로직

		return "";


	}
	
	@GetMapping("/modify")
	public String modify() {
		return "user/modify";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "user/logout";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder){
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}
}








