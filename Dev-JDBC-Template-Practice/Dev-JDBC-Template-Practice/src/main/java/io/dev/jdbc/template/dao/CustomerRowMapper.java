package io.dev.jdbc.template.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.dev.jdbc.template.entity.Customer;

public class CustomerRowMapper implements RowMapper<Customer> {

	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer cust = new Customer();
		cust.setCustId(rs.getLong("cust_id"));
		cust.setName(rs.getString("cust_name"));
		cust.setPhone(rs.getString("cust_phone"));
		return cust;
	}

}
