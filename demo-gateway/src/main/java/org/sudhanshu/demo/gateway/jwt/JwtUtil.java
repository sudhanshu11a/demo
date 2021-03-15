package org.sudhanshu.demo.gateway.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    private String SECRET_KEY = "Secret";

    private final String AUTHENTICATION_PREFIX = "Bearer ";


    public String extractUsername(String token){
        String username = null;
        username = extractClaim(token, Claims::getSubject);
        return username;
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        T claim = null;
        String jwt = removePrefixToken(token);
        if(jwt!=null && claimsResolver!= null){
            final Claims claims = extractAllClaims(jwt);
            if(claims != null)
                claim = claimsResolver.apply(claims);
        }
        return claim;
    }

    private Claims extractAllClaims(String token) {
        //Optional<Claims> claims;
        try{
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token.trim()).getBody();
        }catch (ExpiredJwtException | MalformedJwtException | SignatureException ex){
            LOGGER.error(ex.getMessage() + " for Token :" + token);
        }
        return null;
    }

    private Boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Date issueDate = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(System.currentTimeMillis()+ 1000*60*60*10);
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(issueDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        if(username!=null){
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }else{
            LOGGER.error("username does not exist. Not a valid token " + token);
            return false;
        }
    }

    private String removePrefixToken(String token){
        String jwt = null;
        if(token.startsWith(AUTHENTICATION_PREFIX)) {
            jwt = token.substring(7);
        }else{
            LOGGER.error("Authentication prefix is not valid for token " + token);
        }
        return jwt;
    }
}
