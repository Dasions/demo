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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import java.awt.List;
import java.util.LinkedList;

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
	@Mock
	LinkedList list;
	 @Before
	 public void setup() {
	     MockitoAnnotations.initMocks(this);
	     this.mockMvc = MockMvcBuilders.standaloneSetup(demoController).build();
	 }
	 
	 @Test
	 public void testDemoReturnJsonTest() throws Exception{  
		 mockMvc.perform(post("/demo/demoReturnJsonTest")).andDo(print()).andExpect(status().isOk());
	 }
	 
	 @Test
	 public void testDemoDBTest() throws Exception{  
		 mockMvc.perform(post("/demo/demoDBTest")).andDo(print()).andExpect(status().isOk());
	 }
	 
	 @Test
	 public void testInsertDemoBeanTest() throws Exception{  
		 mockMvc.perform(post("/demo/insertDemoBeanTest")).andDo(print()).andExpect(status().isOk());
	 }
	 
	 @Test
	 public void testUpdateDemoBeanTest() throws Exception{  
		 mockMvc.perform(post("/demo/updateDemoBeanTest")).andDo(print()).andExpect(status().isOk());
	 }
	 
	 @Test
	 public void testDelDemoBeanTest() throws Exception{  
		 mockMvc.perform(post("/demo/delDemoBeanTest")).andDo(print()).andExpect(status().isOk());
	 }
	 
	 @Test
	 public void testTransactionTest() throws Exception{  
		 mockMvc.perform(post("/demo/transactionTest")).andDo(print()).andExpect(status().isOk());
	 }
	 
	 @Test
	 public void testDemoSendParamToAppTest() throws Exception{  
		 when(list.get(0)).thenReturn("test value 1").thenReturn("test value 2").thenReturn("test value 3").thenReturn("test value 4");
		 mockMvc.perform(post("/demo/demoSendParamToAppTest").param("value", list.get(0).toString())).andDo(print()).andExpect(status().isOk());
		 mockMvc.perform(post("/demo/demoSendParamToAppTest").param("value", list.get(0).toString())).andDo(print()).andExpect(status().isOk());
		 mockMvc.perform(post("/demo/demoSendParamToAppTest").param("value", list.get(0).toString())).andDo(print()).andExpect(status().isOk());
	 }
	 
	 @Test
	 public void testSelectDemoBeansTest() throws Exception{  
		 mockMvc.perform(post("/demo/selectDemoBeansTest")).andDo(print()).andExpect(status().isOk());
	 }
	 
	 @Test
	 public void testSelectDemoBeansByPageTest() throws Exception{  
		 mockMvc.perform(post("/demo/selectDemoBeansByPageTest").param("page", "1").param("rows", "10")).andDo(print()).andExpect(status().isOk());
	 }
}
