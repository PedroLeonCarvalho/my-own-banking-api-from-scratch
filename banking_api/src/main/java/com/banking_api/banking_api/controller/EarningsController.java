package com.banking_api.banking_api.controller;

import com.banking_api.banking_api.infra.exception.BadResponseException;
import com.banking_api.banking_api.service.AccountService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@SecurityRequirement(name = "Authorization")
@RestController
@RequestMapping("/earnings")
public class EarningsController {

    private final AccountService accountService;


    public EarningsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    @CacheEvict(value= "taxaSelic")
    @Scheduled(cron = "0 0 0 * * ?")
    public ResponseEntity generateEarnings() throws BadResponseException {
        accountService.earningsGenerate();
        return ResponseEntity.ok("Rendimentos gerados");
    }


}



