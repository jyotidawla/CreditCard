package com.creditcard.app.ws.io.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "credit_cards")
public class UserCreditCardEntity implements Serializable {

	private static final long serialVersionUID = 3862636538866773192L;

	@Id
	@GeneratedValue
	private long Id;

	@Column(nullable = false)
	private String userId;

	@Column(nullable = false, length = 50)
	private String firstName;

	@Column(nullable = false, length = 50)
	private String lastName;

	@Column(nullable = false, length = 19, unique = true)
	private String cardNumber;

	@Column(nullable = false)
	private long balance;

	@Column(nullable = false)
	private long cardLimit;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public long getCardLimit() {
		return cardLimit;
	}

	public void setCardLimit(long cardLimit) {
		this.cardLimit = cardLimit;
	}

}
