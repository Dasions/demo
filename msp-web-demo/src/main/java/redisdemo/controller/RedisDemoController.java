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
	
	/**
	 * 存储对象到redis
	 * @return
	 */
	 @RequestMapping("/redisDemoAdd")
	    public String redisDemoAdd(){
		 RedisDemoBean bean=new RedisDemoBean();
		 bean.setId("test-key");
		 bean.setValue("test value");
		 redisDemoService.addRedisDemo(bean);
	     return "demoTest";
	    }
	 
	 
	 /**
	  * 根据key从redis获取对象
	  * @return
	  */
	 @RequestMapping("/redisDemoGet")
	    public @ResponseBody RedisDemoBean redisDemoGet(){
		 RedisDemoBean bean=redisDemoService.getRedisDemo("test-key");
	     return bean;
	    }

	 /**
	  * redis事务的用法
	  * @return
	  */
	 @RequestMapping("/addRedisTransactionsDemo")
	    public String addRedisTransactionsDemo(){
		 redisDemoService.addRedisTransactionsDemo();
	     return "demoTest";
	    }
	 
	 @RequestMapping("/getRedisTransactionsDemo")
	 public String getRedisTransactionsDemo(){
		 return redisDemoService.getRedisTransactionsDemo("test-1");
	 }
}
