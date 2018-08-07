package com.zyp.common;

import java.util.Properties;

import javax.sql.DataSource;

import io.swagger.annotations.ApiParam;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
 * 
 * 
 * *************************************************
 * 文件名：MyBatisConfig.java
 * 包名：com.zyp.common
 * 作者：  zyp
 * 简要描述:配置MyBatis
 * @version :1.0
 * 变更历史:
 * --------------------------------------------------
 * 序号      变更人     时间     变更原因
 * 1
 * 2
 * **************************************************
 */
@Configuration
@MapperScan(basePackages="com.zyp.dao")
public class MyBatisConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public DataSource getDataSource() throws Exception{
	    Properties props = new Properties();
	    props.put("driverClassName", env.getProperty("jdbc.driverClassName"));
	    props.put("url", env.getProperty("jdbc.url"));
	    props.put("username", env.getProperty("jdbc.username"));
	    props.put("password", env.getProperty("jdbc.password"));
	    return DruidDataSourceFactory.createDataSource(props);
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception{
	    SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
	    fb.setDataSource(ds);//指定数据源(这个必须有，否则报错)
	    //下边两句仅仅用于*.xml文件，如果整个持久层操作不需要使用到xml文件的话（只用注解就可以搞定），则不加
	    fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));//指定基包
	    fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//指定xml文件位置
	    return fb.getObject();
	}
	
	 /**
      * 配置事务管理器
      */
     @Bean
     @Primary
     public DataSourceTransactionManager transactionManager() throws Exception{
         return new DataSourceTransactionManager(getDataSource());
     }
	
}
