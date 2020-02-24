package com.svcethub.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.svcethub.constant.MessageConstants;
import com.svcethub.constant.StatusConstants;
import com.svcethub.excepton.SvcethubException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	
	
	//@Value("${app.jwtSecret}")
    private String jwtSecret= "JWTSuperSecretKey";

    //@Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs = 0;
    
    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        
        System.out.println("email: "+userPrincipal.getEmail() +"  password: " + userPrincipal.getPassword());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }
    
    public boolean validateToken(String authToken) throws SvcethubException {
        try {
        	System.out.println("validateToken");
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
        	throw new SvcethubException(StatusConstants.ERROR_STATUS, MessageConstants.MSG_INVALID_SIGNATURE, null);
        } catch (MalformedJwtException ex) {
        	throw new SvcethubException(StatusConstants.ERROR_STATUS, MessageConstants.MSG_INVALID_TOKEN, null);
        } catch (ExpiredJwtException ex) {
        	throw new SvcethubException(StatusConstants.ERROR_STATUS, MessageConstants.MSG_TOKEN_EXPIRED, null);
        } catch (UnsupportedJwtException ex) {
        	throw new SvcethubException(StatusConstants.ERROR_STATUS, MessageConstants.MSG_TOKEN_UNSUPPORTED, null);
        } catch (IllegalArgumentException ex) {
        	throw new SvcethubException(StatusConstants.ERROR_STATUS, MessageConstants.MSG_CLAIM_EMPTY, null);
        }catch (Exception e) {
			// TODO: handle exception
		}
        return false;    
    }

}
