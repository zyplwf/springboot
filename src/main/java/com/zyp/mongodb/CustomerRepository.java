package com.zyp.mongodb;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.zyp.moudle.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
	
	public Customer findByFirstname(String firstname);
	
	public List<Customer> findBySecondname(String lastName);
}
