package com.daniil.bank.demo.dal.repository;

import com.daniil.bank.demo.dal.entity.BankAccount;
import com.daniil.bank.demo.enums.ACCOUNT_STATUS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    BankAccount findBankAccountByIBAN(String IBAN);

    BankAccount findBankAccountByIBANAndAccountStatus(String IBAN, ACCOUNT_STATUS accountStatus);

}
