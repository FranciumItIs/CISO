package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.service.UserService;

//@Configuration
//@EnableWebSecurity
public class SecurityConfig {

	@Autowired
    private UserService userService;
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                .requestMatchers("/", "/login**", "/error**", "/logout", "/logoutSuccess").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(formLogin -> 
	            formLogin
	                .loginPage("/login")
	                .permitAll()
	        )
            .logout(logout ->
                logout
                    .logoutSuccessUrl("/logoutSuccess")
                    .permitAll()
            );
        return http.build();
    }
}