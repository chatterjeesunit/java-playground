package com.play.util.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

/**
 * Code for the blog - https://sunitc.dev/2020/08/17/working-with-jwt-json-web-tokens/#toc
 */
public class JWTDemo {


    public String createJWT(String issuer, String secret, String subject, long timeToLiveInSeconds) {

        String jwt =
            Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(subject)
                .setIssuer(issuer)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(Duration.ofSeconds(timeToLiveInSeconds))))
                .claim("firstName", "Sunit")
                .claim("lastName", "Chatterjee")
                .claim("isAdmin", true)
                .signWith(getSecretKey(secret))
                .compact();

        System.out.println(jwt);

        return jwt;
    }


    public Claims parseJWT(String jwtString, String secret) {

        Jws<Claims> headerClaimsJwt =
                Jwts.parserBuilder()
                        .setSigningKey(getSecretKey(secret))
                .build()
                .parseClaimsJws(jwtString);

        return headerClaimsJwt.getBody();

    }

    private SecretKey getSecretKey(String secret) {
        SecretKey key = null;
        try {
            key = Keys.hmacShaKeyFor(secret.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return key;
    }


}
