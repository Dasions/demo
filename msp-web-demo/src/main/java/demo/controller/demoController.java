package demo.controller;

import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;

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
    	demoBean.setId("3");
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
    	//demoBean.setId(""+(new Random()).nextInt(10000)+(new Random()).nextInt(10000));
    	demoBean.setValue((new Random()).nextInt(1000)+"insertDemoBeanTest");
        demoService.insertDemoBean(demoBean);
        return "demoTest";
    }
    
    @RequestMapping("/updateDemoBeanTest")
    public String updateDemoBeanTest(){
    	DemoBean demoBean=new DemoBean();
    	demoBean.setId("1");
    	demoBean.setValue((new Random()).nextInt(1000)+"updateDemoBeanTest");
        demoService.updateDemoBean(demoBean);
        return "demoTest";
    }
    
    @RequestMapping("/delDemoBeanTest")
    public String delDemoBeanTest(){
    	DemoBean demoBean=new DemoBean();
    	demoBean.setId("1");
        demoService.delDemoBean(demoBean);
        return "demoTest";
    }
    
    @RequestMapping("/transactionTest")
    public String transactionTest(){
    	DemoBean demoBean=new DemoBean();
    	demoBean.setId(""+(new Random()).nextInt(1000));
    	demoBean.setValue((new Random()).nextInt(1000)+"transactionTestTest");
        demoService.insertTransaction(demoBean);
        return "demoTest";
    }
    
    //http://localhost:8081/msp-web-demo/demo/demoSendParamToAppTest?value=kkk333
    @RequestMapping("/demoSendParamToAppTest")
    public @ResponseBody DemoBean demoSendParamToAppTest(HttpServletRequest request){
    	String value=request.getParameter("value");
    	DemoBean demoBean=new DemoBean();
    	demoBean.setValue(value);
        return demoBean;
    }
    
    //不用前台传的分页参数，在后台代码自定义分页参数进行分页查询
    @RequestMapping("/selectDemoBeansTest")
    public @ResponseBody List<DemoBean> selectDemoBeansTest(){
        int page=1;
        int rows=2;
        PageHelper.startPage(page, rows);
    	return demoService.selectDemoBeans();
    }
    
    //用前台传的分页参数进行分页查询
    //http://localhost:8081/msp-web-demo/demo/selectDemoBeansByPageTest?page=1&&rows=2
    @RequestMapping("/selectDemoBeansByPageTest")
    public @ResponseBody List<DemoBean> selectDemoBeansByPageTest(){
    	return demoService.selectDemoBeans();
    }
}
