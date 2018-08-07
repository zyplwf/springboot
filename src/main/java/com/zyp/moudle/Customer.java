package com.zyp.moudle;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * 
 * *************************************************
 * 文件名：Customer.java
 * 包名：com.zyp.moudle
 * 时间：2018年8月7日 
 * 作者：  zyp
 * 简要描述:实体对象
 * @version :1.0
 * 变更历史:
 * --------------------------------------------------
 * 序号      变更人     时间     变更原因
 * 1
 * 2
 * **************************************************
 */
@Document
public class Customer {

	@Id
	private String cid;
	private String firstname;
	private String secondname;

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSecondname() {
		return secondname;
	}

	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}

}
