package com.example.iSale.service;

import com.example.iSale.dto.request.InterestISaleRequest;
import com.example.iSale.domain.entity.ISale;
import com.example.iSale.domain.entity.InterestISale;
import com.example.iSale.domain.repository.ISaleRepository;
import com.example.iSale.domain.repository.InterestISaleRepository;
import com.example.iSale.global.utils.TokenInfo;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ISaleServiceTest {
    @Autowired
    ISaleRepository iSaleRepository;
    @Autowired
    InterestISaleRepository interestISaleRepository;
    @Autowired
    ISaleService iSaleService;

    @Test
    @Transactional
    void addOrDeleteInterest() {
        //give
        ISale iSale = new ISale();
        iSaleRepository.save(iSale);
        UUID userId = UUID.randomUUID();
        InterestISaleRequest request = new InterestISaleRequest(userId, new TokenInfo("test", "test", LocalDate.now()));

        //when
        iSaleService.addOrDeleteInterest(request);

        //then
        InterestISale byIsaleAndUserId = interestISaleRepository.findByIsaleAndUserId(iSale, userId);
        assertTrue(byIsaleAndUserId != null);
    }
}