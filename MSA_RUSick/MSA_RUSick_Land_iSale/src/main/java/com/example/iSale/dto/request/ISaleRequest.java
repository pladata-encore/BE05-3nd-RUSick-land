package com.example.iSale.dto.request;

import com.example.iSale.domain.entity.ISale;

public record ISaleRequest(
    String iSaleIn,
    Long iSaleSellPrice,
    Integer iSaleCategory,
    String iSaleName,
    String iSaleArea,
    String iSaleAddress
) {
    public ISale toEntity(){
        return ISale.builder()
            .iSaleIn(iSaleIn)
            .iSaleSellPrice(iSaleSellPrice)
            .iSaleCategory(iSaleCategory)
            .iSaleName(iSaleName)
            .iSaleArea(iSaleArea)
            .iSaleAddress(iSaleAddress)
            .build();
    }
}
