package com.codingbydivyanshu.yt.HospitalManagement.error;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data


public class ApiError {
    private LocalDateTime Timestamp;
    private String error;
    private HttpStatus statusCode;
    ApiError(
     String error,
     HttpStatus statusCode){

        this.error=error;
        this.statusCode=statusCode;
    }


}
