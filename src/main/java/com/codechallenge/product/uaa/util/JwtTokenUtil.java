package com.codechallenge.product.uaa.util;

import com.codechallenge.product.uaa.model.entity.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil implements Serializable {

    @Value("${security.jwt.secret}")
    private String secret;
    @Value("${security.jwt.ttlInSeconds}")
    public Integer jwtTTLInSeconds;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(UsernamePasswordAuthenticationToken token) {
        Map<String, Object> rolesClaim = new HashMap<>();
        if (!CollectionUtils.isEmpty(token.getAuthorities())) {
            rolesClaim.put(
                    "roles",
                    token.getAuthorities().stream().map(auth -> ((Role) auth).getAuthority()).collect(Collectors.toList())
            );
        }
        return doGenerateToken(rolesClaim, token.getPrincipal().toString());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder()
                .addClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtTTLInSeconds * 1_000L))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
