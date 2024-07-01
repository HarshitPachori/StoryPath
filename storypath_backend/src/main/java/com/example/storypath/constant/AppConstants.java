package com.example.storypath.constant;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;

public class AppConstants {

    public static final String JWT_TOKEN_SECRET = Base64.getEncoder()
            .encodeToString(Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded());
    public static final long JWT_TOKEN_VALIDITY = 15 * 60 * 1000; // 15 min
    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";
}
