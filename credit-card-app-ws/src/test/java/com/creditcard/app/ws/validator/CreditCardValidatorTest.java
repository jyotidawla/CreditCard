package com.creditcard.app.ws.validator;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.creditcard.app.ws.exceptions.UserServiceException;
import com.creditcard.app.ws.shared.LuhnCheckDigit;
import com.creditcard.app.ws.shared.dto.UserDto;

class CreditCardValidatorTest {

	@InjectMocks
	CreditCardValidator validator;

	@Mock
	LuhnCheckDigit luhnCheckDigit;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testValidateCardNumber() {
		UserDto user1 = new UserDto();
		user1.setCardLimit(10L);
		user1.setCardNumber("4386280031035813");
		user1.setFirstName("Shankar");
		user1.setLastName("Krishna");
		user1.setId(5L);
		user1.setUserId("ghtsujgfthdn");

		when(luhnCheckDigit.isLuhnValid(anyString())).thenReturn(true);
		assertTrue(validator.validateCardNumber(user1));

	}

	@Test
	void testValidateCardDetails() {
		UserDto user1 = new UserDto();
		user1.setCardLimit(10L);
		user1.setCardNumber("4386280031035813");
		user1.setFirstName("Shankar");
		user1.setLastName("Krishna");
		user1.setId(5L);
		user1.setUserId("ghtsujgfthdn");

		assertTrue(validator.validateCardDetails(user1));
	}

	@Test
	void testAreCharactersVaild() {
		assertTrue(validator.areCharactersValid("4386280031035813"));
		assertFalse(validator.areCharactersValid("4386280aa031035813"));
	}

	@Test
	void testValidate() {
		UserDto user1 = new UserDto();
		user1.setCardLimit(10L);
		user1.setCardNumber("4386280031035813");
		user1.setFirstName("Shankar");
		user1.setLastName("Krishna");
		user1.setId(5L);
		user1.setUserId("ghtsujgfthdn");

		when(luhnCheckDigit.isLuhnValid(anyString())).thenReturn(true);

		assertTrue(validator.validate(user1));

	}

	@Test
	void testValidateCardDetails_UserServiceException() {
		UserDto user1 = new UserDto();
		user1.setCardLimit(10L);
		user1.setCardNumber("4386280abcde");
		user1.setFirstName("Shankar");
		user1.setLastName("Krishna");
		user1.setId(5L);
		user1.setUserId("ghtsujgfthdn");
		
		assertThrows(UserServiceException.class, 
				
				()->{
					validator.validateCardDetails(user1);
				}
				);
	}

}
