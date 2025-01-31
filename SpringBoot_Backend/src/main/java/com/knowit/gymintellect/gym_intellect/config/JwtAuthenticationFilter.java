package com.knowit.gymintellect.gym_intellect.config;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.knowit.gymintellect.gym_intellect.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	 private final AuthenticationManager authenticationManager;
	    private final JwtUtils jwtUtils;
	    private final UserDetailsService userDetailsService;  // Inject UserDetailsService

	    // Constructor with UserDetailsService injection
	    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils, UserDetailsService userDetailsService) {
	        this.authenticationManager = authenticationManager;
	        this.jwtUtils = jwtUtils;
	        this.userDetailsService = userDetailsService;  // Initialize userDetailsService
	    }

	    @Override
	    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	            throws ServletException, IOException {
	        String token = parseJwt(request);
	        if (token != null && jwtUtils.validateJwtToken(token)) {
	            String username = jwtUtils.getUsernameFromJwtToken(token);
	            UserDetails userDetails = userDetailsService.loadUserByUsername(username);  // Use injected UserDetailsService

	            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
	                userDetails, null, userDetails.getAuthorities());
	            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	            SecurityContextHolder.getContext().setAuthentication(authentication);
	        }
	        filterChain.doFilter(request, response);
	    }

	    private String parseJwt(HttpServletRequest request) {
	        String headerAuth = request.getHeader("Authorization");
	        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
	            return headerAuth.substring(7);
	        }
	        return null;
	    }
}
