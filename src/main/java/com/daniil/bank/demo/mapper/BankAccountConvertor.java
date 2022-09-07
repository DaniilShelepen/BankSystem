package com.daniil.bank.demo.mapper;

import com.daniil.bank.demo.dal.entity.BankAccount;
import com.daniil.bank.demo.dto.BankAccountDto;

public interface BankAccountConvertor {

    BankAccountDto convert(BankAccount accounts);


}
