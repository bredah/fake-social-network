package com.bredah.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {

        @Bean
        @Profile("dev")
        SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
                return httpSecurity
                                .csrf(csrf -> csrf.disable())
                                .headers((headers) -> headers
                                                .defaultsDisabled()
                                                .frameOptions(frameOptions -> frameOptions
                                                                .sameOrigin())) // habilita acessar o H2
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/oauth/token").permitAll().anyRequest()
                                                .authenticated()
                                                .anyRequest().anonymous())
                                .build();

        }

}
