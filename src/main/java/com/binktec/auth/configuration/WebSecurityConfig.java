package com.binktec.auth.configuration;

import com.binktec.auth.service.CustomerUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;


@Configuration
@EnableResourceServer
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    private CustomerUserDetailService customerUserDetailService;


    @Autowired
    public WebSecurityConfig(CustomerUserDetailService customerUserDetailService, RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.customerUserDetailService = customerUserDetailService;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerUserDetailService);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
		http.authorizeRequests()
                .and().authorizeRequests().anyRequest().authenticated()
                .and().formLogin().failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and().logout()
                .and().csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint);
    }
}