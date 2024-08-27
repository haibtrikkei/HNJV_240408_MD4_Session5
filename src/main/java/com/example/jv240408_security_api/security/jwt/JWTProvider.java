package com.example.jv240408_security_api.security.jwt;

import com.example.jv240408_security_api.security.principal.CustomUserDetail;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTProvider {
    @Value("${expiration}")
    private Long expiration;
    @Value("${secret}")
    private String secret;

    public String generateJWTToken(CustomUserDetail userDetail){
        Date today = new Date();
        return Jwts.builder()
                .setSubject(userDetail.getUsername())
                .setExpiration(new Date(today.getTime()+expiration))
                .signWith(SignatureAlgorithm.HS512,secret)
                .setIssuedAt(today)
                .compact();
    }

    public boolean validateToken(String jwtToken){
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken);
            return true;
        }catch (UnsupportedJwtException ex){
            System.out.println("Server API not supported JWT: ");
            ex.printStackTrace();
        }catch (MalformedJwtException ex){
            System.out.println("Chuoi JWT khong dung");
            ex.printStackTrace();
        }catch (ExpiredJwtException ex){
            System.out.println("Thoi gian dang nhap het han");
            ex.printStackTrace();
        }catch (Exception ex){
            System.out.println("Loi: "+ex.getMessage());
        }
        return false;
    }

    public String getUsernameFromToken(String jwtToken){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwtToken).getBody().getSubject();
    }
}
