package com.knowit.gymintellect.gym_intellect.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.knowit.gymintellect.gym_intellect.utils.JwtUtils;
import com.knowit.gymintellect.gym_intellect.config.JwtAuthenticationFilter;
import com.knowit.gymintellect.gym_intellect.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    // AuthenticationManager Bean
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = 
            http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    // PasswordEncoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Security Filter Chain Bean
//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()  // Disable CORS and CSRF for stateless authentication
//            .authorizeHttpRequests()  // Replaced authorizeRequests() with authorizeHttpRequests()
//            .requestMatchers("/api/auth/**").permitAll()  // Allow public access to auth endpoints
//            .anyRequest().authenticated()  // All other endpoints require authentication
//            .and()
//            .addFilterBefore(new JwtAuthenticationFilter(authenticationManager(http), jwtUtils, userDetailsService), 
//                             UsernamePasswordAuthenticationFilter.class)
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);  // Stateless authentication
//
//        return http.build();
//    }
//	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeRequests()
            .requestMatchers("/api/auth/**").permitAll()  // Public access to auth endpoints
            .requestMatchers("/api/admin/**").permitAll()  // Admin-only access
            .requestMatchers("/api/gym-profile/create").permitAll()
            .requestMatchers("/api/gym-profile/gym-profiles/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(authenticationManager(http), jwtUtils, userDetailsService), 
                             UsernamePasswordAuthenticationFilter.class)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
}
