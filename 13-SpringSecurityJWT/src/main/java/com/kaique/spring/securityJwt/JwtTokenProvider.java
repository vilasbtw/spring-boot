package com.kaique.spring.securityJwt;

import com.auth0.jwt.algorithms.Algorithm;
import com.kaique.spring.data.vo.v1.security.TokenVO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:3600000}")
    private long validityInMilliseconds = 3600000;

    @Autowired
    private UserDetailsService service;

    Algorithm algorithm = null;

    // Spring needs the bean to be instantiated before the injection is made
    // This annotation lets Spring instantiate the var service before calling this method
    // Otherwise, it would return NullPointer, because if not instantiated, the var would be null
    @PostConstruct
    public void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenVO createAccessToken(String username, List<String> roles) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        var accessToken = getAccessToken(username, roles, now, validity);
        var refreshToken = getAccessToken(username, roles, now);

        return new TokenVO(username, true, now, validity, accessToken, refreshToken);
    }

    private String getAccessToken(String username, List<String> roles, Date now, Date validity) {

    }

    private String getAccessToken(String username, List<String> roles, Date now) {

    }
}