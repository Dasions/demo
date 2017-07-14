package demo.test.controller;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import redisdemo.controller.RedisDemoController;
import redisdemo.service.RedisDemoService;

//@ContextConfiguration(locations={"classpath*:/springmvc-servlet.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/resources/springmvc-servlet.xml")
public class RedisDemoControllerTest {
	private MockMvc mockMvc;
	@Mock
	RedisDemoService redisDemoService;
	@InjectMocks
	RedisDemoController redisDemoController;

	 @Before
	 public void setup() {
	     MockitoAnnotations.initMocks(this);
	     this.mockMvc = MockMvcBuilders.standaloneSetup(redisDemoController).build();
	 }
	 
	 @Test
	 public void testRedisDemoAdd() throws Exception{  
		 mockMvc.perform(post("/redisDemo/redisDemoAdd")).andDo(print()).andExpect(status().isOk());
	 }
	 
	 @Test
	 public void testRedisDemoGet() throws Exception{  
		 mockMvc.perform(post("/redisDemo/redisDemoGet")).andDo(print()).andExpect(status().isOk());
	 }
	 
	 @Test
	 public void testAddRedisTransactionsDemo() throws Exception{  
		 mockMvc.perform(post("/redisDemo/addRedisTransactionsDemo")).andDo(print()).andExpect(status().isOk());
	 }
	 
	 @Test
	 public void testGetRedisTransactionsDemo() throws Exception{  
		 mockMvc.perform(post("/redisDemo/getRedisTransactionsDemo")).andDo(print()).andExpect(status().isOk());
	 }
}
