package com.ezen.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.ezen.demo.model.User;
import com.ezen.demo.service.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("uid")
//model 에서 넣어진 값을 세션으로 설정하는 정의
public class UserController {
@Autowired
private UserService svc;
	
	
	@GetMapping("/login")
	public String login() {
		
		return "login_form";
		
	}
	
	@ResponseBody
	@PostMapping("/confirm")
	public Map<String,Object> confirm(User user, Model model){
		
		boolean ok = svc.login(user);
		//boolean 타입으로 서비스 login의 리턴 값을 ok에 저장
		
		model.addAttribute("uid", user.getUid());
		//세션에서 쓸 수 있게 먼저 request영역에 저장하는 
		//model.addAttibute로 설정한다.
		Map<String,Object> map = new HashMap<>();
		map.put("login", ok);
		//mpa에 login이라는 이름으로 ok를 가져온다
		return map;
		//그 값을 map에 담어 json문자로 변화하여 axja로 
		
		
	}
	

	@ResponseBody
	@PostMapping("/logout")
	public Map<String,Object> logout(SessionStatus status){
		//SessionStatus 변수 이름을 설정
		
		status.setComplete();
		//세션을 삭제한다
		Map<String,Object> map = new HashMap<>();
		map.put("logout", true);
		//true값을 맵에 넣어 그것을 리턴
		return map;
		
		
	}

}
