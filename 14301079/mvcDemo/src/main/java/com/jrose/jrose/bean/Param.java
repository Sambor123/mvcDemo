package com.jrose.jrose.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;

import com.jrose.jrose.utils.CastUtil;
import com.jrose.jrose.utils.CollectionUtil;

/**
 * 请求参数对象
 *
 */
public class Param {

   private Map<String, Object> paramMap;
   
   public Param(Map<String, Object> paramMap){
	   this.paramMap=paramMap;
   }
    /**
     * 根据参数名获取 String 型参数值
     */
    public String getString(String name) {
        return CastUtil.castString(paramMap.get(name));
    }

    /**
     * 根据参数名获取 double 型参数值
     */
    public double getDouble(String name) {
        return CastUtil.castDouble(paramMap.get(name));
    }

    /**
     * 根据参数名获取 long 型参数值
     */
    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    /**
     * 根据参数名获取 int 型参数值
     */
    public int getInt(String name) {
        return CastUtil.castInt(paramMap.get(name));
    }

    /**
     * 根据参数名获取 boolean 型参数值
     */
    public boolean getBoolean(String name) {
        return CastUtil.castBoolean(paramMap.get(name));
    }
    /**
     * 获取所有字段
     */
    public Map<String, Object> getMap(){
    	return paramMap;
    }
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return MapUtils.isEmpty(paramMap);
	}
}
