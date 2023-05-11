package com.example.Oms.Configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.Oms.Entity.ShopInfo;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {

    public String genereateJwtToken(String type, String email) {
        String token = JWT.create().withSubject("User")
                .withClaim("Email", email)
                .withClaim("Type", type)
                .withIssuedAt(new Date())
                .withIssuer("www.incture.com")
                .sign(Algorithm.HMAC256("this is a secret key"));
        return token;
    }

    public Map<String, Claim> validateToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("this is a secret key"))
                .withSubject("User")
                .withIssuer("www.incture.com")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims();
    }
}
