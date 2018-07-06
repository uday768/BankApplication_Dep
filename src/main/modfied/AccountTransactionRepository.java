package com.suncorp.banking.application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suncorp.banking.application.dto.AccountTransactionDTO;

@Repository
public interface AccountTransactionRepository extends JpaRepository<AccountTransactionDTO, Long> {

	List<AccountTransactionDTO> findByAccountId(Long accountId);

}
