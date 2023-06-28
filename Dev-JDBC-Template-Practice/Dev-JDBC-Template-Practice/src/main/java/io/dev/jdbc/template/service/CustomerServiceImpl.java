package io.dev.jdbc.template.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.dev.jdbc.template.dao.AddressDao;
import io.dev.jdbc.template.dao.CardDao;
import io.dev.jdbc.template.dao.CustomerDao;
import io.dev.jdbc.template.entity.Address;
import io.dev.jdbc.template.entity.Card;
import io.dev.jdbc.template.entity.Customer;
import io.dev.jdbc.template.model.AddressRsp;
import io.dev.jdbc.template.model.CardRsp;
import io.dev.jdbc.template.model.CustomerEnrollRsp;
import io.dev.jdbc.template.model.CustomerRqst;
import io.dev.jdbc.template.model.CustomerRsp;
import io.dev.jdbc.template.model.UpdateCustRqst;
import io.dev.jdbc.template.spi.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private CardDao cardDao;

	@Autowired
	private RestTemplate restTemplate;

	private final static String URI = "https://lookup.binlist.net/";

	@Autowired
	private ObjectMapper objMapper;

	@Override
	public ResponseEntity<CustomerRsp> enrollCustomer(CustomerRqst custRqst) {
		LOGGER.info(">> enrollCustomer()");
		String url = URI + custRqst.getCardNumber();
		String result = restTemplate.getForObject(url, String.class);
		CustomerEnrollRsp custEnrollRsp = null;
		try {
			custEnrollRsp = objMapper.readValue(result, CustomerEnrollRsp.class);
		} catch (JsonProcessingException e) {
			LOGGER.error("Error During Mapping: ", e);
			throw new RuntimeException(e);
		}
		CustomerRsp custRsp = new CustomerRsp();
		if (custEnrollRsp != null) {
			Customer cust = customerDao.saveCustomer(custRqst.getName(), custRqst.getPhone());
			custRsp.setCustId(cust.getCustId());
			custRsp.setCustName(cust.getName());
			custRsp.setPhone(cust.getPhone());
			if (cust != null) {
				Address address = addressDao.saveAddress(cust.getCustId(), custEnrollRsp.getBank().getCity());
				AddressRsp addRsp = new AddressRsp();
				addRsp.setAddressId(address.getAddressId());
				addRsp.setCity(address.getCity());
				custRsp.setAddressRsp(addRsp);

				Card card = cardDao.saveCard(cust.getCustId(), custEnrollRsp.getCountry().getName(),
						custEnrollRsp.getScheme(), custEnrollRsp.getType(), custEnrollRsp.getBank().getName(),
						custRqst.getCardNumber());
				CardRsp cardRsp = new CardRsp();
				cardRsp.setCardId(card.getCardId());
				cardRsp.setCountry(card.getCountry());
				cardRsp.setScheme(card.getScheme());
				cardRsp.setType(card.getType());
				cardRsp.setBankName(card.getBankName());
				cardRsp.setCardNumber(card.getCardNumber());
				custRsp.setCardRsp(cardRsp);
			}

		}
		LOGGER.info("<< enrollCustomer()");
		return new ResponseEntity<CustomerRsp>(custRsp, HttpStatus.CREATED);
	}

	public ResponseEntity<List<CustomerRsp>> getCustomerByPhone(String phone) {
		LOGGER.info(">> getCustomerByPhone()");
		List<CustomerRsp> custRspList = new ArrayList<>();
		List<Customer> custList = customerDao.findCustomerByPhone(phone);
		for (Customer cust : custList) {
			Address address = addressDao.findByCustId(cust.getCustId());
			Card card = cardDao.findByCustId(cust.getCustId());

			CustomerRsp custRsp = new CustomerRsp();
			custRsp.setCustId(cust.getCustId());
			custRsp.setCustName(cust.getName());
			custRsp.setPhone(cust.getPhone());

			AddressRsp addRsp = new AddressRsp();
			addRsp.setAddressId(address.getAddressId());
			addRsp.setCity(address.getCity());
			custRsp.setAddressRsp(addRsp);

			CardRsp cardRsp = new CardRsp();
			cardRsp.setCardId(card.getCardId());
			cardRsp.setCountry(card.getCountry());
			cardRsp.setScheme(card.getScheme());
			cardRsp.setType(card.getType());
			cardRsp.setBankName(card.getBankName());
			cardRsp.setCardNumber(card.getCardNumber());
			custRsp.setCardRsp(cardRsp);

			custRspList.add(custRsp);
		}
		LOGGER.info("<< getCustomerByPhone()");
		return new ResponseEntity<List<CustomerRsp>>(custRspList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<CustomerRsp>> getAllCustomer() {
		LOGGER.info(">> getAllCustomer()");
		List<CustomerRsp> custRspList = customerDao.findAllCustomer();
		LOGGER.info("<< getAllCustomer()");
		return new ResponseEntity<List<CustomerRsp>>(custRspList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> updateCustomer(UpdateCustRqst updateCustRqst) {
		LOGGER.info(">> updateCustomer()");
		String stringRsp = null;
		Customer cust = customerDao.findByCustId(updateCustRqst.getCustId());
		if (cust != null) {
			if (updateCustRqst.getName() != null) {
				cust.setName(updateCustRqst.getName());
				customerDao.updateCustomer(cust);
			}
			if (updateCustRqst.getCity() != null) {
				Address address = addressDao.findByCustId(cust.getCustId());
				address.setCity(updateCustRqst.getCity());
				addressDao.updateAddress(address);
			}
			if (updateCustRqst.getCountry() != null) {
				Card card = cardDao.findByCustId(cust.getCustId());
				card.setCountry(updateCustRqst.getCountry());
				cardDao.updateCard(card);
			}
			stringRsp = "Customer data get updated.";
		} else {
			stringRsp = "Customer not available.";
			return new ResponseEntity<String>(stringRsp, HttpStatus.NOT_FOUND);
		}
		LOGGER.info("<< updateCustomer()");
		return new ResponseEntity<String>(stringRsp, HttpStatus.OK);
	}

}
