package com.example.iSale.dto.response;

import com.example.iSale.domain.entity.ISale;
import java.util.UUID;

public record ISaleResponse(
    UUID id,
    String iSaleName,
    String iSaleAddress
) {
    public static ISaleResponse from(ISale iSale){
        return new ISaleResponse(
            iSale.getISaleId(),
            iSale.getISaleName(),
            iSale.getISaleAddress()
        );
    }
}
