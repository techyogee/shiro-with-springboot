package com.techyogi.sample.shiro.common;

import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class Configurations {

    @Autowired
    DataSource dataSource;

    /*@Autowired
    LifecycleBeanPostProcessor lifecycleBeanPostProcessor;*/
    @Bean(name = "myCustomRealm")
    public Realm realm() {
        return new MyCustomRealm(dataSource);
    }

    /*//@Bean
    public SecurityManager shiroSecurityManager() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(realm());
        securityManager.setCacheManager(cacheManager());
        *//*securityManager.setAuthenticator(authenticator());
        securityManager.setAuthorizer(authorizer());
        securityManager.setSessionManager(sessionManager());*//*
        return securityManager;
    }*/

    @Bean
    public SessionManager sessionManager() {
        return new DefaultSessionManager();
    }


    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // use permissive to NOT require authentication, our controller Annotations will decide that
        chainDefinition.addPathDefinition("/authenticate/**", "authcBasic[permissive]");
        return chainDefinition;
    }

    @Bean
    public CacheManager cacheManager() {
        // Caching isn't needed in this example, but we will use the MemoryConstrainedCacheManager for this example.
        return new MemoryConstrainedCacheManager();
    }
}
