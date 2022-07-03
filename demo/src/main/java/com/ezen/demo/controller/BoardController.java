package com.ezen.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.ezen.demo.model.Board;
import com.ezen.demo.service.BoardService;
@Controller
//컨트롤러라고 알려줌
@RequestMapping("/bbs")
//대표 url 다른==>"/user/...."이라 쳐야됨
public class BoardController {
	
	@Autowired
	private BoardService svc;
	
	@GetMapping("/input")
	   public String getBoard(Model model) {
		Board b = new Board();//pecod로 인한 오류를 막기 위해
		//객체를 생성하여 만들게 되면 그 안에 값이 초기화가 되기 떄문에 부모글이 0으로 셋팅하게 함
		model.addAttribute("board", b);
		//board라는 이름으로 board객체를 저장
	      return "board_inputForm";
	      //로그인 폼으로 가라
	   }
	
	
	 @PostMapping("/save")
	 @ResponseBody //응답을 주는 곳이라는 뜻
	 public Map<String, Object> save(Board board,
			 @SessionAttribute(name="uid",required=false)String uid){
		 //@SessionAttribute로 세션 가져오기
		 
		 
		
		 Map<String, Object> map= new HashMap<>();
		 if(uid==null) {
			 map.put("saved", false);
			 return map;
			 //만약 세션이 없으면 saved에 false를 반환하여 그 값을 axja로
			 }

		  boolean saved = svc.save(board);
		  //boolean타입으로 save 서비스로 파라미터를 board객체를 줌
		  //이 board객체에는 로그인 폼에서 입력한 데이터들이 들어감
		  map.put("saved", saved);
		  //map에 saved라는 boolean타입의 변수를 넣어줌
		  //이는 스프링에서 알아서 json문자로 바꿔줌
		
		  return map;
		  //이 리턴값은 jsp에 axja로 감
		  
	   }
}
