package com.example.card.service;

import com.example.card.config.constants.CardCheck;
import com.example.card.config.constants.CardTransportation;
import com.example.card.config.utils.card.CVC;
import com.example.card.config.utils.card.GenerateCardNumber;
import com.example.card.config.utils.jwt.TokenInfo;
import com.example.card.domain.dao.CardDao;
import com.example.card.domain.dto.request.CardRequest;
import com.example.card.domain.entity.Card;
import com.example.card.domain.repository.CardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class CardServiceTest {
    @Mock
    private CardRepository cardRepository;

    @Mock
    private CardDao cardDao;

    @InjectMocks
    private CardServiceImpl cardService;

    private TokenInfo tokenInfo;
    private CardRequest cardRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
//        MockitoAnnotations.initMocks(this);
        tokenInfo = new TokenInfo("bfa974e2-817c-4da3-bd46-d841ffb7b17a",
                "BWTUI2",
                "",
                "박박박",
                6);
        cardRequest = new CardRequest(
                "1234",
                CardCheck.CREDIT,
                "4162781287368",
                CardTransportation.POSTPAID,
                1L,
                "01"

        );

    }

    @Test
    void testAddCardSuccess() {


            when(cardRepository.findByCardNumber(anyString())).thenReturn(Optional.empty());
            when(GenerateCardNumber.generateCardNumber("credit")).thenReturn("1234567890123456");
            doNothing().when(cardDao).saveCard(any(CardRequest.class), anyString(), any(UUID.class), anyString(), any(Date.class), any(TokenInfo.class));

            // When
            cardService.addCard(tokenInfo+, cardRequest);

            // Then
            verify(cardDao).saveCard(eq(cardRequest), eq("1234567890123456"), eq(UUID.fromString("1")), anyString(), any(Date.class), eq(tokenInfo));
        }



//    @Test
//    void 카드_생성_실패() {
//    }

//    @Test
//    void updateCard() {
//    }
//
//    @Test
//    void 계좌_삭제_성공() {
//    }
//
//    @Test
//    void 계좌_삭제_실패() {
//    }
    }

