package io.dev.jdbc.template.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Country {

	@JsonProperty("numeric")
	private String numeric;
	
	@JsonProperty("alpha2")
	private String alpha2;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("emoji")
	private String emoji;
	
	@JsonProperty("currency")
	private String currency;
	
	@JsonProperty("latitude")
	private Integer latitude;
	
	@JsonProperty("longitude")
	private Integer longitude;

	public String getNumeric() {
		return numeric;
	}

	public void setNumeric(String numeric) {
		this.numeric = numeric;
	}

	public String getAlpha2() {
		return alpha2;
	}

	public void setAlpha2(String alpha2) {
		this.alpha2 = alpha2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmoji() {
		return emoji;
	}

	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Integer getLatitude() {
		return latitude;
	}

	public void setLatitude(Integer latitude) {
		this.latitude = latitude;
	}

	public Integer getLongitude() {
		return longitude;
	}

	public void setLongitude(Integer longitude) {
		this.longitude = longitude;
	}

}
