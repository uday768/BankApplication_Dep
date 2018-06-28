package com.suncorp.banking.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.suncorp.banking.application.exceptions.AccountNotExistsException;
import com.suncorp.banking.application.exceptions.InSufficientFundsException;
import com.suncorp.banking.application.service.FundsService;
import com.suncorp.banking.application.vo.AccountVo;
import com.suncorp.banking.application.vo.FundsVo;

@RestController
@RequestMapping("/account/funds")
public class FundsController {

	@Autowired()
	FundsService fundsService;
	
	@ResponseBody
	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public AccountVo depositFund(@RequestBody FundsVo fundsVo) throws AccountNotExistsException{
		AccountVo depositFunds = fundsService.depositFunds(fundsVo);
		return depositFunds;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST)
	public AccountVo withDrawFunds(@RequestBody FundsVo fundsVo) throws AccountNotExistsException, InSufficientFundsException{
		AccountVo depositFunds = fundsService.withDrawFunds(fundsVo);
		return depositFunds;
	}
	
	@ResponseBody
	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public void transferFunds(@RequestBody FundsVo fundsVo) throws AccountNotExistsException, InSufficientFundsException {
		fundsService.trasnferFunds(fundsVo);
	}
}
