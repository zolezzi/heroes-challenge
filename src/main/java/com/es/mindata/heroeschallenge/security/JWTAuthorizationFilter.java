package com.es.mindata.heroeschallenge.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.es.mindata.heroeschallenge.utils.TokenUtil;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter{

	@Autowired
	private TokenUtil tokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String bearerToken = request.getHeader("Authorization");
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			String token = bearerToken.replace("Bearer ", "");
			var usernamePAT = tokenUtil.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(usernamePAT);
		}
		filterChain.doFilter(request, response);
	}

}
