package com.ezen.demo.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.demo.model.Board;

@Mapper
public interface MybatisBoardMapper {
	
	List<Board> list();
	int insert(Board bbs);
	Board getBoard(int num);
	int update(Board bbs);
	int delete(int num);
	List<Board> search(Map<String,String> map);

}
