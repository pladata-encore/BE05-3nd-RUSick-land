package com.example.iSale.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ISaleErrorCode {
    ISaleIdNotFound("해당 분양 정보가 없습니다.", HttpStatus.NOT_FOUND),
    ListEmpty("리스트가 비어있습니다.",HttpStatus.NOT_FOUND);

    private final String description;
    private final HttpStatus httpStatus;
}
