package com.example.iSale.dto.response;

import com.example.iSale.domain.entity.ISale;
import com.example.iSale.domain.entity.InterestISale;

public record InterestISaleResponse(
    String iSaleName,
    Long iSaleSellPrice,
    String iSaleArea
) {
    public static InterestISaleResponse from(InterestISale interestISale){
        return new InterestISaleResponse(
            interestISale.getIsale().getISaleName(),
            interestISale.getIsale().getISaleSellPrice(),
            interestISale.getIsale().getISaleArea()
        );
    }
}
