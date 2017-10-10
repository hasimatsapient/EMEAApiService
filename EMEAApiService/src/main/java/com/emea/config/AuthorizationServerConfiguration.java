package com.emea.config;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration
        extends
            AuthorizationServerConfigurerAdapter {

    private static final Logger LOG = Logger
            .getLogger(AuthorizationServerConfiguration.class);

    protected static final String AUTHORIZATION_CODE = "authorization_code";
    protected static final String PASSWORD = "password";
    protected static final String REFRESH_TOKEN = "refresh_token";
    protected static final String CLIENT_CREDENTIAL = "client_credentials";
    protected static final String READ = "read";
    protected static final String WRITE = "write";

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
        endpoints.tokenStore(tokenStore()).authenticationManager(
                authenticationManager);
    }

    @Bean
    public ClientDetailsService clientDetailsService() {
        return new com.emea.service.ClientDetailsService();
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        clients.withClientDetails(clientDetailsService());
    }

}
