package com.ezen.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.demo.vo.Attach;
import com.ezen.demo.vo.Upload;
import com.ezen.demo.service.UploadService;

@Controller
@RequestMapping("/files")
public class UploadController 
{
	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	private UploadService svc;

	@GetMapping("/upload")
	public String getForm() 
	{	
		return "upload/upload_form";
		//파일과 설명 작성자를 입력할수 있는 폼으로(get)
	}
	

	@PostMapping("/upload")
	@ResponseBody
	public String upload(@RequestParam("files")MultipartFile[] mfiles,
			 				//파일을 받을때 필요한 파라미터
							HttpServletRequest request, 
							@RequestParam(name="pnum", defaultValue="0") int pnum,
							Upload vo) 
	{
		//System.out.println("pnum=" + pnum);
		
		ServletContext context = request.getServletContext();
		//ServletContext는 서블릿 컨테이너와 통신하기 위해서 사용되는 메소드를 지원하는 인터페이스
		String savePath = context.getRealPath("/WEB-INF/files");
		//이클립스 내에 파일을 저장하는 경로를 설정해준다(절대경로)
		
		vo.setFpath(savePath);
		//vo의 값이 들어가도록 셋팅
		
		List<Attach> attList = new ArrayList<>();
		//처음 업로드 할떄 attach vo에도 정보가 들어가기 때문에 
		//upload vo에 모든 테이블에 변수가 다 정의된것이 아니라 업로드 테이블이 있는 정보와
		//attach 테이블의 정보는 list로 가지고 있는것이다. 
		//즉 attach vo에 값을 셋팅해주어 attach 테이블에도 값이 들어갈 수 있게 해준다
		/* static/upload 디렉토리에 업로드하려면, 아래처럼 절대경로를 구하여 사용하면 된다
		* Resource resource = resourceLoader.getResource("classpath:/static");
		* String absolutePath = resource.getFile().getAbsolutePath();
		*/ 
		try {
			for(int i=0;i<mfiles.length;i++) {
				//파일이 들어온 배열의 길이 만큼 반목
				String fname_orign = mfiles[i].getOriginalFilename();
				//파일의 원래 이름
				String[] token = fname_orign.split("\\.");
				//"."으로 구분되어 하나씩 나누고
				String fname_changed = token[0]+System.nanoTime()+"."+token[1];
				//나눠진 이름에 나노타임을 붙여 중복저장을 막는다
				
				Attach att = new Attach();
				//attach vo의 객체를 생성해 이후 리스트에 들어가서
				//upload vo에 정보가 들어갈수 있도록한다.
				
				att.setPnum(pnum);
				//이 번호는 upload의 num을 가져와 attach 테이블에 fk로 쓰기 때문에 필수
				att.setFname(fname_changed);
				//나노타임이 있는 이름
				att.setFpath(savePath);
				//절대경로 설정
				
				attList.add(att);
				//위에 설정한 데이터들이 들어있는 객체를 리스트에 담는다
				//upload vo에서 쓸수 있게 함
				
				mfiles[i].transferTo(
				  new File(savePath+"/"+fname_changed));
				//이 파일들을 객체에 담아 설정해준 경로로 이동시킴
				
				/* MultipartFile 주요 메소드
				String cType = mfiles[i].getContentType();
				String pName = mfiles[i].getName();
				Resource res = mfiles[i].getResource();
				long fSize = mfiles[i].getSize();
				boolean empty = mfiles[i].isEmpty();
				*/
			}
			
			vo.setAttach(attList);
			//upload vo에서도 쓸수 있도록
			boolean inserted = svc.insert(vo);
			//서비스에서 실행시킨 insert 메소드를 boolean 타입의 값으로 받음
			
			return "{\"inserted\":" + inserted +"}";
			//그 메소드의 값을 가져와서 화면에 뿌려줌
		} catch (Exception e) {
			e.printStackTrace();
			return "{\"inserted\":" + false +"}";
		}
	}
	/*
	@GetMapping("/download/{filename}")
	public ResponseEntity<Resource> download(
										HttpServletRequest request,
										@PathVariable String filename)
	{
		Resource resource = resourceLoader.getResource("WEB-INF/files/"+filename);
		System.out.println("파일명:"+resource.getFilename());
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
 
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
	}*/
	
	@GetMapping("/download/{num}")
	public ResponseEntity<Resource> downloadByNum(
										HttpServletRequest request,
										@PathVariable("num") int num)
										//get방식으로 넘어온 숫자를 받음
	{
		String filename = svc.getFname(num);
		//넘어온 글번호를 기준으로 그 글의 이름을 찾는 서비스를 사용
		Resource resource = resourceLoader.getResource("WEB-INF/files/"+filename);
		//경로에 있는 파일을 읽어옴
		System.out.println("File Download : "+resource.getFilename());
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
 
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
 
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
	}
	
	@GetMapping("/list")
	public String getList(Model model)
	{
		List<Upload> list = svc.getList();
		model.addAttribute("list", list);
		//서비스에서 가져온 값을 model에 답아 리턴되는 곳으로 전달
		return "upload/list";
	}
	
	@GetMapping("/detail/{num}")
	public String getDetail(@PathVariable("num") int num, Model model)
	{
		Upload vo = svc.getDetail(num);
		model.addAttribute("vo", vo);
		return "upload/detail";
	}
	
	@GetMapping("/delete/{num}")
	@ResponseBody
	public Map<String, Boolean> delete(HttpServletRequest request,
			                           @PathVariable("num")int num)
	{
		boolean deleted = false;
		Map<String,Boolean> map = new HashMap<>();
		try {
			deleted = svc.deleteAttach(request, num);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		map.put("deleted", deleted);
		return map;
	}
	
	
}