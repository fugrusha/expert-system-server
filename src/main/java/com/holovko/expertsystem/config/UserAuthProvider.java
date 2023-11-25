package com.holovko.expertsystem.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import com.holovko.expertsystem.model.dto.user.UserAuthDTO;
import com.holovko.expertsystem.model.dto.user.UserInfoReadDTO;
import com.holovko.expertsystem.model.entity.UserType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Base64;
import java.util.Collections;

@Component
public class UserAuthProvider {

    private final String secretKey;

    public UserAuthProvider(@Value("${security.jwt.token.secret-key:secret-key}") String secretKey) {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserInfoReadDTO userDTO) {
        Instant now = Instant.now();
        Instant validity = now.plusSeconds(3_600_000);

        return JWT.create()
                .withIssuer(userDTO.getUsername())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("userType", userDTO.getUserType().name())
                .withClaim("userId", userDTO.getId())
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        DecodedJWT decodedJWT = verifier.verify(token);

        UserAuthDTO dto = new UserAuthDTO();
        dto.setUsername(decodedJWT.getIssuer());
        dto.setId(decodedJWT.getClaim("userId").asString());
        dto.setUserType(UserType.valueOf(decodedJWT.getClaim("userType").asString()));

        return new UsernamePasswordAuthenticationToken(dto, null, Collections.emptyList());
    }
}
