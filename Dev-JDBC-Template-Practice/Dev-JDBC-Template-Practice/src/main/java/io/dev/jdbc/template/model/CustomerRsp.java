package io.dev.jdbc.template.model;

public class CustomerRsp {

	private Long custId;
	private String custName;
	private String phone;
	private AddressRsp addressRsp;
	private CardRsp cardRsp;

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public AddressRsp getAddressRsp() {
		return addressRsp;
	}

	public void setAddressRsp(AddressRsp addressRsp) {
		this.addressRsp = addressRsp;
	}

	public CardRsp getCardRsp() {
		return cardRsp;
	}

	public void setCardRsp(CardRsp cardRsp) {
		this.cardRsp = cardRsp;
	}

}
