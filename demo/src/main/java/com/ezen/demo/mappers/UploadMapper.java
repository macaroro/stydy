package com.ezen.demo.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.demo.vo.Attach;
import com.ezen.demo.vo.Upload;

@Mapper
public interface UploadMapper 
{
	int insertUpload(Upload vo);
	int insertMultiAttach(List<Attach> list);	
	int insertAttach(Map<String, Object> map);
	
	List<Map<String, Object>> getList();
	
	String getFname(int num);
	
	List<Map<String, Object>> getDetail(int num);

	int deleteAttach(int num);
	int deleteUpload(int num);

	int insertAttach(Attach att);
	
	
	
	List<String> getAllFname(int num);
	
}