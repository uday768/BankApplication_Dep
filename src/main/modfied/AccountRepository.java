package com.suncorp.banking.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suncorp.banking.application.dto.AccountDTO;

@Repository
public interface AccountRepository extends JpaRepository<AccountDTO, Long> {
	
	AccountDTO findAccountByAccountnumber(String accountNumber);
}
