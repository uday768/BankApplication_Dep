package com.suncorp.banking.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.suncorp.banking.application.vo.AccountVo;

@Repository
public interface AccountRepository extends JpaRepository<AccountVo, Long> {
	
	AccountVo findAccountByAccountNumber(String accountNumber);
}
