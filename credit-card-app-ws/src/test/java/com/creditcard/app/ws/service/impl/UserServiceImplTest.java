package com.creditcard.app.ws.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.creditcard.app.ws.UserCreditCardRepository;
import com.creditcard.app.ws.exceptions.UserServiceException;
import com.creditcard.app.ws.io.entity.UserCreditCardEntity;
import com.creditcard.app.ws.shared.Utils;
import com.creditcard.app.ws.shared.dto.UserDto;
import com.creditcard.app.ws.validator.CreditCardValidator;

class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserCreditCardRepository userCreditCardRepository;

	@Mock
	CreditCardValidator cardValidator;

	@Mock
	Utils utils;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddCreditCard() throws Exception {
		UserDto userDto = new UserDto();
		userDto.setFirstName("Vidya");
		userDto.setLastName("D");
		userDto.setId(2L);
		userDto.setCardLimit(10L);
		userDto.setCardNumber("4386280031035813");

		UserCreditCardEntity entity = new UserCreditCardEntity();

		entity.setFirstName("Vidya");
		entity.setLastName("D");
		entity.setId(2L);
		entity.setCardLimit(10L);
		entity.setCardNumber("4386280031035813");
		entity.setUserId("gyhfkosdach");

		when(userCreditCardRepository.findByCardNumber(anyString())).thenReturn(null);
		when(cardValidator.validate(userDto)).thenReturn(true);
		when(utils.generateUserId(anyInt())).thenReturn("gyhfkosdach");
		when(userCreditCardRepository.save(any(UserCreditCardEntity.class))).thenReturn(entity);

		UserDto savedUser = userService.addCreditCard(userDto);

		assertNotNull(savedUser);
		assertEquals("Vidya", savedUser.getFirstName());
		assertNotNull(savedUser.getUserId());

	}

	@Test
	void testGetAllCreditCards() {

		UserCreditCardEntity user1 = new UserCreditCardEntity();
		user1.setCardLimit(10L);
		user1.setCardNumber("4386280031035813");
		user1.setFirstName("Shankar");
		user1.setLastName("Krishna");
		user1.setId(5L);
		user1.setUserId("ghtsujgfthdn");

		UserCreditCardEntity user2 = new UserCreditCardEntity();
		user2.setCardLimit(20L);
		user2.setCardNumber("4012888888881881");
		user2.setFirstName("Misal");
		user2.setLastName("Sharma");
		user2.setId(4L);
		user2.setUserId("okithbdhi");

		List<UserCreditCardEntity> creditCardList = new ArrayList<>();
		creditCardList.add(user1);
		creditCardList.add(user2);

		when(userCreditCardRepository.findAll()).thenReturn(creditCardList);

		List<UserDto> responseList = userService.getAllCreditCards();

		assertNotNull(responseList);
		assertEquals(2, responseList.size());
		assertEquals("Shankar", responseList.get(0).getFirstName());
		assertEquals("4012888888881881", responseList.get(1).getCardNumber());
	}

	@Test
	void testAddCreditCard_UserServiceException() {

		UserDto user = new UserDto();
		user.setCardLimit(10L);
		user.setCardNumber("4386280031035813");
		user.setFirstName("Shankar");
		user.setLastName("Krishna");
		user.setId(5L);
		user.setUserId("ghtsujgfthdn");

		UserCreditCardEntity user1 = new UserCreditCardEntity();
		user1.setCardLimit(10L);
		user1.setCardNumber("4386280031035813");
		user1.setFirstName("Shankar");
		user1.setLastName("Krishna");
		user1.setId(5L);
		user1.setUserId("ghtsujgfthdn");

		when(userCreditCardRepository.findByCardNumber(anyString())).thenReturn(user1);

		assertThrows(UserServiceException.class,

				() -> {
					userService.addCreditCard(user);
				}

		);

	}

	@Test
	void testAddCreditCard_UserServiceExceptionValidationError() {
		when(cardValidator.validate(any(UserDto.class))).thenReturn(false);
		
		UserDto user = new UserDto();
		user.setCardLimit(10L);
		user.setCardNumber("4386280031035813");
		user.setFirstName("Shankar");
		user.setLastName("Krishna");
		user.setId(5L);
		user.setUserId("ghtsujgfthdn");

		assertThrows(UserServiceException.class,

				() -> {
					userService.addCreditCard(user);
				});
	}

}
