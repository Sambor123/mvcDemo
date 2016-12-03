package com.jrose.jrose;

import com.jrose.jrose.core.BeanContainer;
import com.jrose.jrose.helper.ClassHelper;
import com.jrose.jrose.helper.ControllerHelper;
import com.jrose.jrose.helper.IocHelper;
import com.jrose.jrose.utils.ClassUtil;

/**
 * 加载相应的 Helper 类
 *
 */
public final class HelperLoader {

    public static void init() {
        Class<?>[] classList = {
            ClassHelper.class,
            BeanContainer.class,
            IocHelper.class,
            ControllerHelper.class
        };
        for (Class<?> cls : classList) {
            ClassUtil.loadClass(cls.getName());
        }
    }
}