package com.zyp.redis;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;


@Configuration
public class JedisClusterConifg {
	@Autowired
	private RedisProperties redisProperties;
	
	@Bean
	public JedisCluster getJedisCluster() {
	    String[] serverArray = redisProperties.getClusterNodes().split(",");//获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
	    Set<HostAndPort> nodes = new HashSet<HostAndPort>();
	
	    for (String ipPort : serverArray) {
	        String[] ipPortPair = ipPort.split(":");
	        nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
	    }
	    return new JedisCluster(nodes, redisProperties.getCommandTimeout(),redisProperties.getCommandTimeout(),1,"goldkeeper666888",new GenericObjectPoolConfig());
	}
	
}
