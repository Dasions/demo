package commons.interceptors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageHelper;

public class AllRequestInterceptor implements HandlerInterceptor{

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse arg1, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		//手动清理 ThreadLocal 存储的分页参数
		PageHelper.clearPage();
		String page=request.getParameter("page");
		String rows=request.getParameter("rows");

		//当page和rows都是正整数时，设置分页参数
		if(page!=null&&rows!=null){
			if(isInteger(page)&&isInteger(rows)){
			PageHelper.startPage(Integer.parseInt(page), Integer.parseInt(rows));
			}
		}
		return true;
	}

	//判断是否是正整数
	  public static boolean isInteger(String input){  
	        Matcher mer = Pattern.compile("^[0-9]+$").matcher(input);  
	        return mer.find();  
	    }  
}
