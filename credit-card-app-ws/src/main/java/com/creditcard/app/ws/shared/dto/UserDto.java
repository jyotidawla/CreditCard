package com.creditcard.app.ws.shared.dto;

import java.io.Serializable;

/*
 * Model shared between  different layers of the application.
 */
public class UserDto implements Serializable {

	private static final long serialVersionUID = 5036684608772891201L;
	private long id;
	private String userId;
	private String firstName;
	private String lastName;
	private String cardNumber;
	private long cardLimit;
	private long balance;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

}
