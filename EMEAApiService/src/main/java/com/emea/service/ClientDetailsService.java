package com.emea.service;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Component;

@Component("clientDetailsService")
public class ClientDetailsService
        implements
            EnvironmentAware,
            org.springframework.security.oauth2.provider.ClientDetailsService {
    private static final Logger LOG = Logger
            .getLogger(ClientDetailsService.class);

    private static final String ENV_OAUTH = "authentication.oauth.";
    private static final String PROP_SECRET = "secret";
    private static final String PROP_TOKEN_VALIDITY_SECONDS = "tokenValidityInSeconds";
    protected static final String AUTHORIZATION_CODE = "authorization_code";
    protected static final String PASSWORD = "password";
    protected static final String REFRESH_TOKEN = "refresh_token";
    protected static final String CLIENT_CREDENTIAL = "client_credentials";
    protected static final String READ = "read";
    protected static final String WRITE = "write";

    @Autowired
    private UserDetailsService userDetailsService;

    private RelaxedPropertyResolver propertyResolver;

    @Override
    public ClientDetails loadClientByClientId(String clientId)
            throws ClientRegistrationException {
        UserDetails user = userDetailsService.loadUserByUsername(clientId);
        BaseClientDetails details = new BaseClientDetails();
        details.setClientId(clientId);
        details.setAuthorizedGrantTypes(Arrays.asList(AUTHORIZATION_CODE,
                PASSWORD, REFRESH_TOKEN, CLIENT_CREDENTIAL));
        details.setScope(Arrays.asList(READ, WRITE));
        details.setClientSecret(propertyResolver.getProperty(PROP_SECRET));
        details.setAuthorities(user.getAuthorities());

        Integer tokenValidityInSeconds = 0;

        try {
            tokenValidityInSeconds = Integer.valueOf(propertyResolver
                    .getProperty(PROP_TOKEN_VALIDITY_SECONDS));
        } catch (NumberFormatException e) {
            LOG.error(
                    "Invalid token validity time configured setting to default 0",
                    e);
        }

        details.setRefreshTokenValiditySeconds(tokenValidityInSeconds);

        return details;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment,
                ENV_OAUTH);

    }

}
