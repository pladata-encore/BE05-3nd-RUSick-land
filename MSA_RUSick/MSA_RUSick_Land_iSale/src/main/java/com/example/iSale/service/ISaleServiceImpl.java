package com.example.iSale.service;

import com.example.iSale.dto.request.ISaleEnrollRequest;
import com.example.iSale.dto.request.ISaleRequest;
import com.example.iSale.dto.request.InterestISaleRequest;
import com.example.iSale.domain.entity.ISale;
import com.example.iSale.domain.entity.ISaleEnroll;
import com.example.iSale.domain.repository.ISaleEnrollRepository;
import com.example.iSale.domain.entity.InterestISale;
import com.example.iSale.domain.repository.ISaleRepository;
import com.example.iSale.dto.response.ISaleDetailResponse;
import com.example.iSale.dto.response.ISaleResponse;
import com.example.iSale.dto.response.InterestISaleResponse;
import com.example.iSale.exception.ISaleErrorCode;
import com.example.iSale.exception.ISaleException;
import com.example.iSale.global.utils.TokenInfo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.iSale.domain.repository.InterestISaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ISaleServiceImpl implements ISaleService {
    private final ISaleRepository iSaleRepository;
    private final InterestISaleRepository interestISaleRepository;
    private final ISaleEnrollRepository iSaleEnrollRepository;

    @Override
    public void save(ISaleRequest req) {
        iSaleRepository.save(req.toEntity());
    }

    @Override
    public ISaleDetailResponse getISaleById(String id) {
        Optional<ISale> byId = iSaleRepository.findById(UUID.fromString(id));
        ISale iSale = byId.orElseThrow(() -> new ISaleException(ISaleErrorCode.ISaleIdNotFound));

        return ISaleDetailResponse.from(iSale);
    }

    @Override
    public List<ISaleResponse> getAllISale() {
        return iSaleRepository.findAll()
            .stream()
            .map(ISaleResponse::from)
            .toList();
    }

    @Override
    public void enrollSave(String id, TokenInfo tokenInfo, ISaleEnrollRequest req) {
        Optional<ISale> byId = iSaleRepository.findById(UUID.fromString(id));
        ISale iSale = byId.orElseThrow(() -> new ISaleException(ISaleErrorCode.ISaleIdNotFound));
        int age = LocalDateTime.now().getYear() - tokenInfo.birthDay().getYear();
        Optional<ISaleEnroll> enroll = iSaleEnrollRepository.findByUserId(UUID.fromString(tokenInfo.id()));

        if (enroll.isPresent()) {
            if(enroll.get().getQualTF() == null || enroll.get().getQualTF())
                throw new IllegalArgumentException("이미 분양 신청 상태입니다.");
        }

        ISaleEnroll iSaleEnroll = ISaleEnroll.builder()
            .iSale(iSale)
            .userId(UUID.fromString(tokenInfo.id())) // todo token 까서 id값 넣기
            .income(req.income())
            .child(req.child())
            .age(age) // todo token 까서 age 넣기
            .qualTF(null)
            .build();
        iSaleEnrollRepository.save(iSaleEnroll);
    }

    @Override
    public List<InterestISaleResponse> getInterestById(TokenInfo tokenInfo) {
        List<InterestISale> interestISaleList
                = interestISaleRepository.findAllByUserId(UUID.fromString(tokenInfo.id()));

        List<InterestISaleResponse> interestISaleResponseList = new ArrayList<>();

        if(interestISaleList.isEmpty()) throw new ISaleException(ISaleErrorCode.ListEmpty);

        for (InterestISale interestISale : interestISaleList) {
            interestISaleResponseList.add(InterestISaleResponse.from(interestISale));
        }

        return interestISaleResponseList;
    }

    @Override
    public void addOrDeleteInterest(InterestISaleRequest req/*, String id, TokenInfo tokenInfo*/) {
        Optional<ISale> byId = iSaleRepository.findById(req.iSaleId());
        ISale isale = byId.orElseThrow(() -> new ISaleException(ISaleErrorCode.ISaleIdNotFound));

        InterestISale byIsale =
            interestISaleRepository.findByIsaleAndUserId(isale, UUID.fromString(req.tokenInfo().id()));
        if(byIsale != null) {
            interestISaleRepository.delete(byIsale);
        } else {
            interestISaleRepository.save(req.toEntity());
        }
    }

    @Override
    public Boolean getInterest(String iSaleId, TokenInfo tokenInfo) {
        Optional<ISale> byId = iSaleRepository.findById(UUID.fromString(iSaleId));
        ISale iSale = byId.orElseThrow(() -> new ISaleException(ISaleErrorCode.ISaleIdNotFound));
        InterestISale byIsaleAndUserId = interestISaleRepository.findByIsaleAndUserId(iSale,
            UUID.fromString(tokenInfo.id()));

        return byIsaleAndUserId != null;
    }
}
