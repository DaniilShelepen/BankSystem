package com.daniil.bank.demo.services;

import com.daniil.bank.demo.dal.entity.legal.Entity;
import com.daniil.bank.demo.dal.entity.natural.Individual;
import com.daniil.bank.demo.enums.CARD_TYPE;

public interface CardService {

    String createIndividualCard(Individual individual, CARD_TYPE cardType, Integer bankAccNumber);


    String createEntityCard(Entity entity, Individual individual, CARD_TYPE cardType, Integer bankAccNumber);


}
