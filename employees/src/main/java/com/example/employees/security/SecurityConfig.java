package com.example.employees.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests( configuerer ->
                        configuerer
                                .requestMatchers(HttpMethod.GET,"/h2-console/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/h2-console/**").permitAll()

                                .requestMatchers( "/swagger-ui/**",
                                        "/v3/api-docs/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST,"/api/employees/**").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/employees/**").hasAnyRole("MANAGER", "ADMIN")
                                .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")

        );

        http.httpBasic(httpBasicCustomizer ->httpBasicCustomizer.disable());

        //use https basic autjetication
        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf -> csrf.disable());

        http.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(authenticationEntryPoint()));

        http.headers(headers -> headers.disable());
        return http.build();
    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {

        return (request, response, e) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");
            response.setHeader("WWW-Authenticate", "");
            response.getWriter().write("{\"error\":\"unauthorized access\"}");
        };
    }

}
