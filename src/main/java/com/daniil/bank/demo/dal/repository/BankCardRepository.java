package com.daniil.bank.demo.dal.repository;

import com.daniil.bank.demo.dal.entity.BankCard;
import com.daniil.bank.demo.enums.CARD_TYPE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankCardRepository extends JpaRepository<BankCard, Long> {


    BankCard findByCardTypeAndCardNumber(CARD_TYPE cardType, String cardNumber);

    BankCard findByCardNumber(String cardNumber);


}
