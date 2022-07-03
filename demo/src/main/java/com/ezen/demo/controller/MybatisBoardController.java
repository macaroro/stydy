package com.ezen.demo.controller;

import java.util.List;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.mappers.MybatisBoardMapper;
import com.ezen.demo.model.Board;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/bbs")
public class MybatisBoardController {
	
	@Autowired
	private MybatisBoardMapper dao;
	
	@GetMapping("/list")
	public String getList(Model model) {
		
		List<Board> list = dao.list();
		model.addAttribute("list", list);
		return "board/board_list";
		
	}
	
	@GetMapping("add")
	public String add() {
		return "board/add";
	}
	
	@PostMapping("/insert")
	@ResponseBody
	public Map<String,Object> insert( @RequestParam("title") String title,
			                          @RequestParam("contents") String contents,
			                          @RequestParam("pcode") int pcode){
		
		Board bbs = new Board();
		bbs.setTitle(title);
		bbs.setContents(contents);
		bbs.setAuthor("민경");
		bbs.setPcode(pcode);
		
		boolean saved = dao.insert(bbs)>0;
		 Map<String,Object> map = new HashMap<>();
		 map.put("saved", saved);

		return map;
		
		
	}
	
	@GetMapping("/detail/{num}")
	public String getBoard(@PathVariable("num") int num, Model model) {
		
		Board bbs = dao.getBoard(num);
		model.addAttribute("bbs", bbs);
		return "board/getBoard";
		
	}
	
	@GetMapping("/edit/{num}")
	public String edit(@PathVariable("num") int num, Model model) {
		
		Board bbs = dao.getBoard(num);
		model.addAttribute("bbs", bbs);
		return "board/edit";	}
	
	
	@PostMapping("/update")
	@ResponseBody
	public Map<String,Object> update( @RequestParam("title") String title,
			                          @RequestParam("contents") String contents,
			                          @RequestParam("num") int num)
			                       {
		
		Board bbs = new Board();
		bbs.setNum(num);
		bbs.setTitle(title);
		bbs.setContents(contents);

		
		boolean updated = dao.update(bbs)>0;
		 Map<String,Object> map = new HashMap<>();
		 map.put("updated",updated);

		return map;
	
	}
	
	@PostMapping("/delete/{num}")
	@ResponseBody
	public Map<String,Object> delete(@PathVariable("num") int num)
	 {

		System.out.println("1");
		boolean deleted = dao.delete(num)>0;
		 Map<String,Object> map = new HashMap<>();
		 map.put("deleted",deleted);

		return map;
	
	}
	@PostMapping("/search")
	public String search(@RequestParam("key") String key,
			@RequestParam("category") String category,
			@RequestParam(name="psize",defaultValue = "2") int psize,
			@RequestParam(name="page",defaultValue = "1") int page,
			Model model) {
		
		Map<String,String> map  = new HashMap<>();
		map.put("category", category);
		map.put("key", key);
		
		PageHelper.startPage(page,psize);
		
		PageInfo<Board> pageInfo = new PageInfo<>(dao.search(map));
		model.addAttribute("category",category);
		model.addAttribute("key",key);
		model.addAttribute("pageInfo",pageInfo);
	
		return "board/board_search";
	}
	
//	@PostMapping("/page")
//	public String page(Model model, 
//			@RequestParam(name="page", required = false,defaultValue ="1") int page)
//	//페이지를 설정하면 처음으로 뜰 화면을 기본1로 설정하여 startPage에 넣어준다
//{       PageHelper.startPage(page, 3);
//		//startPage시작하는 페이지 넘버와 그 페이지에 얼마의 글이 들어갈지를 정한다.
//		PageInfo<Board> pageInfo = new PageInfo<>(dao.list());
//		List<Board> list= pageInfo.getList();	 
//		 model.addAttribute("pageInfo", pageInfo);
//		
//		return "board/page";
//	}
	
	

}
