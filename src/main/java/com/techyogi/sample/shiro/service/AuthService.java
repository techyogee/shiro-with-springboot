package com.techyogi.sample.shiro.service;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    //create a method to authenticate the user
    public String authenticate(String username, String password) throws Exception;

}
