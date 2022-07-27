package com.codechallenge.product.uaa.config;

import com.codechallenge.product.uaa.exception.UAAErrorInfo;
import com.codechallenge.product.uaa.exception.UAAException;
import com.codechallenge.product.uaa.model.repository.UserInfoRepository;
import com.codechallenge.product.uaa.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CodeChallengeAuthenticationManager implements AuthenticationManager {

    private final UserInfoRepository userInfoRepository;

    private final PasswordUtil passwordUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String plainPassword = authentication.getCredentials().toString();
        return userInfoRepository.findByUsername(username)
                .filter(user -> passwordUtil.matchPassword(username, plainPassword, user.getHashedPassword()))
                .map(user -> new UsernamePasswordAuthenticationToken(
                                username,
                                plainPassword,
                                user.getAuthorities()
                        )
                ).orElseThrow(() -> new UAAException(UAAErrorInfo.BadCredentials));
    }
}
