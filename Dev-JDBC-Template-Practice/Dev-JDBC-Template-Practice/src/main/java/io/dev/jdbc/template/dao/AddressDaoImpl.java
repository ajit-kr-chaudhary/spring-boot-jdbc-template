package io.dev.jdbc.template.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import io.dev.jdbc.template.entity.Address;

@Service
public class AddressDaoImpl implements AddressDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final static String ADDRS_SEQUENCE = "select nextval('address_id_seq')";

	@Override
	public Address saveAddress(Long custId, String city) {
		Long addressId = getAddressId();
		jdbcTemplate.update("insert into address (adrs_id, cust_id, adrs_city) values(?,?,?)",
				new Object[] { addressId, custId, city });
		return findByAddressId(addressId);
	}

	public Long getAddressId() {
		String custId = jdbcTemplate.query(ADDRS_SEQUENCE, result -> {
			if (result.next()) {
				return result.getString(1);
			} else {
				return null;
			}
		}, new Object[] {});
		return Long.valueOf(custId);
	}

	@Override
	public Address findByCustId(Long custId) {
		return jdbcTemplate.queryForObject("SELECT * FROM address WHERE cust_id = ?", new AddressRowMapper(),
				custId);
	}

	private Address findByAddressId(Long addressId) {
		return jdbcTemplate.queryForObject("SELECT * FROM address WHERE adrs_id = ?", new AddressRowMapper(),
				addressId);
	}

	@Override
	public void updateAddress(Address address) {
		jdbcTemplate.update("UPDATE address SET adrs_city = ? WHERE adrs_id = ?", new Object[] {address.getCity(), address.getAddressId()});
	}

}
