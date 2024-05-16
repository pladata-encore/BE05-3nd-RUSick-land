package com.example.iSale.dto.request;

import com.example.iSale.domain.entity.ISale;
import com.example.iSale.domain.entity.InterestISale;

import com.example.iSale.global.utils.TokenInfo;
import java.util.UUID;

public record InterestISaleRequest(
        // UUID userId, UUID iSaleId
        UUID iSaleId, TokenInfo tokenInfo
) {
    // public InterestISale toEntity(){
    //     return InterestISale.builder()
    //             .userId(userId)
    //             .isale(ISale.builder().iSaleId(iSaleId).build())
    //             .build();
    // }

    public InterestISale toEntity(){
        return InterestISale.builder()
            .userId(UUID.fromString(tokenInfo.id()))
            .isale(ISale.builder().iSaleId(iSaleId).build())
            .build();
    }
}
