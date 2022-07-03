package com.ezen.demo.service;

import java.io.File;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.demo.mappers.UploadMapper;
import com.ezen.demo.vo.Attach;
import com.ezen.demo.vo.Upload;

@Service
public class UploadService 
{
	@Autowired
	private UploadMapper dao;
//
//	public boolean insert(Upload vo) 
//	   {
//	      int pnum = 0;
//	      if(vo.getWriter()!=null) {
//	         dao.insertUpload(vo);    //upload_tb에 저장
//	         pnum = vo.getNum();  // 자동 증가된 업로드 번호를 받음
//	      }
//
//	      List<Attach> attList = vo.getAttach();  //첨부파일 정보
//	      
//	      if(pnum>0) {
//	         for(int i=0;i<attList.size();i++) {
//	            attList.get(i).setPnum(pnum);
//	         }
//	      }
//	      
//	      int insertedCnt = 0;
//	      for(int i=0;i<attList.size();i++) {
//	         insertedCnt += dao.insertAttach(attList.get(i));
//	      }
//	      
//	      return insertedCnt==attList.size();
//	   }
	
	public boolean insert(Upload vo) 
	{
		int pnum = 0;
		if(vo.getWriter()!=null) {
			//만약 작성자가 잇다면
			dao.insertUpload(vo);   
			//upload_tb에 저장
			pnum = vo.getNum();  
			// 자동 증가된 업로드 번호를 받음
		}//이후 추가 업로드때문에 이런 조건이 붙음
		
		List<Attach> attList = vo.getAttach();
		//controller에서 셋팅한 값을 가져옴 즉 첨부파일의 정보
		
		if(pnum>0) {
			//만약 넘어온 부모번호가 0보다 크다면
			for(int i=0; i<attList.size();i++) {
				//list 사이즈 즉 내가 넣은 파일의 정보 개수만큼 반복
				attList.get(i).setPnum(pnum);
				//리스트에 담아있는 부모번호를 각 배열의 번호에 셋팅해줌
			}
		}
		
		int res = dao.insertMultiAttach(attList);
		//attach 테이블에 입력된 행의 개수를 설정하여
		return res==attList.size();
		//받은 파일의 정보가 저장되있는 list의 사이즈와
		//테이블에 입력된 개수가 같으면 true이다.
	}


	public List<Upload> getList() 
	{
		List<Map<String, Object>> list = dao.getList();  
		//db에서 select한 값을 가져옴(left outer join)
		List<Upload> voList = new ArrayList<>();
		//upload 타입의 리스트를 만들어줌
		
		for(int i=0;i<list.size();i++) {
			Map<String, Object> map = list.get(i);
			//그리고 map에 select한 정보를 넣어준다.
			
			String fname = (String)map.get("FNAME");
			//fname에는 맵에 있는 FNAME의 키의 value를 가져온다.

			Attach aVO = null;
			
			if(fname!=null) {
				//fname이 있다면
				aVO = new Attach();
				//객체를 생성하고 그 객체에
				int fnum = ((BigDecimal)map.get("FNUM")).intValue();
				//mapper에서 셋팅한 가져오는 컬럼중 
				//FNUM(ATTACH 테이블의 번호)의 값을 가져와 fnum에 대입
				aVO.setNum(fnum);
				//그리고 위의 번호를 attach의 num에 설정
				aVO.setFname(fname);
				//fname또한 설정해준다
			}
			
			Upload vo = new Upload();
			//upload 객체를 만들어 그 객체에
			int num = ((BigDecimal)map.get("NUM")).intValue();
			//NUM의 값을 변수에 저장하고
			vo.setNum(num);
			//셋팅해준다.
			
			if(voList.contains(vo)) {
				//리스트에 있는 내용과 vo객체의 번호가 같으면
				voList.get(voList.size()-1).getAttach().add(aVO);
				//리스트에 사이즈를 줄이고 그 리스트의 attach정보를 추가 시킴
			}else {
				//아니라면
				String writer = (String)map.get("WRITER");
				Date udate = new Date(((Timestamp)map.get("UDATE")).getTime());
				String comments = (String)map.get("COMMENTS");
				
				vo.setWriter(writer);
				vo.setUdate(udate);
				vo.setComments(comments);
				vo.getAttach().add(aVO);
				
				voList.add(vo);
				//위에 정보 모두 가져와 voList에 추가함
			}
		}
		return voList;//리스트를 리턴
	}

	public String getFname(int num) {
		return dao.getFname(num);
	}
	
	public Upload getDetail(int _num)
	{
		List<Map<String, Object>> list = dao.getDetail(_num);
		//db의 리스트를 불러와서 list에 담는다
		Upload vo = new Upload();
		//upload객체를 만든다.
		for(int i=0;i<list.size();i++) {
			Map<String, Object> map = list.get(i);
			
			
			int num = ((BigDecimal)map.get("NUM")).intValue();
			String writer = (String)map.get("WRITER");
			Date udate = new Date(((Timestamp)map.get("UDATE")).getTime());
			String comments = (String)map.get("COMMENTS");
			
			vo.setNum(num);
			vo.setWriter(writer);
			vo.setUdate(udate);
			vo.setComments(comments);
			//vo객체에 map에 담긴 key값의 value들을 넣어주고 값을 설정
			
			String fname = (String)map.get("FNAME");
			//이름은 attach 테이블에 존재

			if(fname!=null) {
				//만약 이름이 있다면
				Attach aVO = new Attach();
				int fnum = ((BigDecimal)map.get("FNUM")).intValue();
				aVO.setNum(fnum);
				aVO.setFname(fname);
				vo.getAttach().add(aVO);
				//첨부 파일 정보도 추가해줌
			}
		}
		return vo;
		//upload의 객체를 리턴
	}

	@Transactional(rollbackFor= {Exception.class})
	//만약 이중하나라도 에러가 나면 rollback
	public boolean deleteAttach(HttpServletRequest request, int pnum) throws Exception
	
	{
		boolean dbDeleted = dao.deleteAttach(pnum)>0;
		//넘어온 번호로 attach 테이블 정보 삭제
		if(!dbDeleted) throw new Exception("fail");
		boolean uploadDelete=dao.deleteUpload(pnum)>0;
		//넘어온 번호로 upload 테이블 정보 삭제
		if(!uploadDelete) throw new Exception("fail");
		
		List<String> list= dao.getAllFname(pnum);
		//넘어온 글번호의 모든 첨부파일의 이름 가져와기
		String path = request.getServletContext().getRealPath("WEB-INF/files/");
		int count=0;
		
		if(list==null||list.size()==0) {
			for(int i=0;i<list.size();i++) {
				String del=path+list.get(i);
				//파일의 절대 경로와 리스트 i번째의 이름을 가져오기

				File f = new File(del);
				//파일의 객체를 생성
				
				if(!f.exists()) {
					System.out.println("File Not Found");
					continue;
				}
				count+=f.delete()? 1:0;
				//그 객체를 삭제수 참이면 1,아니면0을 리턴
				//그 값을 카운트
			}
				if(count==list.size()) {
					System.out.println("success");
					//만약 그 카운트가 리스트에 사이즈와 같으면
					//성공
				}else {
					System.out.println("fail");
			
			
		}
		
		
	}
		return true;
	}
}

	


