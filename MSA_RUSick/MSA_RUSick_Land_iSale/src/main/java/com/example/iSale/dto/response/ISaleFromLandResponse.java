package com.example.iSale.dto.response;

import java.util.UUID;

public record ISaleFromLandResponse(
    UUID ownerId, String landName
) {

}
