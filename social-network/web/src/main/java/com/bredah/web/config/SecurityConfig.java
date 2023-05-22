package com.bredah.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  //  @Override
    // protected void configure(HttpSecurity http) throws Exception {
    //     http
    //         .authorizeRequests()
    //             .antMatchers("/publico").permitAll() // Adiciona a exclusão para o endpoint /publico
    //             .anyRequest().authenticated()
    //             .and()
    //         .formLogin()
    //             .loginPage("/login")
    //             .permitAll()
    //             .and()
    //         .logout()
    //             .permitAll();
    // }

}
