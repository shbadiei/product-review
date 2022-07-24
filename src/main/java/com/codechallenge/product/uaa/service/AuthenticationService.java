package com.codechallenge.product.uaa.service;

public interface AuthenticationService {

    String loginAndGenerateJWT(String username, String password);

}
