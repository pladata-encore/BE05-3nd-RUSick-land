package com.example.iSale.exception;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ISaleErrorResponse {
    private ISaleErrorCode errorCode;
    private String message;

}
