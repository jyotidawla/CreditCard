package com.creditcard.app.ws.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditcard.app.ws.exceptions.UserServiceException;
import com.creditcard.app.ws.service.UserService;
import com.creditcard.app.ws.shared.dto.UserDto;
import com.creditcard.app.ws.ui.model.request.UserCreditCardDetailsRequestModel;
import com.creditcard.app.ws.ui.model.response.ErrorMessages;
import com.creditcard.app.ws.ui.model.response.UserCreditCardResponseModel;

@RestController
@RequestMapping("creditcards") // http://localhost:8080/creditcards
public class UserCreditCardController {

	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<UserCreditCardResponseModel> add(
			@RequestBody UserCreditCardDetailsRequestModel userCreditCard) throws Exception {

		ResponseEntity<UserCreditCardResponseModel> response;

		if (!StringUtils.hasLength(userCreditCard.getFirstName())
				|| !StringUtils.hasLength(userCreditCard.getLastName())
				|| !StringUtils.hasLength(userCreditCard.getCardNumber())) {
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());

		}

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userCreditCard, userDto);

		UserDto serviceResponse = userService.addCreditCard(userDto);

		UserCreditCardResponseModel responseModel = new UserCreditCardResponseModel();
		BeanUtils.copyProperties(serviceResponse, responseModel);
		response = ResponseEntity.status(HttpStatus.CREATED).body(responseModel);

		return response;
	}

	@GetMapping
	public ResponseEntity<List<UserCreditCardResponseModel>> getAll() {
		List<UserDto> cards = userService.getAllCreditCards();
		List<UserCreditCardResponseModel> responseList = new ArrayList<>();

		cards.forEach(item -> {
			UserCreditCardResponseModel responseModel = new UserCreditCardResponseModel();
			BeanUtils.copyProperties(item, responseModel);
			responseList.add(responseModel);

		});

		return ResponseEntity.status(HttpStatus.OK).body(responseList);
	}

}
