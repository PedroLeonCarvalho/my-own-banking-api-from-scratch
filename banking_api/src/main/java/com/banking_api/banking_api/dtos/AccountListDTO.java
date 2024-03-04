package com.banking_api.banking_api.dtos;

import com.banking_api.banking_api.domain.account.AccountType;

public record AccountListDTO(
        String accountNumber,
        AccountType type,
        boolean active,

        String userId) {

}
