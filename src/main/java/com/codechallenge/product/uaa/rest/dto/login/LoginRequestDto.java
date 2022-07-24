package com.codechallenge.product.uaa.rest.dto.login;

import javax.validation.constraints.NotBlank;

public record LoginRequestDto(@NotBlank String username, @NotBlank String password) {}
