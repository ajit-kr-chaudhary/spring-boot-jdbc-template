package io.dev.jdbc.template.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import io.dev.jdbc.template.entity.Card;

@Service
public class CardDaoImpl implements CardDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final static String CARD_SEQUENCE = "select nextval('card_id_seq')";

	@Override
	public Card saveCard(Long custId, String country, String scheme, String type, String bankName, String cardNumber) {
		Long cardId = getCardId();
		jdbcTemplate.update(
				"insert into card (card_id, cust_id, card_country, card_scheme, card_type, card_bank_name, card_number) values(?,?,?,?,?,?,?)",
				new Object[] { cardId, custId, country, scheme, type, bankName, cardNumber });
		return findByCardId(cardId);
	}

	public Long getCardId() {
		String custId = jdbcTemplate.query(CARD_SEQUENCE, result -> {
			if (result.next()) {
				return result.getString(1);
			} else {
				return null;
			}
		}, new Object[] {});
		return Long.valueOf(custId);
	}

	private Card findByCardId(Long cardId) {
		return jdbcTemplate.queryForObject("SELECT * FROM card WHERE card_id = ?", new CardRowMapper(), cardId);
	}
	
	public Card findByCustId(Long cardId) {
		return jdbcTemplate.queryForObject("SELECT * FROM card WHERE card_id = ?", new CardRowMapper(), cardId);
	}

	@Override
	public void updateCard(Card card) {
		jdbcTemplate.update("UPDATE card SET card_country = ? WHERE card_id = ?", new Object[] {card.getCountry(), card.getCardId()});
		
	}
}
