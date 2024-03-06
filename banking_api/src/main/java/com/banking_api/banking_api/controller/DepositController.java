package com.banking_api.banking_api.controller;

import com.banking_api.banking_api.domain.transactions.deposit.Deposit;
import com.banking_api.banking_api.dtos.DepositDTO;
import com.banking_api.banking_api.service.DepositService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deposit")
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService) {

        this.depositService = depositService;
    }

    @PostMapping
    public ResponseEntity<Deposit> toDeposit(@RequestBody DepositDTO dto) throws Exception {
        var newDeposit = depositService.deposit(dto);
        return ResponseEntity.ok(newDeposit);
    }
}