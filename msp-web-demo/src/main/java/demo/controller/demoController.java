package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class demoController {

    @RequestMapping("/demoTest")
    public String demoTest(){
        return "demoTest";
    }

}
