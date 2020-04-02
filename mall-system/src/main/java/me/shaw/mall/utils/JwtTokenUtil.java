package me.shaw.mall.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsMutator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIN_KEY_USERNAME = "sub";
    private  static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expire}")
    private Long expiration;

    private String generateToken(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpireDate())
                .signWith(SignatureAlgorithm.ES512, secret)
                .compact();
    }

    private Claims getClaimsFromToken(String token){
        Claims claims = null;
        try{
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e){
            LOGGER.debug("Load JWT failed:{}", token);
        }
        return claims;
    }

    private Date generateExpireDate(){
        return new Date(System.currentTimeMillis() + expiration*1000);
    }

    public String getUsernameFromToken(String token){
        String username = "";
        try{
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e){}
        return username;
    }

    public boolean validToken(String token, UserDetails userDetails){
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpire(token);
    }

    public boolean isTokenExpire(String token){
        Date expireDate = getExpireDate(token);
        return new Date().before(expireDate);
    }

    private Date getExpireDate(String token){
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIN_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    public boolean canRefresh(String token){
        return !isTokenExpire(token);
    }

    public String refreshToken(String token){
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
}
