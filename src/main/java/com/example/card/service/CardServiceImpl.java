package com.example.card.service;

import com.example.card.config.utils.card.CVC;
import com.example.card.config.utils.card.GenerateCardNumber;
import com.example.card.config.utils.jwt.TokenInfo;
import com.example.card.domain.dao.CardDao;
import com.example.card.domain.dto.request.*;
import com.example.card.domain.dto.response.AccountFeignResponse;
import com.example.card.domain.dto.response.CardProductCardResponse;
import com.example.card.domain.entity.Card;
import com.example.card.domain.repository.CardRepository;
import com.example.card.global.api.AccountApi;
import com.example.card.global.api.AccountFeign;
import jakarta.persistence.EntityNotFoundException;

import java.util.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final CardDao cardDao;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final AccountApi accountApi;

    @Override
    public void deleteCardFromAccount(UUID userId,String account){
        // List<Card> 를 가져옴
        List<Card> cards = cardRepository.findByAccount(account);
        cards.forEach(card -> card.setCardAble(false));

        // 가져온 다음에 false로 만듬 예를 들어 .forEach .stream.map() 사용
        cardRepository.saveAll(cards);
    }
    @Override
    public List<Card> getAllMyCard(TokenInfo tokenInfo) {
        return cardRepository.findByUserId(UUID.fromString(tokenInfo.id()));
    }

    @Override
    public Card getMyCard(Long cardId) {
        return cardRepository.findDisabledCardByCardId(cardId).orElseThrow(EntityNotFoundException::new);
    }
    @Override
    public Boolean getAbleMyCard(Long cardId) {
        return cardRepository.findDisabledCardByCardId(cardId).isPresent();
    }



    @Override
    public void addCard(TokenInfo tokenInfo, CardRequest req) {
        UUID userId = UUID.fromString(tokenInfo.id());

        String cardNumber = generateUniqueCardNumber(req.cardCheck().getType());
        String cardCVC = CVC.generateCVC();
        Date cardCreatedAt = new Date();
        cardDao.saveCard(req, cardNumber, userId, cardCVC, cardCreatedAt, tokenInfo);
        scheduleCardDeletion(userId, cardNumber, cardCreatedAt);

    }
    //중복된 카드 번호 확인
    private String generateUniqueCardNumber(String type) {
        String cardNumber;
        do {
            cardNumber = GenerateCardNumber.generateCardNumber(type);
        } while (cardRepository.findByCardNumber(cardNumber).isPresent());
        return cardNumber;
    }
    //카드 생성 5년이 지나면
    private void scheduleCardDeletion(UUID userId, String cardNumber, Date cardCreatedAt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cardCreatedAt);
        calendar.add(Calendar.YEAR, 5);
        Date fiveYearsLater = calendar.getTime();

        long delay = fiveYearsLater.getTime() - System.currentTimeMillis();

        scheduler.schedule(() -> deleteCard(userId, cardNumber), delay, TimeUnit.MILLISECONDS);
    }

    @Override
    @Transactional
    public void updateCard(UUID userId,long cardId, CardUpdateRequest req) {
        if (userId == null) throw new IllegalArgumentException("유효하지 않는 토큰입니다.");
        Card card = cardRepository.findByCardId(cardId)
                .orElseThrow(IllegalArgumentException::new);
        card.updateCard(req.password(), req.cardOneTimeLimit(), req.cardMonthLimit(), req.cardName(),req.cardPaymentDate());


    }
    @Override
    public void updateCardPassword(UUID userId,long cardId, CardPasswordRequest req) {
        if (userId == null) throw new IllegalArgumentException("유효하지 않는 토큰입니다.");
        Card card = cardRepository.findByCardId(cardId)
                .orElseThrow(IllegalArgumentException::new);
        card.updateCardPassword(req.cardpassword());
    }
    @Override
    public void updateCardName(UUID userId,long cardId, CardNameRequest req) {
        if (userId == null) throw new IllegalArgumentException("유효하지 않는 토큰입니다.");
        Card card = cardRepository.findByCardId(cardId)
                .orElseThrow(IllegalArgumentException::new);
        card.updateCardName(req.cardName());
    }
    @Override
    public void updateCardOneTimeLimit(UUID userId,long cardId, CardOneTimeLimitRequest req) {
        if (userId == null) throw new IllegalArgumentException("유효하지 않는 토큰입니다.");
        Card card = cardRepository.findByCardId(cardId)
                .orElseThrow(IllegalArgumentException::new);
        card.updateCardOneTimeLimit(req.cardOneTimeLimit());
    }
    @Override
    public void updateCardMonthLimit(UUID userId,long cardId, CardMonthLimitRequest req) {
        if (userId == null) throw new IllegalArgumentException("유효하지 않는 토큰입니다.");
        Card card = cardRepository.findByCardId(cardId)
                .orElseThrow(IllegalArgumentException::new);
        card.updateCardMonth(req.cardMonthLimit());
    }
    @Override
    public void updateCardPaymentDate (UUID userId,long cardId, CardPatmentDateRequest req){
        if (userId == null) throw new IllegalArgumentException("유효하지 않는 토큰입니다.");
        Card card = cardRepository.findByCardId(cardId)
                .orElseThrow(IllegalArgumentException::new);
        card.updateCardPaymentDate(req.cardPaymentDate());
    }



    @Override
    public CardProductCardResponse getCardCardProductByCardID (long cardId){
        Card card = cardRepository.findCardCardProductByCardId(cardId)
                .orElseThrow(IllegalArgumentException::new);
        return CardProductCardResponse.from(card.getCardProduct(),card);

    }


    @Override
    public void deleteCard(UUID userId, String cardNumber) {
        Card delete = cardRepository.findByUserIdAndCardNumber(userId, cardNumber)
                .orElseThrow(IllegalArgumentException::new);

        delete.setCardAble(false);
        cardRepository.save(delete);

    }





}
