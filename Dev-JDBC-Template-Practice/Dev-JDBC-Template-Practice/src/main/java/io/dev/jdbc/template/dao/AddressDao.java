package io.dev.jdbc.template.dao;

import io.dev.jdbc.template.entity.Address;

public interface AddressDao {

	public Address saveAddress(Long custId, String city);
	
	public Address findByCustId(Long addressId);
	
	public void updateAddress(Address address);
}
