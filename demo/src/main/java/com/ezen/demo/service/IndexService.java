package com.ezen.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service//스프링에게 이것이 서비스라고 알려준다
public class IndexService 
{
   private HttpServletRequest request;
   
   
   public HttpServletRequest getRequest() {
	return request;
}
@Autowired//스프링에서 자동적으로 아래의 메소드를 실행 /Dependency Injection==>setter Injection/
public void setRequest(HttpServletRequest request) {
	this.request = request;
}

public IndexService() 
   {
 
   }
   
   public List<String> getGugu(int dan)
   {
//      String sDan = request.getParameter("dan");
//      int dan = 0;
//      try {
//         dan = Integer.parseInt(sDan);
//      }catch(NumberFormatException nfe) {
//         dan = 2;
//      }
//      
    
      List<String> list= new ArrayList<>();
      for(int i=1;i<=9;i++) {
    	  
    	  list.add(String.format("%d * %d = %d<br>", dan, i, dan*i)); 
         
		
      }
     
      return list;
   }
}