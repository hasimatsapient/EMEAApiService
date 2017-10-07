package com.emea.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * Spring Security logout handler
 * @author hmolla
 *
 */
@Component
public class CustomLogoutSuccessHandler
        extends AbstractAuthenticationTargetUrlRequestHandler
        implements LogoutSuccessHandler {


    private static final String BEARER_AUTHENTICATION = "Bearer ";
    private static final String HEADER_AUTHORIZATION = "authorization";

    @Autowired
    private TokenStore tokenStore;

    /**
     * Method to clear access token.
     * (non-Javadoc)
     * @see org.springframework.security.web.authentication.logout.LogoutSuccessHandler#onLogoutSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {

        String token = request.getHeader(HEADER_AUTHORIZATION);

        if (token != null && token.startsWith(BEARER_AUTHENTICATION)) {

            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token.split(" ")[0]);

            if (oAuth2AccessToken != null) {
                tokenStore.removeAccessToken(oAuth2AccessToken);
            }

        }

        response.setStatus(HttpServletResponse.SC_OK);

    }

}
