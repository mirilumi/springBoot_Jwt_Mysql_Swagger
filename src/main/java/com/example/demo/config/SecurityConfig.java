package com.example.demo.config;

import com.example.demo.security.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    public static final String AUTHENTICATION_HEADER_NAME = "Authorization";
    public static final String AUTHENTICATION_URL = "/api/login";
    public static final String REFRESH_TOKEN_URL = "/api/auth/token";
    public static final String API_ROOT_URL = "/api/**";

    @Autowired CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    @Autowired AuthenticationSuccessHandler successHandler;
    @Autowired AuthenticationFailureHandler failureHandler;
    @Autowired ObjectMapper objectMapper;
    @Autowired AuthenticationManager authenticationManager;
    @Autowired TokenExtractor tokenExtractor;
    @Autowired private CustomAuthenticationProvider customAuthenticationProvider;
    @Autowired private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(customAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(Arrays.toString(AUTH_WHITELIST)).permitAll()
                .antMatchers(API_ROOT_URL).authenticated()
                .and()
                .addFilterBefore(new CustomCorsFilter(),UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildLoginFilter(AUTHENTICATION_URL),UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildJwtFilter(Arrays.asList(AUTH_WHITELIST),
                        API_ROOT_URL), UsernamePasswordAuthenticationFilter.class);
    }

    protected LoginFilter buildLoginFilter(String loginEntryPoint) {
        LoginFilter filter = new LoginFilter(loginEntryPoint,successHandler,failureHandler,objectMapper);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }
    protected JwtFilter buildJwtFilter(List<String> pathsToSkip, String pattern) {
        SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(pathsToSkip, pattern);
        JwtFilter filter
                = new JwtFilter(failureHandler, tokenExtractor, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }
    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/login",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**"
            // other public endpoints of your API may be appended to this array
    };
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
