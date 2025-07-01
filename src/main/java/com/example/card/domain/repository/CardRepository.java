package com.example.card.domain.repository;

import com.example.card.domain.entity.Card;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface CardRepository extends JpaRepository<Card, Long> {
    Optional<Card> findByCardId(@Param("cardId") long cardId);

    Optional<Card> findByCardNumber(String cardNumber);
    Optional<Card>findByUserIdAndCardNumber(UUID userId, String cardNumber);
    @Query("SELECT c FROM Card c JOIN FETCH c.cardProduct cp WHERE c.cardId = :cardId")
    Optional<Card> findCardCardProductByCardId(@Param("cardId") long cardId);


    @Query("SELECT c FROM Card c WHERE c.cardId = :cardId ")
    Optional<Card> findCardByCardId(@Param("cardId") long cardId);
    @Query("SELECT c FROM Card c WHERE c.userId = :userId AND c.cardAble = true")
    List<Card> findByUserId(@Param("userId") UUID userId);
    @Query("SELECT c FROM Card c WHERE c.cardId = :cardId AND c.cardAble = true")
    Optional<Card> findDisabledCardByCardId(@Param("cardId") long cardId);


    @Query("SELECT c FROM Card c WHERE c.account = :account")
    List<Card> findByAccount(@Param("account") String account);

}
