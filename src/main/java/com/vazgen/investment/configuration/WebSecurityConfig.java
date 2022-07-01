package com.vazgen.investment.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vazgen.investment.security.entrypoint.JwtAuthenticationEntryPoint;
import com.vazgen.investment.security.filter.JwtCredentialsAuthenticationProcessingFilter;
import com.vazgen.investment.security.filter.UserCredentialsAuthenticationProcessingFilter;
import com.vazgen.investment.security.handler.CommonAuthenticationFailureHandler;
import com.vazgen.investment.security.handler.TokenResponseAuthenticationSuccessHandler;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import java.util.List;

@Configuration
// TODO: 29.06.2022 Зачем эти аннотация?
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final List<AuthenticationProvider> providers;
    private final ObjectMapper objectMapper;
    private final CommonAuthenticationFailureHandler failureHandler;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .formLogin().disable()
                .httpBasic().disable()
                .csrf().disable()
                .logout().disable()
                .addFilterBefore(
                        userCredentialsAuthenticationProcessingFilter(),
                        UsernamePasswordAuthenticationFilter.class
                )
                .addFilterBefore(
                        getJwtAuthenticationFilter(),
                        UserCredentialsAuthenticationProcessingFilter.class
                )
                .sessionManagement().disable()
                .authorizeRequests()
                .antMatchers(
                        "/v1/contribution/trigger",
                        "/auth/signin",
                        "/auth/signup"
                )
                .permitAll()
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(@NonNull final AuthenticationManagerBuilder auth) {
        for (AuthenticationProvider provider : providers) {
            auth.authenticationProvider(provider);
        }
    }

    private Filter getJwtAuthenticationFilter() throws Exception {
        return new JwtCredentialsAuthenticationProcessingFilter(authenticationManagerBean(), new JwtAuthenticationEntryPoint());
    }

    private Filter userCredentialsAuthenticationProcessingFilter() {
        UserCredentialsAuthenticationProcessingFilter filter = new UserCredentialsAuthenticationProcessingFilter(objectMapper);
        try{
            filter.setAuthenticationManager(authenticationManagerBean());
            filter.setAuthenticationSuccessHandler(new TokenResponseAuthenticationSuccessHandler(objectMapper));
            filter.setAuthenticationFailureHandler(failureHandler);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return filter;
    }

}
