package com.codewithmosh.store.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
@AllArgsConstructor
public class LoginResponse {
    private Jwt accessToken;
    private Jwt refreshToken;
}
