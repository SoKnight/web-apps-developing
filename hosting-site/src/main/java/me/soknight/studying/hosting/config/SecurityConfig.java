package me.soknight.studying.hosting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/admin/**").authenticated()
                        .anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement -> sessionManagement
                        .maximumSessions(1))
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.FORBIDDEN)))
                .formLogin(formLogin -> formLogin
                        .loginPage("/auth/sign-in")
                        .loginProcessingUrl("/auth/sign-in")
                        .usernameParameter("email")
                        .passwordParameter("password"))
                .logout(logout -> logout
                        .logoutUrl("/auth/logout"))
                .build();
    }

}
