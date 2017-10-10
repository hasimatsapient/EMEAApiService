package com.emea.config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.emea.controller.CustomControllerAdvice;
import com.emea.security.CustomAuthenticationEntryPoint;
import com.emea.security.CustomLogoutSuccessHandler;

@Configuration
public class OAuth2Configuration {
    private static final Logger LOG = Logger
            .getLogger(OAuth2Configuration.class);
    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration
            extends
                ResourceServerConfigurerAdapter {
        
       

        @Autowired
        private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

        @Autowired
        private CustomLogoutSuccessHandler customLogoutSuccessHandler;

        @Override
        public void configure(HttpSecurity http) throws Exception {

            http.exceptionHandling()
                    .authenticationEntryPoint(customAuthenticationEntryPoint)
                    .and()
                    .logout()
                    .logoutUrl("/oauth/logout")
                    .logoutSuccessHandler(customLogoutSuccessHandler)
                    .and()
                    .csrf()
                    .requireCsrfProtectionMatcher(
                            new AntPathRequestMatcher("/oauth/authorize"))
                    .disable().headers().frameOptions().disable().and()
                    .authorizeRequests()
                    .antMatchers("/emeaapiservice/**").authenticated();

        }

    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration
            extends
                AuthorizationServerConfigurerAdapter
            implements
                EnvironmentAware {

        private static final String ENV_OAUTH = "authentication.oauth.";
        private static final String PROP_CLIENTID = "clientid";
        private static final String PROP_SECRET = "secret";
        private static final String PROP_TOKEN_VALIDITY_SECONDS = "tokenValidityInSeconds";
        protected static final String AUTHORIZATION_CODE = "authorization_code";
        protected static final String PASSWORD = "password";
        protected static final String REFRESH_TOKEN = "refresh_token";
        protected static final String CLIENT_CREDENTIAL = "client_credentials";
        protected static final String READ = "read";
        protected static final String WRITE = "write";

        private RelaxedPropertyResolver propertyResolver;
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
            return new ClientDetailsService() {

                @Override
                public ClientDetails loadClientByClientId(String clientId)
                        throws ClientRegistrationException {
                   UserDetails user= userDetailsService.loadUserByUsername(clientId);
                   BaseClientDetails details = new BaseClientDetails();
                   details.setClientId(clientId);
                   details.setAuthorizedGrantTypes(Arrays.asList(
                           AUTHORIZATION_CODE, PASSWORD, REFRESH_TOKEN,
                           CLIENT_CREDENTIAL));
                   details.setScope(Arrays.asList(READ,  WRITE));
                   details.setClientSecret(propertyResolver
                           .getProperty(PROP_SECRET));
                   details.setAuthorities(user.getAuthorities());
                   
                   Integer tokenValidityInSeconds=0;
                   
                   try {
                       tokenValidityInSeconds= Integer.valueOf( propertyResolver
                               .getProperty(PROP_TOKEN_VALIDITY_SECONDS));
                   } catch (NumberFormatException e) {
                       LOG.error("Invalid token validity time configured setting to default 0", e);
                   }
                   
                   details.setRefreshTokenValiditySeconds(tokenValidityInSeconds);
                    
                    return details;
                }
            };
        }
        @Override
        public void configure(ClientDetailsServiceConfigurer clients)
                throws Exception {
            clients.withClientDetails(clientDetailsService());
        }

        @Override
        public void setEnvironment(Environment environment) {
            this.propertyResolver = new RelaxedPropertyResolver(environment,
                    ENV_OAUTH);
        }

    }

}
