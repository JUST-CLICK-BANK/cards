package com.example.card.service;

import com.example.card.config.utils.card.CVC;
import com.example.card.config.utils.card.GenerateCardNumber;
import com.example.card.config.utils.jwt.TokenInfo;
import com.example.card.domain.dao.CardDao;
import com.example.card.domain.dto.request.CardUpdateRequest;
import com.example.card.domain.dto.request.CardRequest;
import com.example.card.domain.dto.response.CardProductCardResponse;
import com.example.card.domain.entity.Card;
import com.example.card.domain.repository.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;
    private final CardDao cardDao;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    public List<Card> getAllMyCard(TokenInfo tokenInfo) {
        return cardRepository.findByUserId(UUID.fromString(tokenInfo.id()));
    }

    @Override
    public Card getMyCard(Long cardId) {
        return cardRepository.findByCardId(cardId).orElseThrow(EntityNotFoundException::new);
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
    public void deleteCard(UUID userId, String cardNumber) {
        Card delete = cardRepository.findByUserIdAndCardNumber(userId, cardNumber)
                .orElseThrow(IllegalArgumentException::new);

        delete.setCardDisable(false);
        cardRepository.save(delete);

    }
    @Override
    public CardProductCardResponse getCardCardProductByCardID (long cardId){
        Card card = cardRepository.findCardCardProductByCardId(cardId)
                .orElseThrow(IllegalArgumentException::new);
        return CardProductCardResponse.from(card.getCardProduct(),card);

    }




}
