package com.zyp.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * 
 * *************************************************
 * 文件名：ApplicationRun.java
 * 包名：com.zyp.boot
 * 时间：2018年8月7日 
 * 模块名：springboot启动类
 * 作者：  zyp
 * @version :1.0
 * 变更历史:
 * --------------------------------------------------
 * 序号      变更人     时间     变更原因
 * 1
 * 2
 * **************************************************
 */
@Configuration
@ComponentScan(basePackages="com.zyp")
@EnableAutoConfiguration
@EnableSwagger2
public class ApplicationRun {
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationRun.class, args);
	}
} 
