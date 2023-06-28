package io.dev.jdbc.template.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Number {

	@JsonProperty("length")
	private Integer length;
	
	@JsonProperty("luhn")
	private Boolean luhn;

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Boolean getLuhn() {
		return luhn;
	}

	public void setLuhn(Boolean luhn) {
		this.luhn = luhn;
	}

}
