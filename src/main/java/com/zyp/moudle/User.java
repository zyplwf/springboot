package com.zyp.moudle;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * *************************************************
 * 文件名：User.java
 * 包名：com.zyp.moudle
 * 时间：2018年8月7日 
 * 作者：  zyp
 * 简要描述:用户对象
 * @version :1.0
 * 变更历史:
 * --------------------------------------------------
 * 序号      变更人     时间     变更原因
 * 1
 * 2
 * **************************************************
 */
@Component
@ConfigurationProperties(prefix="user")
public class User {
	private Integer id;
	private String name;
	private String passWord;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
}
