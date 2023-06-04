package com.bredah.web.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private static final String[] ALLOW_LIST_PATH = {
      "/usuario",
      "/mensagem",
      "/actuator/**"
  };

  private static final String[] DENY_LIST_PATH = {
      "/timeline/mensagens",
      "/usuario/admin",
  };

  @Bean
  BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(6);
  }

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
            .requestMatchers(HttpMethod.POST, "/usuario").permitAll() // Permite o cadastro de usuário sem autenticação
            .requestMatchers(ALLOW_LIST_PATH).permitAll()
            .requestMatchers(PathRequest.toH2Console()).permitAll() // habilita acessar o H2
            .requestMatchers(DENY_LIST_PATH).hasRole("ADMIN").anyRequest().authenticated())
        .build();

  }

}
