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
	
	//存储对象到redis
	 @RequestMapping("/redisDemoAdd")
	    public String redisDemoAdd(){
		 RedisDemoBean bean=new RedisDemoBean();
		 bean.setId("redis");
		 bean.setValue("yyy");
		 redisDemoService.addRedisDemo(bean);
	     return "demoTest";
	    }
	 
	 //根据key从redis获取对象
	 @RequestMapping("/redisDemoGet")
	    public @ResponseBody RedisDemoBean redisDemoGet(){
		 RedisDemoBean bean=redisDemoService.getRedisDemo("redis");
	     return bean;
	    }
	 
}
