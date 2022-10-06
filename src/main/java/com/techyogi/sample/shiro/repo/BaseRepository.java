package com.techyogi.sample.shiro.repo;

public interface BaseRepository {

    //create a method to fetch the user details
    public String fetchUserDetails(String username);
    //create a method to fetch the user roles
    public String fetchUserRoles(String username);
    //create a method to fetch the user permissions
    public String fetchUserPermissions(String username);
    //create a method to add the user details
    public String addUserDetails(String username, String password);
}
