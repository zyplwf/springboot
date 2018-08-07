package com.zyp.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.MongoOptions;
import com.mongodb.ServerAddress;

/**
 * 
 * 
 * *************************************************
 * 文件名：MongoDBConfig.java
 * 包名：com.zyp.common
 * 时间：2018年8月7日 
 * 作者：  zyp
 * 简要描述:mongoDB配置类
 * @version :1.0
 * 变更历史:
 * --------------------------------------------------
 * 序号      变更人     时间     变更原因
 * 1
 * 2
 * **************************************************
 */
@Configuration
@EnableMongoRepositories(basePackages={"com.zyp"})
public class MongoDBConfig extends AbstractMongoConfiguration {

	@Autowired
    private Environment env;
	
	@Override
	protected String getDatabaseName() {
		return env.getProperty("mongo.name");
	}

	@Override
	public Mongo mongo() throws Exception {
		 ServerAddress serverAddress=new ServerAddress(env.getProperty("mongo.host"));
	     List<MongoCredential> credentials=new ArrayList<MongoCredential>();
	     credentials.add(MongoCredential.createCredential("root", "mydb", "123456".toCharArray()));
	     return new MongoClient(serverAddress, credentials);
	}

}
