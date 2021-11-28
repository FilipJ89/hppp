package com.pg.hppp.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/h2-console/**").permitAll() // for H2 console free access
                        .antMatchers("/", "/register", "/webjars/**", "/login", "/resources/**").permitAll()
                        .antMatchers("/materials", "/risks").permitAll())
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginProcessingUrl("/login")
                        .loginPage("/login").permitAll()
                        .successForwardUrl("/")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error"))
                .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
                        .logoutSuccessUrl("/?success")
                        .permitAll())
                .httpBasic()
                .and()
                .csrf().disable();

        // H2 console frame config
        http.headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/assets/**");
    }
}
