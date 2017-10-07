package com.emea.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

import com.emea.security.Authorities;
import com.emea.security.CustomAuthenticationEntryPoint;
import com.emea.security.CustomLogoutSuccessHandler;

@Configuration
public class OAuth2Configuration {

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

        private RelaxedPropertyResolver propertyResolver;

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
                    BaseClientDetails details = new BaseClientDetails();
                    details.setClientId(clientId);
                    details.setAuthorizedGrantTypes(Arrays.asList(
                            "authorization_code", "password", "refresh_token",
                            "client_credentials"));
                    details.setScope(Arrays.asList("read, trust", "write"));
                    details.setClientSecret(propertyResolver
                            .getProperty(PROP_SECRET));
                    Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
                    authorities.add(new SimpleGrantedAuthority(
                            Authorities.ROLE_CLIENT.toString()));
                    authorities.add(new SimpleGrantedAuthority(
                            Authorities.ROLE_ADMIN.toString()));
                    authorities.add(new SimpleGrantedAuthority(
                            Authorities.ROLE_USER.toString()));
                    details.setAuthorities(authorities);
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
