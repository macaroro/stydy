package com.ezen.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.demo.model.Board;
import com.ezen.demo.model.BoardDAO;

@Service
//여긴 서비스입니다하고 스프링에게 알려줌
public class BoardService {
	
	@Autowired
	private BoardDAO dao;
	//굳이 dao 객체를 new 하지 않아도 되도록 설정
	
	public boolean save(Board board) {
		return dao.save(board);
		//boolean타입의 dao의 값을 리턴 받음
		//리턴받은 값은 다시 컨트롤러로 가서 ajxa로 이어짐
	}

}
