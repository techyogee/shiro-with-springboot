package com.techyogi.sample.shiro.repo;

import org.springframework.stereotype.Repository;

@Repository
public class LoginDao extends AbstractRepository{

    @Override
    public String fetchUserDetails(String username) {
        if (username != null && isUserExists(username)) {
            return userMap.get(username);
        } else {
            return null;
        }
    }
}
