package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.pojo.User;

@Controller
public class FirstController {
	
	@RequestMapping("/index")
	public String firstMethod() {
		
		return "index";
	}
	
	@RequestMapping(value="/param",method=RequestMethod.POST)
	@ResponseBody
	public void secondMethod(@RequestParam(value="name",defaultValue="wangwu")String name,@RequestParam(value="age",defaultValue="18")int age,HttpServletRequest req) {
		System.out.println(name);
		System.out.println(age);
		System.out.println(req.getParameter("salary"));
	}
	
	@RequestMapping(value="/modelt")
	public String modelMethod(User user,Model model,String msg) {
		
		model.addAttribute("user",user);
		model.addAttribute("msg",msg);
		System.out.println(user);
		return "model";
	}
	@RequestMapping(value="/map",method=RequestMethod.POST)
	@ResponseBody
	public void mapMethod(@RequestParam Map<String,String> m) {
		
		System.out.println(m);
		
	}
	
	@RequestMapping(value="/head",method=RequestMethod.POST)
	@ResponseBody
	public void headMethod(@RequestHeader ("User-Agent") String userAgent) {
		System.out.println(userAgent);
	}
	
	@RequestMapping(value="/users/{userId}/roles/{roleId}",method=RequestMethod.POST)
	@ResponseBody
	public void changeParamMethod(@PathVariable("userId") String userId,@PathVariable("roleId") String roleId) {
		System.out.println(userId);
	}
	
	@RequestMapping("/redirect")
	public String redirectMethod(RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addAttribute("msg","sasasa");
		return "redirect:modelt";
	}
	
	@RequestMapping("/forward")
	public String forwordMethod(Model model) {
		model.addAttribute("msgforword","wqwqwq");
		return "forward:modelt";
	}
	
	@RequestMapping("/userjson")
	@ResponseBody
	public User jsonTest(User user) {
		System.out.println(user);
		return user;
	}
	@RequestMapping("/produceuser")
	@ResponseBody
	public List<User> produceUse() {
		System.out.println("请求了");
		List<User> users= new ArrayList<User>();
		for(int i=0;i<=10;i++) {
		User user = new User();
		user.setName("张三"+i);
		user.setAge(18);
		users.add(user);
		}
		return users;
	}
	
	@RequestMapping("/model")
	public String modelMethod() {
		return "model";
	}
	
}
