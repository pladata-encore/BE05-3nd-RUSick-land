package com.example.iSale.service;

import com.example.iSale.dto.request.ISaleEnrollRequest;
import com.example.iSale.dto.request.ISaleRequest;
import com.example.iSale.dto.request.InterestISaleRequest;
import com.example.iSale.domain.entity.ISale;
import com.example.iSale.domain.entity.InterestISale;

import com.example.iSale.dto.response.ISaleDetailResponse;
import com.example.iSale.dto.response.ISaleResponse;
import com.example.iSale.dto.response.InterestISaleResponse;
import com.example.iSale.global.utils.TokenInfo;
import java.util.List;
import java.util.UUID;

public interface ISaleService {
    void save(ISaleRequest req);
    ISaleDetailResponse getISaleById(String id);
    List<ISaleResponse> getAllISale();
    void enrollSave(String id, TokenInfo tokenInfo, ISaleEnrollRequest req);
    List<InterestISaleResponse> getInterestById(TokenInfo tokenInfo/*, UUID Id*/);
    // void addOrDeleteInterest(InterestISaleRequest request);
    void addOrDeleteInterest(InterestISaleRequest req);
    Boolean getInterest(String iSaleId, TokenInfo tokenInfo);
}
