package demo.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.bean.DemoBean;
import demo.service.DemoService;

@Controller
@RequestMapping("/demo")
public class demoController {
	@Autowired 
	DemoService demoService;
	
    @RequestMapping("/demoTest")
    public String demoTest(){
        return "demoTest";
    }

    @RequestMapping("/demoReturnJsonTest")
    public @ResponseBody DemoBean demoReturnJsonTest(){
    	DemoBean demoBean=new DemoBean();
    	demoBean.setId(3);
    	demoBean.setValue("ReturnJsonTest");
        return demoBean;
    }
    
    @RequestMapping("/demoDBTest")
    public @ResponseBody  DemoBean demoDBTest(){
        return demoService.selectDemoBeanById(1);
    }
    
    @RequestMapping("/insertDemoBeanTest")
    public String insertDemoBeanTest(){
    	DemoBean demoBean=new DemoBean();
    	demoBean.setId((new Random()).nextInt(1000));
    	demoBean.setValue((new Random()).nextInt(1000)+"insertDemoBeanTest");
        demoService.insertDemoBean(demoBean);
        return "demoTest";
    }
    
    @RequestMapping("/updateDemoBeanTest")
    public String updateDemoBeanTest(){
    	DemoBean demoBean=new DemoBean();
    	demoBean.setId(1);
    	demoBean.setValue((new Random()).nextInt(1000)+"insertDemoBeanTest");
        demoService.updateDemoBean(demoBean);
        return "demoTest";
    }
    
    @RequestMapping("/delDemoBeanTest")
    public String delDemoBeanTest(){
    	DemoBean demoBean=new DemoBean();
    	demoBean.setId(1);
    	demoBean.setValue((new Random()).nextInt(1000)+"insertDemoBeanTest");
        demoService.delDemoBean(demoBean);
        return "demoTest";
    }
    
    @RequestMapping("/transactionTest")
    public String transactionTest(){
    	DemoBean demoBean=new DemoBean();
    	demoBean.setId((new Random()).nextInt(1000));
    	demoBean.setValue((new Random()).nextInt(1000)+"transactionTestTest");
        demoService.insertTransaction(demoBean);
        return "demoTest";
    }
    
}
