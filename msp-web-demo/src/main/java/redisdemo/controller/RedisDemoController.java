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
	 * �洢����redis
	 * @return
	 */
	 @RequestMapping("/redisDemoAdd")
	    public String redisDemoAdd(){
		 RedisDemoBean bean=new RedisDemoBean();
		 bean.setId("notransaction");
		 bean.setValue("yyy");
		 redisDemoService.addRedisDemo(bean);
	     return "demoTest";
	    }
	 
	 
	 /**
	  * ����key��redis��ȡ����
	  * @return
	  */
	 @RequestMapping("/redisDemoGet")
	    public @ResponseBody RedisDemoBean redisDemoGet(){
		 RedisDemoBean bean=redisDemoService.getRedisDemo("notransaction");
	     return bean;
	    }

	 /**
	  * redis������÷�
	  * @return
	  */
	 @RequestMapping("/addRedisTransactionsDemo")
	    public String addRedisTransactionsDemo(){
		 redisDemoService.addRedisTransactionsDemo();
	     return "demoTest";
	    }
	 
	 @RequestMapping("/getRedisTransactionsDemo")
	 public void getRedisTransactionsDemo(){
		 System.out.println(redisDemoService.getRedisTransactionsDemo("w"));
	 }
}
