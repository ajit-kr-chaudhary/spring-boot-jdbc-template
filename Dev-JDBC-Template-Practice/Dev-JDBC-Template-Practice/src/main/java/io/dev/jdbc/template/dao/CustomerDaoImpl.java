package io.dev.jdbc.template.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import io.dev.jdbc.template.entity.Customer;
import io.dev.jdbc.template.model.AddressRsp;
import io.dev.jdbc.template.model.CardRsp;
import io.dev.jdbc.template.model.CustomerRsp;

@Service
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final static String CUST_SEQUENCE = "select nextval('customer_id_seq')";

	@Override
	public Customer saveCustomer(String custName, String custPhone) {
		Long custId = getCustId();
		jdbcTemplate.update("insert into customer (cust_id, cust_name, cust_phone) values(?,?,?)",
				new Object[] { custId, custName, custPhone });
		Customer cust = findByCustId(custId);
		return cust;
	}

	public Long getCustId() {
		String custId = jdbcTemplate.query(CUST_SEQUENCE, result -> {
			if (result.next()) {
				return result.getString(1);
			} else {
				return null;
			}
		}, new Object[] {});
		return Long.valueOf(custId);
	}

	public List<Customer> findCustomerByPhone(String phone) {
		List<Customer> customerList = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList("SELECT * FROM customer WHERE cust_phone = ?",
				phone);
		for (Map row : rows) {
			Customer cust = new Customer();
			cust.setCustId(((Long) row.get("cust_id")).longValue());
			cust.setName((String) row.get("cust_name"));
			cust.setPhone((String) row.get("cust_phone"));

			customerList.add(cust);
		}

		return customerList;
	}

	public List<CustomerRsp> findAllCustomer() {
		List<CustomerRsp> listOfCust = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(
				"SELECT * FROM customer, address, card WHERE address.cust_id = customer.cust_id AND card.cust_id = customer.cust_id");
		for (Map row : rows) {
			CustomerRsp custRsp = new CustomerRsp();
			custRsp.setCustId(((Long) row.get("cust_id")).longValue());
			custRsp.setCustName((String) row.get("cust_name"));
			custRsp.setPhone((String) row.get("cust_phone"));

			AddressRsp addRsp = new AddressRsp();
			addRsp.setAddressId(((Long) row.get("adrs_id")).longValue());
			addRsp.setCity((String) row.get("adrs_city"));
			custRsp.setAddressRsp(addRsp);

			CardRsp cardRsp = new CardRsp();
			cardRsp.setCardId(((Long) row.get("card_id")).longValue());
			cardRsp.setCountry((String) row.get("card_country"));
			cardRsp.setScheme((String) row.get("card_scheme"));
			cardRsp.setType((String) row.get("card_type"));
			cardRsp.setBankName((String) row.get("card_bank_name"));
			cardRsp.setCardNumber((String) row.get("card_number"));
			custRsp.setCardRsp(cardRsp);

			listOfCust.add(custRsp);
		}

		return listOfCust;
	}

	@Override
	public Customer findByCustId(Long custId) {
		return jdbcTemplate.queryForObject("SELECT * FROM customer WHERE cust_id = ?", new CustomerRowMapper(), custId);
	}

	@Override
	public void updateCustomer(Customer cust) {
		jdbcTemplate.update("UPDATE customer SET cust_name = ?, cust_phone = ? WHERE cust_id = ?", new Object[] {cust.getName(), cust.getPhone(), cust.getCustId()});
	}
	
}
