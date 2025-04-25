package com.contact_notes.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

/**
 * properties for JWT token creation
 */
@Component
public class JwtUtil {
    private final String secret = UUID.randomUUID().toString();

    // Method to sign and create a JWT using the injected secret
    public String generateToken(String username) throws IllegalArgumentException, JWTCreationException {
        return JWT.create()
                .withSubject("User Details")
                .withClaim("username", username)
                .withIssuedAt(new Date())
                .withIssuer("Contact Notes System")
                .sign(Algorithm.HMAC256(secret));
    }

    // Method to verify the JWT and then decode and extract the user email stored in the payload of the token
    public String validateTokenAndRetrieveSubject(String token)throws JWTVerificationException {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withSubject("User Details")
                .withIssuer("Contact Notes System")
                .build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaim("username").asString();
    }

}
