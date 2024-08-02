package com.example.card.controller;

import com.example.card.domain.dto.request.CreditHistoryRequest;
import com.example.card.domain.dto.response.CreditHistoryResponse;
import com.example.card.service.CreditService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/credit")
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditService creditService;

    @GetMapping("/{cardId")
    public List<CreditHistoryResponse> getCreditHistory(
        @RequestParam(name = "cardId") Long cardId) {
        return creditService.getCreditHistory(cardId);
    }

    @GetMapping("/{cardId}/thisMonth")
    public Long getThisMonthBill(
        @RequestParam(name = "cardId") Long cardId) {
        return creditService.getMonthAmount(cardId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPayHistory(@RequestBody CreditHistoryRequest req) {
        creditService.createCreditHistory(req);
    }

    /*
        이하는 스케쥴러로 해야하는것
        집계끝나고 청구 만들기
        결제일마다 결제하기
     */

}
