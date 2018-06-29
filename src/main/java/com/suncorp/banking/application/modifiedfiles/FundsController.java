package com.suncorp.banking.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.suncorp.banking.application.exceptions.AccountNotExistsException;
import com.suncorp.banking.application.exceptions.BeneficiaryNotFoundException;
import com.suncorp.banking.application.exceptions.InSufficientFundsException;
import com.suncorp.banking.application.service.FundsService;
import com.suncorp.banking.application.vo.FundsVo;
import com.suncorp.banking.application.vo.TransactionResponse;

@RestController
@RequestMapping("/account/funds")
public class FundsController {

	@Autowired()
	FundsService fundsService;
	
	@ResponseBody
	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public ResponseEntity<TransactionResponse> depositFund(@RequestBody FundsVo fundsVo) throws AccountNotExistsException{
		TransactionResponse depositFundsResponse = fundsService.depositFunds(fundsVo);
		return ResponseEntity.status(HttpStatus.OK).body(depositFundsResponse);
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public ResponseEntity<TransactionResponse> withDrawFunds(@RequestBody FundsVo fundsVo) throws AccountNotExistsException, InSufficientFundsException{
		TransactionResponse withDrawFundsResponse = fundsService.withDrawFunds(fundsVo);
		return ResponseEntity.status(HttpStatus.OK).body(withDrawFundsResponse);

	}
	
	@ResponseBody
	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public ResponseEntity<TransactionResponse> transferFunds(@RequestBody FundsVo fundsVo) throws AccountNotExistsException, InSufficientFundsException, BeneficiaryNotFoundException {
		TransactionResponse trasnferFundsResponse = fundsService.trasnferFunds(fundsVo);
		return ResponseEntity.status(HttpStatus.OK).body(trasnferFundsResponse);
	}
}
