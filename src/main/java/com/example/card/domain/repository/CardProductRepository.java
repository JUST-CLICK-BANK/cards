package com.example.card.domain.repository;

import com.example.card.domain.entity.CardProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface CardProductRepository extends JpaRepository<CardProduct, Long> {
//    Optional<CardProduct> findByProductId(Long productId);
    @Query("SELECT c.cardImg FROM CardProduct c WHERE c.cardProductId = :cardProductId ")
    String findCardImgByCardProductId(@Param("cardProductId") Long cardProductId);
    @Query("SELECT c.cardImg FROM CardProduct c")
    List<String> findAllCardImages();
    @Query("SELECT c FROM CardProduct c WHERE c.cardProductId = :cardProductId")
    Optional<CardProduct> findCardProductByCardProductId(@Param("cardProductId") long cardProductId);
//    List<CardProduct> findCardProductByCardId(Long cardProductId);
}
