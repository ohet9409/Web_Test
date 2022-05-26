package com.example.demo.security;

import com.example.demo.model.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {
    private static final String SECRET_KEY="NMA8JPctFuna59f5";

    public String create(UserEntity userEntity) {
        // ������ ���ݺ��� 1�Ϸ� ����
        Date expiryDate = Date.from(
                Instant.now()
                        .plus(1, ChronoUnit.DAYS));


        /*
        {
            // header
            "alg":"HS512"
        }
        {
            // payload
            "sub":"402880b780ff8c7c0180ff8c97d70000",
                "iss":"demo app",
                "iat":1653554913,
                "exp":1653641313
        }
        */

        // JWT Token ����
        return Jwts.builder()
                // header�� �� ���� �� ������ �ϱ� ���� SECRET_KEY
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                // payload�� �� ����
                .setSubject(userEntity.getId()) // sub
                .setIssuer("demo app") // iss
                .setIssuedAt(new Date()) // iat
                .setExpiration(expiryDate) // exp
                .compact();
    }

    public String validateAndGetUserId(String token) {
        // parseClaimsJws �޼��尡 Base64�� ���ڵ� �Ľ�
        // ����� ���̷ε带 setSigningKey�� �Ѿ�� ��ũ���� �̿��� ������ �� token�� ������ ��
        // �������� �ʾҴٸ� ���̷ε�(Claims) ����, ������� ���ܸ� ����
        // ���� �츮�� userId�� �ʿ��ϹǷ� getBody�� �θ���.
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}