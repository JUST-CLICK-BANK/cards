package com.example.card.service;

import com.example.card.domain.dto.request.CardProductRequest;
import com.example.card.domain.dto.request.CardRequest;
import com.example.card.domain.dto.response.CardProductResponse;
import com.example.card.domain.entity.CardProduct;
import com.example.card.domain.repository.CardProductRepository;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import io.grpc.internal.ReadableBuffer;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
//import lombok.Value;
import org.hibernate.engine.jdbc.ReaderInputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
//@RequiredArgsConstructor
public class CardProductServiceImpl implements  CardProductService{
    private final CardProductRepository cardProductRepository;
//    private final Storage storage;
//    private final CardProductRepository cardProductRepository;
//    private final String BUCKET_NAME = "cardimg_bucket"; // 버킷 이름
//    private final Storage storage = StorageOptions.getDefaultInstance().getService();
private final Storage storage;
    private final String bucketName;

    public CardProductServiceImpl(CardProductRepository cardProductRepository, Storage storage, @Value("${spring.cloud.gcp.storage.bucket}") String bucketName) {
        this.cardProductRepository = cardProductRepository;
        this.storage = storage;
        this.bucketName = bucketName;
    }

    @Override
    public List<CardProduct> getAllCardProduct() {
        return cardProductRepository.findAll();
    }

    @Override
    public CardProduct getCardProductById(Long cardProductId) {
        return cardProductRepository.findById(cardProductId).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void addCardProduct(CardProductRequest  req){
        CardProduct cardProduct = req.toEntity();
        // 이미지 파일 업로드
        MultipartFile cardImg = req.cardImg();
        if (cardImg != null && !cardImg.isEmpty()) {
            String imgUrl = uploadFile(cardImg);
            cardProduct.setCardImg(imgUrl); // 이미지 URL을 엔티티에 설정
        }
         cardProductRepository.save(cardProduct);

    }
    @Override
    public  void deleteCardProduct(long cardProductId){
        CardProduct cardProduct = cardProductRepository.findById(cardProductId)
                .orElseThrow(() -> new RuntimeException("Card product not found"));

        // 이미지 파일 삭제
        if (cardProduct.getCardImg() != null) {
            deleteFile(cardProduct.getCardImg());
        }
        cardProductRepository.deleteById(cardProductId);

    }
    @Override
    public String getCardImgBycardID(long cardProductId) {
        return cardProductRepository.findCardImgByCardProductId(cardProductId)
                .describeConstable().orElseThrow(() -> new RuntimeException("Card product not found"));

    }

    @Override
    public List<String> getAllCardImages() {
        return cardProductRepository.findAllCardImages();
    }

//    @Override
//    public CardProductResponse getCardNameCardImgCardAnnualFeeCardBenefitByCardID(long cardProductId) {
//        CardProduct cardProduct = cardProductRepository.findCardProductByCardProductId(cardProductId)
//                .orElseThrow(() -> new RuntimeException("Card product not found"));
//        return CardProductResponse.from(cardProduct);
//    }
    @Override
    public CardProductResponse getCardNameCardImgCardAnnualFeeCardBenefitByCardID(long cardProductId) {
        CardProduct cardProduct = cardProductRepository.findCardProductByCardProductId(cardProductId)
                .orElseThrow(() -> new RuntimeException("Card product not found"));
        return CardProductResponse.from(cardProduct);
    }

    private String uploadFile(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        String ext = file.getContentType();
        String uuid = UUID.randomUUID().toString();
        String fileName = uuid + "_" + originalName;

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName)
                .setContentType(ext)
                .build();

        try {
            String prefix = "https://storage.googleapis.com/";
            storage.create(blobInfo, file.getInputStream());
            return prefix + bucketName + "/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file", e);
        }
    }

    private void deleteFile(String fileUrl) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf('/') + 1);
        storage.delete(bucketName, fileName);
    }

}
