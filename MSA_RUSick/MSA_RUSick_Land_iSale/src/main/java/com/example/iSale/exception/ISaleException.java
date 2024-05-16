package com.example.iSale.exception;

import lombok.*;


@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ISaleException extends RuntimeException{

    private ISaleErrorCode errorCode;
    private String message;

    public ISaleException(ISaleErrorCode errorCode){
        this.errorCode = errorCode;
        this.message = errorCode.getDescription();
    }

}
