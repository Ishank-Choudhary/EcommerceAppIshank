package com.ecommerce.sb_ecom_project.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;
    private int status;
    private long timeStamp;

    public ErrorResponse(String message, int status) {
        this.message = message;
        this.status = status;
        this.timeStamp = System.currentTimeMillis();
    }
}
