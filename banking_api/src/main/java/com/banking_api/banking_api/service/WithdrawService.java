package com.banking_api.banking_api.service;

import com.banking_api.banking_api.domain.transactions.deposit.Deposit;
import com.banking_api.banking_api.domain.transactions.withdraw.Withdraw;
import com.banking_api.banking_api.dtos.DepositDTO;
import com.banking_api.banking_api.dtos.WithdrawDTO;
import com.banking_api.banking_api.infra.exception.InsufficientBalanceException;
import com.banking_api.banking_api.repository.DepositRepository;
import com.banking_api.banking_api.repository.WithdrawRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class WithdrawService {

    private final WithdrawRepository repository;
    private final AccountService accountService;

    public WithdrawService(WithdrawRepository repository, AccountService accountService) {
        this.repository = repository;
        this.accountService = accountService;
    }


    @Transactional
    public WithdrawDTO withdraw (WithdrawDTO dto) throws EntityNotFoundException , InsufficientBalanceException {
        var account = accountService.findByAccountId(dto.accountId());
        var value = dto.value();

        if (account.getBalance().compareTo(value) < 0) {
            throw new InsufficientBalanceException ("Saldo insuficiente para realizar a operação.");
        }

        var newBalance = account.getBalance().subtract(value);
        account.setBalance(newBalance);
        accountService.save(account);

        Withdraw newWithdraw = new Withdraw();
        newWithdraw.setValue(value);
        newWithdraw.setTimestamp(LocalDateTime.now());
        newWithdraw.setAccount(account);
        repository.save(newWithdraw);




        return new WithdrawDTO(null,null,null,newWithdraw.getTimestamp().truncatedTo(ChronoUnit.MINUTES),null,newBalance);
    }



}
