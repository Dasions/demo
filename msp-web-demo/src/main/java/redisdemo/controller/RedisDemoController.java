package redisdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import redisdemo.bean.RedisDemoBean;
import redisdemo.service.RedisDemoService;

@Controller
@RequestMapping("/redisDemo")
public class RedisDemoController {
	@Autowired 
	RedisDemoService redisDemoService;
	
	 @RequestMapping("/redisDemoAdd")
	    public String redisDemoAdd(){
		 RedisDemoBean bean=new RedisDemoBean();
		 bean.setId("redis");
		 bean.setValue("test");
		 redisDemoService.addRedisDemo(bean);
	     return "demoTest";
	    }
	 
	 @RequestMapping("/redisDemoGet")
	    public @ResponseBody RedisDemoBean redisDemoGet(){
		 RedisDemoBean bean=redisDemoService.getRedisDemo("redis");
	     return bean;
	    }
	 
}
