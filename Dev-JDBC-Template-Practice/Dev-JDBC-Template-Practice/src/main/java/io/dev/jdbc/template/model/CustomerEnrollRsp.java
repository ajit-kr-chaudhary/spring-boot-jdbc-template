package io.dev.jdbc.template.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerEnrollRsp {

	@JsonProperty("number")
	private Number number;
	
	@JsonProperty("scheme")
	private String scheme;
	
	@JsonProperty("type")
	private String type;
	
	@JsonProperty("brand")
	private String brand;
	
	@JsonProperty("prepaid")
	private Boolean prepaid;
	
	@JsonProperty("country")
	private Country country;
	
	@JsonProperty("bank")
	private Bank bank;

	public Number getNumber() {
		return number;
	}

	public void setNumber(Number number) {
		this.number = number;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Boolean getPrepaid() {
		return prepaid;
	}

	public void setPrepaid(Boolean prepaid) {
		this.prepaid = prepaid;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

}
