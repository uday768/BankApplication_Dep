package com.suncorp.banking.application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suncorp.banking.application.vo.AccountTransactionVO;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransactionVO, Long> {
	
	
    //Page<AccountTransactionVO> findByAccountNumber(String accountNumber, Pageable pageable);
	
	List<AccountTransactionVO> findByAccountId(Long accountId);

}
