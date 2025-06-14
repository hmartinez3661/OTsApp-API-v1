package com.mantprev.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Service
public class JWTService {

	
	private static final String SECRET_KEY = "586E3272357538782F413F4428472B4B6250655368566B597033733676397924";

	
    public String createToken (UserDetails usuario) {
    //**********************************************	
        return getToken(new HashMap<>(), usuario);
    }

    
    private String getToken (Map<String, Object> extraClaims, UserDetails usuario) {
    //****************************************************************************	
        return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(usuario.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + (1000*60*60*48)))  //Duración de 2 días
            .signWith(getKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    
    private Key getKey() {
    //*********************	
       byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
       return Keys.hmacShaKeyFor(keyBytes);
    }
    

    public boolean isTokenValid (String token, UserDetails userDetails) {
    //******************************************************************	
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
    
    
    public String getUsernameFromToken(String token) {
    //************************************************	
        return getClaim (token, Claims::getSubject);
    }
    
    
    private boolean isTokenExpired(String token) {
    //*********************************************	
        return getExpiration(token).before(new Date());
    }
    
    
    private Date getExpiration (String token) {
    //****************************************	
        return getClaim(token, Claims::getExpiration);
    }
    
    
    private Claims getAllClaims (String token){
    //****************************************	
        return Jwts
            .parserBuilder()
            .setSigningKey(getKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    
    public <T> T getClaim (String token, Function<Claims,T> claimsResolver) {
    //**********************************************************************	
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    

    
    
    
    
	
}
