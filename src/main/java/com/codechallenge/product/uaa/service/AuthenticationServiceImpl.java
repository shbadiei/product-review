package com.codechallenge.product.uaa.service;

import com.codechallenge.product.uaa.config.CodeChallengeAuthenticationManager;
import com.codechallenge.product.uaa.exception.UAAErrorInfo;
import com.codechallenge.product.uaa.exception.UAAException;
import com.codechallenge.product.uaa.model.repository.UserInfoRepository;
import com.codechallenge.product.uaa.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final CodeChallengeAuthenticationManager authManager;

    private final UserInfoRepository userInfoRepository;

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public String loginAndGenerateJWT(String username, String password) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
        return userInfoRepository.findByUsername(username)
                .map(jwtTokenUtil::generateToken)
                .orElseThrow(() -> new UAAException(UAAErrorInfo.BadCredentials));

    }

}
