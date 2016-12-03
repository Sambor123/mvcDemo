package test;
import com.jrose.jrose.Annotation.Action;
import com.jrose.jrose.Annotation.Controller;
import com.jrose.jrose.Annotation.RequestMapping;
import com.jrose.jrose.bean.ModelAndView;
import com.jrose.jrose.bean.Param;

@Controller
public class test {
	@Action(path="/hello",method="post")
	public ModelAndView  hello(Param param) {
		ModelAndView mav=new ModelAndView("/result.jsp");
		mav.addObject("name", param.getString("name"));
		mav.addObject("pas", param.getString("pas"));
		return mav;
	}
	@Action(path="/hello2",method="get")
	public ModelAndView  hello2(Param param){
		ModelAndView mav =new ModelAndView("test.jsp");
		// TODO Auto-generated constructor stub
		return mav;
	}
	
}
