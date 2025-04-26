package com.darshan.spring_security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static io.jsonwebtoken.Jwts.*;

@Service
public class JwtTokenService {


    private String secretKey;

    public JwtTokenService() {
        this.secretKey = generateSecretKey();
    }
    public String generateSecretKey(){
        try{
            KeyGenerator generator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey key = generator.generateKey();
            return Base64.getEncoder().encodeToString(key.getEncoded());
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException("Error generating secret key", e);
        }
    }

    public String getJwToken(String userName) {

        Map<String , Object> claims = new HashMap<>();

        return builder().
                setClaims(claims).setSubject(userName).
                setIssuedAt(new Date(System.currentTimeMillis())).
                setExpiration(new Date(System.currentTimeMillis() + 1000*60*3))
                .signWith(getKey() , SignatureAlgorithm.HS256).compact();


    }

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {


        return extractClaim(token , Claims::getSubject);
    }
    private <T> T extractClaim(String token , Function<Claims, T> claimResolver)
    {
        final Claims claims = extraAllClaims(token);
        return claimResolver.apply(claims);
    }

    public Claims extraAllClaims(String token){
        return Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return  (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

}
