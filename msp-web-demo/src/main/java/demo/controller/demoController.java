package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.bean.DemoBean;

@Controller
@RequestMapping("/demo")
public class demoController {

    @RequestMapping("/demoTest")
    public String demoTest(){
        return "demoTest";
    }

    @RequestMapping("/demoReturnJsonTest")
    public @ResponseBody DemoBean demoReturnJsonTest(){
    	DemoBean demoBean=new DemoBean();
    	demoBean.setId("demo");
    	demoBean.setValue("ReturnJsonTest");
        return demoBean;
    }
}
