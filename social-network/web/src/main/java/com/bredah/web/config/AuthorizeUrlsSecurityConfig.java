package com.bredah.web.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class AuthorizeUrlsSecurityConfig {
  
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http.authorizeRequests()
    //             .requestMatchers(request -> request.getServletPath().startsWith("/admin/"))
    //             .hasRole("ADMIN")
    //             .requestMatchers(request -> !request.getServletPath().startsWith("/admin/"))
    //             .hasRole("USER").and().formLogin();
    //     return http.build();
    // }

    // @Bean
    // public UserDetailsService userDetailsService() {
    //     UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password")
    //             .roles("USER").build();
    //     UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("password")
    //             .roles("ADMIN", "USER").build();
    //     return new InMemoryUserDetailsManager(user, admin);
    // }
}
