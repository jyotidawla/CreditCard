package com.creditcard.app.ws;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.creditcard.app.ws.io.entity.UserCreditCardEntity;

@Repository
public interface UserCreditCardRepository extends CrudRepository<UserCreditCardEntity, Long> {

	UserCreditCardEntity findByCardNumber(String cardNumber);

}
