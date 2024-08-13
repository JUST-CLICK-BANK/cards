package com.example.card.global.api;

import com.example.card.domain.dto.response.AccountFeignResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountApi {
    private final AccountFeign accountFeign;

    @Async
    public AccountFeignResponse getAccount(String account) {
        return accountFeign.getAccountAccountAble(account);
    }


}
