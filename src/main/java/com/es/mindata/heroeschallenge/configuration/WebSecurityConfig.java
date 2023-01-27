package com.es.mindata.heroeschallenge.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.es.mindata.heroeschallenge.security.JWTAuthorizationFilter;

import lombok.RequiredArgsConstructor;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

	private final JWTAuthorizationFilter jwtAuthorizationFilter;
	
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {    	
    	return http
    		.csrf().disable()
        	.cors().disable()
        	.authorizeHttpRequests()
        	.antMatchers("/login").permitAll()
        	.anyRequest().authenticated()
        	.and()
        	.sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        	.and()
        	.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
        	.build();
    }
    
    @Bean
    AuthenticationManager authManager(HttpSecurity http, PasswordEncoder encoder) throws Exception {
    	return http.getSharedObject(AuthenticationManagerBuilder.class)
    			.userDetailsService(userDetailsService())
    			.passwordEncoder(passwordEncoder())
    			.and()
    			.build();		
    }
    
    @Bean
    public UserDetailsService userDetailsService() {
    	InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    	manager.createUser(User.withUsername("admin")
    			.password(passwordEncoder().encode("admin"))
    			.roles()
    			.build());
    	return manager;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080/"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
    
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
    	 return (web) -> web.ignoring().antMatchers("/swagger-resources/**", "/swagger-ui/**", "/v2/api-docs/**");
    }
}
