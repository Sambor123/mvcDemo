package com.jrose.jrose.bean;

/**
 * 返回数据对象
 *
 * @author huangyong
 * @since 1.0.0
 */
public class JsonData {

    /**
     * 模型数据
     */
    private Object model;

    public JsonData(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
