package com.ashish.pack.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.codec.Decoder;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtService {

    public String generateToken(String userName){

        Map<String,Object> claims =  new HashMap<>();
        return createToken(claims,userName);
    }

    public String createToken(Map<String,Object> claims, String userName){
        return Jwts.builder()
                .addClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+15*60*1000))
                .signWith(SignatureAlgorithm.HS512, "F-JaNdRgUkXp2s5v8y/B?E(G+KbPeShV")
                .compact();
    }

 /*   private Key getSignKey() {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] keyBytes = decoder.decode("F-JaNdRgUkXp2s5v8y/B?E(G+KbPeShV");
//        return
    }*/
}
