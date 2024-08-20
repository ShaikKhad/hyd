package com.jsp.Crudoperationonetoonemappingrealation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BankService {
    Page<Bank> getAllBanks(Pageable pageable);
    Bank getBankById(Long id);
    Bank saveBank(Bank bank);
    void deleteBank(Long id);
}

