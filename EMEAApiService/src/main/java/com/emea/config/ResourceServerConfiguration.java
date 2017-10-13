package com.emea.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.emea.security.CustomAuthenticationEntryPoint;
import com.emea.security.CustomLogoutSuccessHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration
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
                .disable().headers().frameOptions().disable()
                .and().authorizeRequests()
                .antMatchers("/emeaapiservice/swagger-ui.html")
          .permitAll()
                .and()
                .authorizeRequests().antMatchers("/emeaapiservice/**")
                .authenticated()
                
                ;

    }

}
