package io.dev.jdbc.template.spi;

import java.util.List;

import org.springframework.http.ResponseEntity;

import io.dev.jdbc.template.model.CustomerRqst;
import io.dev.jdbc.template.model.CustomerRsp;
import io.dev.jdbc.template.model.UpdateCustRqst;

public interface CustomerService {

	public ResponseEntity<CustomerRsp> enrollCustomer(CustomerRqst custRqst);
	
	public ResponseEntity<List<CustomerRsp>> getCustomerByPhone(String phone);
	
	public ResponseEntity<List<CustomerRsp>> getAllCustomer();
	
	public ResponseEntity<String> updateCustomer(UpdateCustRqst updateCustRqst);
}
