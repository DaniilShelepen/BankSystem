package com.daniil.bank.demo.services;

import com.daniil.bank.demo.dal.entity.legal.EntityUser;
import com.daniil.bank.demo.dal.entity.natural.IndividualUser;
import com.daniil.bank.demo.enums.CARD_TYPE;

public interface CardService {

    String createIndividualCard(IndividualUser individualUser, CARD_TYPE cardType, Integer bankAccNumber);


    String createEntityCard(EntityUser entityUser, IndividualUser individualUser, CARD_TYPE cardType, Integer bankAccNumber);


}
