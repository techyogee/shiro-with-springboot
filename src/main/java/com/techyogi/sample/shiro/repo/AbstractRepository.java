package com.techyogi.sample.shiro.repo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AbstractRepository implements BaseRepository {

    static Map<String, String> userMap = new HashMap<>();
    //create a static block to initialize the userMap with admin and password
    static {
        userMap.put("admin", "admin");
    }
    //create a method to fetch the user details
    @Override
    public String fetchUserDetails(String username) {
        //return the response
        return null;
    }

    //create a method to fetch the user roles
    @Override
    public String fetchUserRoles(String username) {
        //return the response
        return null;
    }

    //create a method to fetch the user permissions
    @Override
    public String fetchUserPermissions(String username) {
        //return the response
        return null;
    }

    //create a method to add the user details
    @Override
    public String addUserDetails(String username, String password) {
        //create in memory database to store the user details
        userMap.put(username, password);
        return "User Details Added";
    }

    public boolean isUserExists(String username) {
        return userMap.containsKey(username);
    }
}
