package com.techyogi.sample.shiro.service;

import com.techyogi.sample.shiro.common.AuthException;
import com.techyogi.sample.shiro.repo.LoginDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements AuthService {

    @Autowired
    LoginDao loginDao;
    //create a method to authenticate the user
    @Override
    public String authenticate(String username, String password) throws AuthException {
        //return the response
        if (!username.isEmpty() && !password.isEmpty()) {
            String pwd = loginDao.fetchUserDetails(username);
            if (pwd != null && pwd.equals(password)) {
                return "User Authenticated, user can login";
            } else {
                throw new AuthException("Username does not exist, User Authentication Failed");
            }
        } else {
            throw new AuthException("Username or Password is empty, User Authentication Failed");
        }
    }

}
