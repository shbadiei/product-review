package com.codechallenge.product.uaa.rest;

import com.codechallenge.product.uaa.rest.dto.login.LoginRequestDto;
import com.codechallenge.product.uaa.rest.dto.login.LoginResponseDto;
import com.codechallenge.product.uaa.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/uaa/api/v1")
public class AuthenticationResource {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        return ResponseEntity.ok(
                new LoginResponseDto(
                        authenticationService.loginAndGenerateJWT(requestDto.username(), requestDto.password())
                )
        );
    }

}
