package com.ezen.demo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.service.IndexService;




@Controller
@RequestMapping("/index")//이 클래스에서 대표로 쓰이는 url 이 url 입력후 개인 url입력
public class IndexController {
	@Autowired
 private IndexService svc;//field Injection(선언만 할테니 너가 생성해 주렴)
   
   @GetMapping("")// /index로 주소창 입력
   public String index() {
      return "index";
   }
   

       
    
   
  
  // @RequestMapping(value="/gugu", method=RequestMethod.GET)//포스트는 주소창에 입력해셔 하는것이 아니기 때문에 오류가 남
   @GetMapping("/gugu") //이것과 아래의 정의는 같음
   public String getGugu(Model model, @PathVariable("dan") int dan)
  {//"dan"을 받아오돼 만약 값이 없어 false가 나오면 2를 넣어라
     
      //IndexService svc = new IndexService(request); 이건 예전 방식 스프링에서는 스프링에서 객체를 만들수 있게 해야됨
        //List<String> msg = svc.getGugu();
        
      model.addAttribute("msg", svc.getGugu(dan));
       //실제로 돌아가면 스프리에서 인덱스 서비스의 객체를 만들어서 값을 넣어주기 때문에 가능
        return "gugu";
  }


   
   @GetMapping("/gugu/{dan}")//포스트는 주소창에 입력해셔 하는것이 아니기 때문에 오류가 남
  public String getGugu2(Model model, @PathVariable("dan")int dan)
  {//"dan"을 받아오돼 만약 값이 없어 false가 나오면 2를 넣어라
     
      //IndexService svc = new IndexService(request); 이건 예전 방식 스프링에서는 스프링에서 객체를 만들수 있게 해야됨
        //List<String> msg = svc.getGugu();
	   
	   
	   System.out.println("dan="+dan);
        
      model.addAttribute("msg", svc.getGugu(dan));
       //실제로 돌아가면 스프리에서 인덱스 서비스의 객체를 만들어서 값을 넣어주기 때문에 가능
        return "gugu";
  }
   
   @PostMapping("/res")
   @ResponseBody//애가 응답이야!!==> 즉 jsp의 역할이야, ajax같은것에 좋음
   public String gugu3() {
	   
	   
	   return "{\"saved\":true}";
	   
	   
	   
   }
   
   
   @PostMapping("/test/{num}")//{}안에 있는 값만 쓰겠다
   @ResponseBody//애가 응답이야!!==> 즉 jsp의 역할이야, ajax같은것에 좋음
   public Map<String,Object> gugu4(@PathVariable("num")int num) {
	   
	   Map<String,Object> map = new HashMap<>();
	   map.put("num",num);
	   System.out.println(num);
	   return map;
	   
	   
	   
   }
   @PostMapping("/fruit")
   @ResponseBody//애가 응답이야!!==> 즉 jsp의 역할이야, ajax같은것에 좋음
   public Map<String,Object>  fruit(@RequestParam("fruit") String fruit, Model model) {
	   //@RequestPram은 마치 request.getParameter같은 느낌 ajax에서 날라온 데이터를 받음
	   
	   Map<String,Object> map = new HashMap<>();
	   map.put("select",fruit);
	   //받은 것을 맵핑하여 다시 ajax로 날려줌
	   return map;
   }
  

}

