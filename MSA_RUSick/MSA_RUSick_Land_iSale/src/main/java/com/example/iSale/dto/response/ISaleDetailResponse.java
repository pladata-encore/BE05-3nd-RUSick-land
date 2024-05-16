package com.example.iSale.dto.response;

import com.example.iSale.domain.entity.ISale;
import java.util.UUID;

public record ISaleDetailResponse(
    UUID id,
    String iSaleName,
    Integer iSaleCategory,
    String iSaleIn,
    Long iSaleSellPrice,
    String iSaleArea,
    String iSaleAddress
) {
    public static ISaleDetailResponse from(ISale iSale){
        return new ISaleDetailResponse(
            iSale.getISaleId(),
            iSale.getISaleName(),
            iSale.getISaleCategory(),
            iSale.getISaleIn(),
            iSale.getISaleSellPrice(),
            iSale.getISaleArea(),
            iSale.getISaleAddress()
        );
    }
}
