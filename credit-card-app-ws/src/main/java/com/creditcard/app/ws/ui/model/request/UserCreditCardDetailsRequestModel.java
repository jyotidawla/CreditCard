package com.creditcard.app.ws.ui.model.request;

/*
 * Request Model.
 */
public class UserCreditCardDetailsRequestModel {

	private String firstName;
	private String lastName;
	private String cardNumber;
	private long cardLimit;

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
