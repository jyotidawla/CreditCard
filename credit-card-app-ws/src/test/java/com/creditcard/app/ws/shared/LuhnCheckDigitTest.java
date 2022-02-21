package com.creditcard.app.ws.shared;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class LuhnCheckDigitTest {

	@InjectMocks
	LuhnCheckDigit luhnCheckDigit;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testIsLuhnValid() {
		assertTrue(luhnCheckDigit.isLuhnValid("4386280031035813"));
		assertFalse(luhnCheckDigit.isLuhnValid("43862800310358139099"));

	}

}
