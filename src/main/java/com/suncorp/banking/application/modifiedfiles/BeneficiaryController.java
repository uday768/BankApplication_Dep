package com.suncorp.banking.application.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.suncorp.banking.application.exceptions.AccountNotExistsException;
import com.suncorp.banking.application.service.BeneficiaryService;
import com.suncorp.banking.application.vo.BeneficiaryVO;
import com.suncorp.banking.application.vo.TransactionResponse;

@RestController
@RequestMapping("/account/beneficiary")
public class BeneficiaryController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BeneficiaryService beneficiaryService;

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<TransactionResponse> createAccount1(@RequestBody BeneficiaryVO beneficiaryDetails)
			throws AccountNotExistsException {
		logger.info("Inside the create acccount controller method");
		TransactionResponse addBeneficiary = beneficiaryService.addBeneficiary(beneficiaryDetails);
		return ResponseEntity.status(HttpStatus.CREATED).body(addBeneficiary);
	}

	@ResponseBody
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public List<BeneficiaryVO> fetchAccountTransactions(@RequestBody BeneficiaryVO beneficiaryDetails) throws AccountNotExistsException {
		logger.info("Fetching the transaction details for the account number:" + beneficiaryDetails.getAccountnumber());
		return beneficiaryService.getBeneficiaryList(beneficiaryDetails.getAccountnumber());
	}

}
