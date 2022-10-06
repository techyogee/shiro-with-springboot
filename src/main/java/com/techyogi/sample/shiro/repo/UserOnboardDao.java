package com.techyogi.sample.shiro.repo;

import com.techyogi.sample.shiro.model.UserInfo;
import com.techyogi.sample.shiro.model.UserInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserOnboardDao {

    private static final Logger logger = LoggerFactory.getLogger(UserOnboardDao.class);
    //create a Insert query
    private static final String INSERT_USER = "INSERT INTO public.user (username, password) VALUES (?, ?)";

    @Autowired
    JdbcTemplate jdbcTemplate;

    //create a method to save the user
    public UserInfoResponse saveUser(UserInfo userInfo) {

        logger.info("user on-boarding !!!");
        int row = jdbcTemplate.update(INSERT_USER, userInfo.getUsername(), userInfo.getPassword());

        UserInfoResponse response = new UserInfoResponse();
        if (row > 0) {
            logger.info("user on-boarding success !!!");
            response.setResponseCode("00");
            response.setResponseMessage("User on-boarding success !!!");
        } else {
            logger.info("user on-boarding failed !!!");
            response.setResponseCode("01");
            response.setResponseMessage("User on-boarding failed !!!");
        }
        return response;
    }
}
