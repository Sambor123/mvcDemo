package com.jrose.jrose.helper;

import java.util.Properties;

import com.jrose.jrose.AppConfig;
import com.jrose.jrose.utils.PropsUtil;

/**
 * 配置文件工具类，用于获取配置文件jrose.properties中的属性值
 * @author kumaha
 *
 */
public class ConfigHelper {
	/**
	 * 用于维护配置信息的Properties对象
	 */
	private static final Properties APP_CONFIG=PropsUtil.loadProps(AppConfig.CONFIG_FILE);
	
	/**
	 * 获取JDBC Driver
	 */
	public static String getJDBCDriver() {
		return PropsUtil.getString(APP_CONFIG, AppConfig.JDBC_DRIVER);
	}
	/**
	 * 获取JDBC URL
	 */
	public static String getJDBCUrl() {
		return PropsUtil.getString(APP_CONFIG, AppConfig.JDBC_URL);
	}
	/**
	 * 获取JDBC username
	 */
	public static String getJDBCUsername() {
		return PropsUtil.getString(APP_CONFIG, AppConfig.JDBC_USERNAME);
	}
	/**
	 * 获取JDBC password
	 */
	public static String getJDBCPassword() {
		return PropsUtil.getString(APP_CONFIG, AppConfig.JDBC_PASSWORD);
	}
	

	/**
	 * 获取Package基础包名
	 */
	public static String getPackageBase() {
		return PropsUtil.getString(APP_CONFIG, AppConfig.PACKAGR_BASE);
	}
	/**
	 * 获取Package Controlelr包名
	 */
	public static String getPackageController() {
		return PropsUtil.getString(APP_CONFIG, AppConfig.PACKAGE_CONTROLLER,getPackageBase()+".Controller");
	}
	/**
	 * 获取Package Service包名
	 */
	public static String getPackageService() {
		return PropsUtil.getString(APP_CONFIG, AppConfig.PACKAGE_SERVICE,getPackageBase()+".Service");
	}
	
	/**
	 * 获取view路径,提供默认值
	 */
	public static String getPathView() {
		return PropsUtil.getString(APP_CONFIG, AppConfig.PATH_VIEW,"/");
	}
	/**
	 * 获取static文件路径，提供默认值
	 */
	public static String getPathStatic() {
		return PropsUtil.getString(APP_CONFIG, AppConfig.PATH_STATIC,"/static/");
	}
	
	
}
