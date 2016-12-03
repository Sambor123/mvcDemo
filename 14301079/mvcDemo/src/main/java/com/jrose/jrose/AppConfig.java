package com.jrose.jrose;
/**
 * 框架配置常量接口
 * @author kumaha
 *
 */
public interface AppConfig {
	String CONFIG_FILE = "jrose.properties";

    String JDBC_DRIVER = "jrose.jdbc.driver";
    String JDBC_URL = "jrose.jdbc.url";
    String JDBC_USERNAME = "jrose.jdbc.url.username";
    String JDBC_PASSWORD = "jrose.jdbc.url.password";

    String PACKAGR_BASE = "jrose.package.base";
    String PACKAGE_CONTROLLER = "jrose.package.controller";
    String PACKAGE_SERVICE = "jrose.package.service";
    
    String PATH_VIEW = "jrose.path.view";
    String PATH_STATIC="jrose.path.static";
}
