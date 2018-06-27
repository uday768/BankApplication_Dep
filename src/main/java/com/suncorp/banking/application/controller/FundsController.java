package com.suncorp.banking.application.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/funds")
public class FundsController {

	@ResponseBody
	@RequestMapping(value = "/deposit", method = RequestMethod.GET)
	public String viewFunds(){
		return "Inside deposit account";
	}
}
