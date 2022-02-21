package com.creditcard.app.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.creditcard.app.ws.UserCreditCardRepository;
import com.creditcard.app.ws.exceptions.UserServiceException;
import com.creditcard.app.ws.io.entity.UserCreditCardEntity;
import com.creditcard.app.ws.service.UserService;
import com.creditcard.app.ws.shared.Utils;
import com.creditcard.app.ws.shared.dto.UserDto;
import com.creditcard.app.ws.ui.model.response.ErrorMessages;
import com.creditcard.app.ws.validator.CreditCardValidator;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserCreditCardRepository userCreditCardRepository;

	@Autowired
	CreditCardValidator cardValidator;

	@Autowired
	Utils utils;

	@Override
	public UserDto addCreditCard(UserDto user) throws Exception {
		UserDto returnValue = new UserDto();

		if (userCreditCardRepository.findByCardNumber(user.getCardNumber()) != null) {
			throw new UserServiceException(ErrorMessages.ALREADY_EXISTS.getErrorMessage());
		}

		if (cardValidator.validate(user)) {

			UserCreditCardEntity userCreditCardEntity = new UserCreditCardEntity();
			BeanUtils.copyProperties(user, userCreditCardEntity);

			String publicUserId = utils.generateUserId(30);
			userCreditCardEntity.setUserId(publicUserId);

			UserCreditCardEntity userSavedDetails = userCreditCardRepository.save(userCreditCardEntity);

			BeanUtils.copyProperties(userSavedDetails, returnValue);
		} else {

			throw new UserServiceException(ErrorMessages.VALIDATION_ERROR.getErrorMessage());
		}

		return returnValue;
	}

	@Override
	public List<UserDto> getAllCreditCards() {
		Iterable<UserCreditCardEntity> creditCards = userCreditCardRepository.findAll();

		List<UserDto> dtoCreditCardsList = new ArrayList<>();

		creditCards.forEach(item -> {
			String cardNumber = item.getCardNumber();
			if (StringUtils.hasLength(cardNumber)) {
				UserDto userDto = new UserDto();
				BeanUtils.copyProperties(item, userDto);
				dtoCreditCardsList.add(userDto);
			}
		});
		return dtoCreditCardsList;
	}

}
