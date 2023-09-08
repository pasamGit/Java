package com.pasam.demo.util;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;
@Component
public class JwtTokenUtil implements Serializable {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.token.expire}")
    private long tokenExpire;

    public String getUserNameFromToken(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private <T>T extractClaim(String token, Function<Claims,T> getSubject) {
        final Claims claims=extractAllClaims(token);
        return getSubject.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignedKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignedKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public boolean isTokenValid(String token){
        return extractClaimToken(token).before(new Date());
    }

    private Date extractClaimToken(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    public String generateToken(UserDetails service){
        return buildToken(new HashMap<>(),service,tokenExpire);
    }

    private String buildToken(HashMap<String, Object> claims, UserDetails service, long tokenExpire) {

    return Jwts.builder().setClaims(claims).setSubject(service.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpire))
                .signWith(getSignedKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token,UserDetails userDetails){
        String userName=getUserNameFromToken(token);
        return userName.equals(userDetails.getUsername()) && !isTokenValid(token);
    }


}
