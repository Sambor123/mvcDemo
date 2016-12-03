package com.jrose.jrose.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回视图-模型对象
 */
public class ModelAndView {

    /**
     * 视图路径
     */
    private String viewName;

    /**
     * 模型数据
     */
    private Map<String, Object> model;

    public ModelAndView(String viewName) {
        this.viewName = viewName;
        model = new HashMap<String, Object>();
    }

    public ModelAndView addObject(String key, Object value) {
        model.put(key, value);
        return this;
    }
    
    public String getViewName() {
        return viewName;
    }
    public void setViewName(String viewName){
    	this.viewName=viewName;
    }

    public Map<String, Object> getModel() {
        return model;
    }

	public Object getMap(String string) {
		// TODO Auto-generated method stub
		return model.get(string);
	}

	
    
    
}
