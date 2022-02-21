package com.creditcard.app.ws.validator;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.creditcard.app.ws.exceptions.UserServiceException;
import com.creditcard.app.ws.shared.LuhnCheckDigit;
import com.creditcard.app.ws.shared.dto.UserDto;
import com.creditcard.app.ws.ui.model.response.ErrorMessages;

/**
 * 
 * Credit Card Object Validation.
 *
 */
@Component
public class CreditCardValidator {

	@Autowired
	LuhnCheckDigit luhnCheckDigit;

	public boolean validateCardDetails(UserDto user) {

		if (!(Objects.nonNull(user.getFirstName()) && Objects.nonNull(user.getLastName())
				&& user.getCardNumber().length() <= 19 && areCharactersValid(user.getCardNumber()))) {
			throw new UserServiceException(ErrorMessages.INVALID_REQUEST.getErrorMessage());
		}
		return true;
	}

	public boolean areCharactersValid(String cardNumber) {
		Pattern numerics = Pattern.compile("\\d*");
		Matcher source = numerics.matcher(cardNumber);
		return source.matches();
	}

	public boolean validate(UserDto user) {
		return validateCardDetails(user) && validateCardNumber(user);
	}

	public boolean validateCardNumber(UserDto user) {
		String creditCardNumber = user.getCardNumber();
		return luhnCheckDigit.isLuhnValid(creditCardNumber);
	}

}
