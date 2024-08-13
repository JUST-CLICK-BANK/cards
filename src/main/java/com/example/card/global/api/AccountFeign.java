package com.example.card.global.api;

import com.example.card.domain.dto.response.AccountFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "account-service",path = "https://just-click.shop/api/v1/accounts")
public interface AccountFeign {

    @GetMapping("/card")
    AccountFeignResponse getAccountAccountAble(@RequestParam("account") String account);
}
