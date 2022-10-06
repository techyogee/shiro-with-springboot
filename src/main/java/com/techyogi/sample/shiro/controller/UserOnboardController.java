package com.techyogi.sample.shiro.controller;

import com.techyogi.sample.shiro.model.UserInfo;
import com.techyogi.sample.shiro.model.UserInfoResponse;
import com.techyogi.sample.shiro.repo.UserOnboardDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/onboard")
public class UserOnboardController {

    private static final Logger logger = LoggerFactory.getLogger(UserOnboardController.class);

    @Autowired
    UserOnboardDao userOnboardDao;
    //create a post method to onboard the user
    @PostMapping(path = "/adduser", consumes = "application/json", produces = "application/json")
    public UserInfoResponse onboardUser(@RequestBody UserInfo userInfo) {
        logger.info("user onboarding !!!");
        return userOnboardDao.saveUser(userInfo);
    }
}
