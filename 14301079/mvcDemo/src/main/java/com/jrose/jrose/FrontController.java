package com.jrose.jrose;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jrose.jrose.bean.Handler;
import com.jrose.jrose.bean.JsonData;
import com.jrose.jrose.bean.Param;
import com.jrose.jrose.bean.ModelAndView;
import com.jrose.jrose.core.BeanContainer;
import com.jrose.jrose.helper.ClassHelper;
import com.jrose.jrose.helper.ConfigHelper;
import com.jrose.jrose.helper.ControllerHelper;
import com.jrose.jrose.utils.ArrayUtil;
import com.jrose.jrose.utils.CodecUtil;
import com.jrose.jrose.utils.JsonUtil;
import com.jrose.jrose.utils.ReflectionUtil;
import com.jrose.jrose.utils.StreamUtil;
import com.jrose.jrose.utils.StringUtil;
/**
 * 请求转发器
 *
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class FrontController extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
    	//初始化Bean
        HelperLoader.init();
        ServletContext servletContext = servletConfig.getServletContext();
        registerServlet(servletContext);
    }

    private void registerServlet(ServletContext servletContext) {
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping("/index.jsp");
        //jspServlet.addMapping("/result.jsp");
        jspServlet.addMapping(ConfigHelper.getPathView() + "*");

        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping("/favicon.ico");
        defaultServlet.addMapping(ConfigHelper.getPathStatic() + "*");
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestMethod = request.getMethod().toLowerCase();
            String requestPath = request.getPathInfo();
            //获取Action处理器
            Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
            if (handler != null) {
            	//获取controlle的bean实例
                Class<?> controllerClass = handler.getController();
                Object controllerBean = BeanContainer.getBean(controllerClass);
                //创建请求参数
                Map<String, Object> paramMap=new HashMap<String, Object>();
                Enumeration<String> paramNames=request.getParameterNames();
                while (paramNames.hasMoreElements()) {
					String paramName = (String) paramNames.nextElement();
					String paramValue=request.getParameter(paramName);
					paramMap.put(paramName, paramValue);
					
				}
                String body=CodecUtil.decodeURL(StreamUtil.getString(request.getInputStream()));
                if (StringUtil.isNotEmpty(body)) {
					String[] params=StringUtil.splitString(body,"&");
					if (ArrayUtil.isNotEmpty(params)) {
						for (String param:params) {
							String[] array=StringUtil.splitString(body,"=");
							if (ArrayUtil.isNotEmpty(array)&&array.length==2) {
								String paramName=array[0];
								String paramValue=array[1];
								paramMap.put(paramName, paramValue);							}
						}
					}
				}
                Param param=new Param(paramMap);
                //调用Action方法
                Method action=handler.getAction();
                Object result;
                System.out.println(action.getName()+":"+controllerBean);
                if (param.isEmpty()) {
                    result = ReflectionUtil.invokeMethod(controllerBean, action);
                } else {
                    result = ReflectionUtil.invokeMethod(controllerBean, action, param);
                }

                if (result instanceof ModelAndView) {
                    handleViewAndModelResult((ModelAndView) result, request, response);
                } else if (result instanceof JsonData) {
                    handleDataResult((JsonData) result, response);
                }
            }else{
            	
            }
    }

    private void handleViewAndModelResult(ModelAndView viewAndModel, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	String path = viewAndModel.getViewName();
        if (StringUtil.isNotEmpty(path)) {
            if (path.startsWith("/")) {
                response.sendRedirect(request.getContextPath() + path);
            } else {
                Map<String, Object> model = viewAndModel.getModel();
                for (Map.Entry<String, Object> entry : model.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
                request.getRequestDispatcher(ConfigHelper.getPathView() + path).forward(request, response);
            }
        }
    }

    private void handleDataResult(JsonData jsonData, HttpServletResponse response) throws IOException {
        Object model = jsonData.getModel();
        if (model != null) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            String json = JsonUtil.toJson(model);
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }
}
