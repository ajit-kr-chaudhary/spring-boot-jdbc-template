package io.dev.jdbc.template.dao;

import java.util.List;

import io.dev.jdbc.template.entity.Customer;
import io.dev.jdbc.template.model.CustomerRsp;

public interface CustomerDao {

	public Customer saveCustomer(String custName, String custPhone);
	
	public List<Customer> findCustomerByPhone(String phone);
	
	public List<CustomerRsp> findAllCustomer();
	
	public Customer findByCustId(Long custId);
	
	public void updateCustomer(Customer cust);
}
