package com.creditcard.app.ws.shared;

import org.springframework.stereotype.Component;

/*
 * Validation of credit card numbers using luhn check.
 */
@Component
public class LuhnCheckDigit {

	public boolean isLuhnValid(String cardNumber) {
		int sum = 0;
		boolean secondDigit = false;
		for (int i = cardNumber.length() - 1; i >= 0; i--) {
			int digit = (int) cardNumber.charAt(i) - 48;
			if (secondDigit) {
				digit *= 2;
				sum += digit % 10 + digit / 10;
				secondDigit = false;
			} else {
				sum += digit;
				secondDigit = true;
			}
		}
		return sum % 10 == 0;
	}

}
