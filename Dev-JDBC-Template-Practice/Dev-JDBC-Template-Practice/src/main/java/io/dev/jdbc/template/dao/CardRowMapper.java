package io.dev.jdbc.template.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.dev.jdbc.template.entity.Card;

public class CardRowMapper implements RowMapper<Card> {

	@Override
	public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
		Card card = new Card();
		card.setCardId(rs.getLong("card_id"));
		card.setCountry(rs.getString("card_country"));
		card.setScheme(rs.getString("card_scheme"));
		card.setType(rs.getString("card_type"));
		card.setBankName(rs.getString("card_bank_name"));
		card.setCardNumber(rs.getString("card_number"));
		return card;
	}

}
