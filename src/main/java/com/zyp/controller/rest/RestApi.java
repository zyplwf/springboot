package com.zyp.controller.rest;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zyp.common.MyConstants;
import com.zyp.moudle.User;
import com.zyp.redis.MyRedisTemplate;
import com.zyp.service.UserService;


@RestController
@RequestMapping("/zyp")
@Api(value="USER的API")
public class RestApi {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MyRedisTemplate myRedisTemplate;
	
	
	/*@RequestMapping("/hello")
    public JSONObject greeting(@RequestParam(value="name", defaultValue="World") String name) {  
		System.err.println("Hello"+name);
		String text = "{'animals':{'dog':[{'name':'Rufus','breed':'labrador','count':1,'twoFeet':false},{'name':'Marty','breed':'whippet','count':1,'twoFeet':false}],'cat':{'name':'Matilda'}}}";
		return  JSONObject.parseObject(text);  
    }*/
	
	@RequestMapping(value="/user",method=RequestMethod.GET)
    public String getUser(@RequestParam(value="name", defaultValue="World") String name) {  
		User user = userService.getUser(name);
		return  JSONObject.toJSONString(user);  
    }
	
	
	@ApiOperation("保存并获取redis集群中的信息")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query",name="username",dataType="String",required=true,value="用户的名称")
	})
	@ApiResponses({
		 @ApiResponse(code=400,message="请求参数没填好"),
		 @ApiResponse(code=404,message="请求路径没有或页面跳转路径不对"),
	})
	@RequestMapping(value="/testJedisCluster",method=RequestMethod.GET)
	public User testJedisCluster(@RequestParam("username") String username){
	    String value =  myRedisTemplate.get(MyConstants.USER_FORWARD_CACHE_PREFIX, username);
	    if(StringUtils.isBlank(value)){
	    	System.err.println(JSON.toJSONString(getUser(username)));
	        myRedisTemplate.set(MyConstants.USER_FORWARD_CACHE_PREFIX, username, getUser(username));
	        return null;
	    }
	    return JSON.parseObject(value, User.class);
	}
	
}
