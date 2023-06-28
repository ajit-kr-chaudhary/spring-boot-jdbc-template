package io.dev.jdbc.template.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.dev.jdbc.template.model.CustomerRqst;
import io.dev.jdbc.template.model.CustomerRsp;
import io.dev.jdbc.template.model.UpdateCustRqst;
import io.dev.jdbc.template.spi.CustomerService;

@RestController
public class CustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@PostMapping(path = "/enrollCustomer", produces = "application/json")
	public ResponseEntity<CustomerRsp> enrollCustomer(@RequestBody CustomerRqst custRqst) {
		LOGGER.info("enrollCustomer()");
		return customerService.enrollCustomer(custRqst);
	}

	@GetMapping(path = "/getCustomerByPhone", produces = "application/json")
	public ResponseEntity<List<CustomerRsp>> getCustomerByPhone(@RequestParam String phone) {
		LOGGER.info("getCustomerByPhone()");
		return customerService.getCustomerByPhone(phone);
	}

	@GetMapping(path = "/getAllCustomers", produces = "application/json")
	public ResponseEntity<List<CustomerRsp>> getAllCustomer() {
		LOGGER.info("getAllCustomer()");
		return customerService.getAllCustomer();
	}

	@PutMapping(path = "/updateCustomers", produces = "application/json")
	public ResponseEntity<String> updateCustomer(@RequestBody UpdateCustRqst updateCustRqst) {
		LOGGER.info("updateCustomer()");
		return customerService.updateCustomer(updateCustRqst);
	}

}
