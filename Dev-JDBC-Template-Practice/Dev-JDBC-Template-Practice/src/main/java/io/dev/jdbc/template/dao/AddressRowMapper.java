package io.dev.jdbc.template.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.dev.jdbc.template.entity.Address;

public class AddressRowMapper implements RowMapper<Address> {

	@Override
	public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
		Address address = new Address();
		address.setAddressId(rs.getLong("adrs_id"));
		address.setCity(rs.getString("adrs_city"));
		return address;
	}

}
