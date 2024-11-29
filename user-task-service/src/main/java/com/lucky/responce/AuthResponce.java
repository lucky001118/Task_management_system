package com.lucky.responce;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponce {
    private String jwt;
    private String message;
    private boolean status;
}
