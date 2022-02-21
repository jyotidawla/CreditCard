package com.creditcard.app.ws.ui.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.creditcard.app.ws.exceptions.UserServiceException;
import com.creditcard.app.ws.service.impl.UserServiceImpl;
import com.creditcard.app.ws.shared.dto.UserDto;
import com.creditcard.app.ws.ui.model.request.UserCreditCardDetailsRequestModel;
import com.creditcard.app.ws.ui.model.response.UserCreditCardResponseModel;

class UserCreditCardControllerTest {

	@InjectMocks
	UserCreditCardController controller;

	@Mock
	UserServiceImpl userService;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAdd() throws Exception {

		UserCreditCardDetailsRequestModel requestModel = new UserCreditCardDetailsRequestModel();

		requestModel.setFirstName("Vidya");
		requestModel.setLastName("D");
		requestModel.setCardNumber("4386280031035813");
		requestModel.setCardLimit(10L);

		UserDto userDto = new UserDto();
		userDto.setFirstName("Vidya");
		userDto.setLastName("D");
		userDto.setId(2L);
		userDto.setCardLimit(10L);
		userDto.setCardNumber("4386280031035813");
		userDto.setUserId("ghbftyduj");

		when(userService.addCreditCard(any(UserDto.class))).thenReturn(userDto);

		ResponseEntity<UserCreditCardResponseModel> response = controller.add(requestModel);

		assertNotNull(response);
		assertEquals("Vidya", response.getBody().getFirstName());
		assertNotNull(response.getBody().getUserId());

	}

	@Test
	void testGetAll() {
		UserDto user1 = new UserDto();
		user1.setCardLimit(10L);
		user1.setCardNumber("4386280031035813");
		user1.setFirstName("Shankar");
		user1.setLastName("Krishna");
		user1.setId(5L);
		user1.setUserId("ghtsujgfthdn");

		UserDto user2 = new UserDto();
		user2.setCardLimit(20L);
		user2.setCardNumber("4012888888881881");
		user2.setFirstName("Misal");
		user2.setLastName("Sharma");
		user2.setId(4L);
		user2.setUserId("okithbdhi");

		List<UserDto> creditCardList = new ArrayList<>();
		creditCardList.add(user1);
		creditCardList.add(user2);

		when(userService.getAllCreditCards()).thenReturn(creditCardList);

		ResponseEntity<List<UserCreditCardResponseModel>> responseList = controller.getAll();

		assertNotNull(responseList);
		assertEquals(2, responseList.getBody().size());
		assertEquals("Krishna", responseList.getBody().get(0).getLastName());

	}

	@Test
	void testAdd_UserServiceException() throws Exception {

		UserCreditCardDetailsRequestModel requestModel = new UserCreditCardDetailsRequestModel();
		requestModel.setFirstName("Rosy");
		requestModel.setLastName("Thomas");
		requestModel.setCardNumber("");
		requestModel.setCardLimit(10L);

		assertThrows(UserServiceException.class,

				() -> {
					controller.add(requestModel);

				});
	}

}
