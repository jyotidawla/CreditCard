package com.creditcard.app.ws.service;

import java.util.List;

import com.creditcard.app.ws.shared.dto.UserDto;

public interface UserService {
	UserDto addCreditCard(UserDto user) throws Exception;

	List<UserDto> getAllCreditCards();

}
