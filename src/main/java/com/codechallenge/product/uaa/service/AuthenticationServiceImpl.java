package com.codechallenge.product.uaa.service;

import com.codechallenge.product.uaa.config.CodeChallengeAuthenticationManager;
import com.codechallenge.product.uaa.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final CodeChallengeAuthenticationManager authManager;

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public String loginAndGenerateJWT(String username, String password) {
        return jwtTokenUtil.generateToken(
                (UsernamePasswordAuthenticationToken)
                        authManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                        username,
                                        password
                                )
                        ));

    }

}
