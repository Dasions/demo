package demo.test.controller;

import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import demo.bean.DemoBean;
import demo.controller.DemoController;
import demo.service.DemoService;

//@ContextConfiguration(locations={"classpath*:/springmvc-servlet.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/resources/springmvc-servlet.xml")
public class DemoControllerTest {
	private MockMvc mockMvc;
	@Mock
	private DemoService demoService;
	@InjectMocks
	DemoController demoController;
	
	 @Before
	 public void setup() {
	     MockitoAnnotations.initMocks(this);
	     this.mockMvc = MockMvcBuilders.standaloneSetup(demoController).build();
	 }
	 
	 @Test
	 public void testController() throws Exception{  
     when(demoService.selectDemoBeanById("1")).thenReturn(new DemoBean("1","vvv"));
     System.out.println(demoService.selectDemoBeanById("1").getValue());
	 }
}
