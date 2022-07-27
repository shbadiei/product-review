package com.codechallenge.product.uaa.filter;

import com.codechallenge.product.uaa.exception.UAAErrorInfo;
import com.codechallenge.product.uaa.exception.UAAException;
import com.codechallenge.product.uaa.model.repository.UserInfoRepository;
import com.codechallenge.product.uaa.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;

    private final UserInfoRepository userInfoRepository;

    private final static String BEARER_PREFIX = "Bearer ";

    public final static String[] PUBLIC_ENDPOINTS = {"/uaa/api/v1/login", "/aggregation/api/v1/public/product"};

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.isBlank(requestTokenHeader)) {
            if (Arrays.stream(PUBLIC_ENDPOINTS).anyMatch(pubicEndPoint -> request.getRequestURI().endsWith(pubicEndPoint))) {
                chain.doFilter(request, response);
                return;
            } else {
                throw new UAAException(UAAErrorInfo.InvalidJWTToken);
            }
        }

        String username = null;
        String jwtToken = null;

        if (requestTokenHeader.trim().startsWith(BEARER_PREFIX)) {
            jwtToken = requestTokenHeader.replace(BEARER_PREFIX, "");
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                log.error("Unable to get JWT Token", e);
            } catch (ExpiredJwtException e) {
                log.error("JWT Token has expired", e);
            }
        } else {
            log.warn("JWT Token does not begin with Bearer String");
        }

        final String finalJwtToken = jwtToken;

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            userInfoRepository.findByUsername(username).ifPresent(
                    user -> {
                        // check token validity offline
                        if (jwtTokenUtil.validateToken(finalJwtToken, user)) {
                            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                                    user.getUsername(), null, user.getAuthorities());
                            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                            SecurityContextHolder.getContext().setAuthentication(token);
                        }
                    }
            );
        }
        chain.doFilter(request, response);
    }

}
