package io.dev.jdbc.template.dao;

import io.dev.jdbc.template.entity.Card;

public interface CardDao {

	public Card saveCard(Long custId, String customer, String scheme, String type, String bankName, String cardNumber);
	
	public Card findByCustId(Long cardId);
	
	public void updateCard(Card card); 
}
