package com.suncorp.banking.application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.suncorp.banking.application.vo.BeneficiaryVO;

@Repository
public interface BeneficiaryRepository extends JpaRepository<BeneficiaryVO, Long> {

	List<BeneficiaryVO> findByAccountId(Long accountId);

	@Query("select benef from BeneficiaryVO benef, AccountVo accounts where benef.account.id = accounts.id and benef.benefaccountnumber= :benefaccountnumber and accounts.accountnumber= :accountnumber")
	public List<BeneficiaryVO> find(@Param("benefaccountnumber") String benefaccountnumber, @Param("accountnumber") String accountnumber);
}
