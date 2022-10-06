package com.techyogi.sample.shiro.common;

import com.techyogi.sample.shiro.model.AuthRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

//create a spring boot request filter class to intercept the request implementation Filter
@Component
@Order(1)
public class AuthFilter implements Filter {

    //add logger object
    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    //@Autowired
    SecurityManager securityManager;
    @Autowired
    MyCustomRealm myCustomRealm;
    @Autowired
    DataSource dataSource;
    //create a doFilter method to intercept the request
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, ServletException, IOException {
        //create a http servlet request
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        logger.info("AuthType is : [{}]", request.getAuthType());
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername(request.getParameter("username"));
        authRequest.setPassword(request.getParameter("password"));
        securityManager = new DefaultSecurityManager();
        ((DefaultSecurityManager) securityManager).setRealm(myCustomRealm);
        //securityManager.authenticate(new UsernamePasswordToken(authRequest.getUsername(), authRequest.getPassword()));
        SecurityUtils.setSecurityManager(securityManager);

        //create a subject
        Subject subject = SecurityUtils.getSubject();
        //create a token
        UsernamePasswordToken token = new UsernamePasswordToken(authRequest.getUsername(), authRequest.getPassword());
        token.setRememberMe(false);
        //authenticate the user
        subject.login(token);
        //check if the user is authenticated
        try {
            Set<String> roleIdentifier = myCustomRealm.getRoleNamesForUser(dataSource.getConnection(), authRequest.getUsername());
            //Set<String> permissions = myCustomRealm.getPermissions(dataSource.getConnection(), authRequest.getUsername(),roleIdentifier);
            if (subject.isAuthenticated() && subject.hasRole(roleIdentifier.stream().toArray()[0].toString())) {
                logger.info("user authenticated !!");
                //create a http servlet response
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                //continue the filter chain
                filterChain.doFilter(request, response);
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                logger.info("user not authenticated !!!");
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
