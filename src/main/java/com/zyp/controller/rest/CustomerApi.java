package com.zyp.controller.rest;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.zyp.mongodb.CustomerRepository;
import com.zyp.moudle.Customer;

@RestController
@RequestMapping("/customer")
@Api("USER的API")
public class CustomerApi {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@ApiOperation("增加一个Customer")
	@RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
	public Customer addCustomer(@RequestParam("firstname") String firstname,
	                            @RequestParam("secondname") String secondname) {
	    Customer customer = new Customer();
	    customer.setFirstname(firstname);
	    customer.setSecondname(secondname);
	    return customerRepository.save(customer);
	}
	
	@ApiOperation("获取所有的Customer")
	@RequestMapping(value = "/getAllCustomer", method = RequestMethod.GET)
	public List<Customer> getAllCustomer() {
	    return customerRepository.findAll();
	}
	
	@ApiOperation("根据firstname获取Customer")
	@RequestMapping(value = "/getCustomerByFirstname", method = RequestMethod.GET)
	public Customer getCustomerByFirstname(@RequestParam("firstname") String firstname) {
	    return customerRepository.findByFirstname(firstname);
	}
	
	@ApiOperation("根据secondname获取多个Customer")
	@RequestMapping(value = "/getCustomerBySecondname", method = RequestMethod.GET)
	public List<Customer> getCustomerBySecondname(@RequestParam("secondname") String secondname) {
	    return customerRepository.findBySecondname(secondname);
	}
	
	@ApiOperation("根据id删除Customer")
	@RequestMapping(value = "/deleteCustomerById", method = RequestMethod.GET)
	public boolean deleteCustomerById(@RequestParam("cid") String cid) {
	    customerRepository.delete(cid);
	    return true;
	}
	
	
	@ApiOperation("复杂的Customer查询")
	@RequestMapping(value = "/getAdminByNameAndSexOrAddress", method = RequestMethod.GET)
	public List<Customer> getCustomerSecondOrFirst(
									@RequestParam("firstname") String firstname,
									@RequestParam("secondname") String secondname) {
	    /**
	     * OR
	     */
	    BasicDBList orList = new BasicDBList(); //用于记录
	    if (firstname != null) {
	        orList.add(new BasicDBObject("firstname", firstname));
	    }
	    /**
	     * 完全匹配
	     * Pattern pattern = Pattern.compile("^name$", Pattern.CASE_INSENSITIVE);
	     * 右匹配
	     * Pattern pattern = Pattern.compile("^.*name$", Pattern.CASE_INSENSITIVE);
	     * 左匹配
	     * Pattern pattern = Pattern.compile("^name.*$", Pattern.CASE_INSENSITIVE);
	     * 模糊匹配
	     * Pattern pattern = Pattern.compile("^.*name8.*$", Pattern.CASE_INSENSITIVE);
	     * 												
	     *  
	     * http://www.what21.com/database/mongodb/mongodb-basis/java-query.html
	     */
	    if (StringUtils.isNotBlank(secondname)) {
	    	Pattern pattern = Pattern.compile("^.*"+secondname+".*$", Pattern.CASE_INSENSITIVE);
	        orList.add(new BasicDBObject("secondname", pattern));
	    }
	    BasicDBObject orDBObject = new BasicDBObject("$or", orList);
	    
	    /**
	     * and
	     */
	    /*BasicDBList andList = new BasicDBList();
	    andList.add(new BasicDBObject("name", name));
	    andList.add(orDBObject);
	    BasicDBObject andDBObject = new BasicDBObject("$and", andList);*/
	    return mongoTemplate.find(new BasicQuery(orDBObject), Customer.class, "customer");
	
	}
}
