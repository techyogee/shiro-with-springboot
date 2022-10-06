package com.techyogi.sample.shiro.controller;

import com.techyogi.sample.shiro.common.AuthException;
import com.techyogi.sample.shiro.model.AuthRequest;
import com.techyogi.sample.shiro.service.LoginService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

//create a rest controller class with a http post method
@RestController
@RequestMapping("/authenticate")
public class LoginController {
    //add logger
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    LoginService loginService;

    //create a post method to authenticate the user
    @PostMapping("/admin/login")
    @RequiresPermissions("users:*")
    @ResponseBody
    public String adm_login(@RequestParam("username") String username, @RequestParam("password") String password) {
        try {
            logger.info("user attempting to login !!!");
            String msg = loginService.authenticate(username, password);
            return msg;
        } catch (AuthException e) {
            throw new RuntimeException(e);
        }

    }

    //create a post method with AuthRequest object
    @PostMapping(name = "/manager/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    @RequiresPermissions("users:create,read")
    public String mgmt_login(@RequestBody AuthRequest authRequest) {
        try {
            logger.info("user attempting to login !!!");
            String msg = loginService.authenticate(authRequest.getUsername(), authRequest.getPassword());
            return msg;
        } catch (AuthException e) {
            throw new RuntimeException(e);
        }
    }
}
