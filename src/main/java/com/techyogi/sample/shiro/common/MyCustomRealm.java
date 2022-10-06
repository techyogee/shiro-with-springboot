package com.techyogi.sample.shiro.common;

import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

//create a custom realm class to authenticate the user

public class MyCustomRealm extends JdbcRealm {
    //add logger
    private static final Logger logger = LoggerFactory.getLogger(MyCustomRealm.class);
    private static final String AUTHENTICATION_QUERY = "select password from public.user where username = ?";
    private static final String USER_ROLES_QUERY = "select role_name from public.user_role where username = ?";
    private static final String PERMISSIONS_QUERY = "select permission from role_permission where role_name = ?";

    private DataSource dataSource;
    public MyCustomRealm(DataSource dataSource) {
        super();
        logger.info("Custom Realm is loading.... !!!");
        if (dataSource != null) {
            setDataSource(dataSource);
        }else {
            logger.warn("datasource is not initialized properly !!!");
        }
        setAuthenticationQuery(AUTHENTICATION_QUERY);
        setUserRolesQuery(USER_ROLES_QUERY);
        //setPermissionsQuery(PERMISSIONS_QUERY);
        setPermissionsLookupEnabled(false);
        logger.info("Custom Realm is loaded successfully: [{}]", this.getClass().getName());
    }


    @Override
    protected Set<String> getRoleNamesForUser(Connection conn, String username) throws SQLException {
        logger.info("getRoleNamesForUser method called !!!");
        return super.getRoleNamesForUser(conn, username);
    }

    protected Set<String> getPermissions(Connection conn, String username, Set<String> roleNames) throws SQLException {
        logger.info("getPermissions method called !!!");
        Set<String> permissions = new HashSet<>();
        permissions.add("users:*");
        return permissions;
        //return super.getPermissions(conn, username, roleNames);
    }
}
