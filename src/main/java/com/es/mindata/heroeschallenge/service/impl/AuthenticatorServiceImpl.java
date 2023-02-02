package com.es.mindata.heroeschallenge.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.es.mindata.heroeschallenge.dto.JwtResponseDTO;
import com.es.mindata.heroeschallenge.dto.UserDTO;
import com.es.mindata.heroeschallenge.entity.User;
import com.es.mindata.heroeschallenge.repository.UserRepository;
import com.es.mindata.heroeschallenge.service.AuthenticatorService;
import com.es.mindata.heroeschallenge.utils.TokenUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticatorServiceImpl implements AuthenticatorService{

	private final UserRepository repository;
    private final AuthenticationManager authenticationManager;
    private final TokenUtil tokenUtil;
	
    @Override
	public JwtResponseDTO login(UserDTO user) {
		authenticate(user.getUsername(), user.getPassword());
		var userDetails = (User) repository.findOneByUsername(user.getUsername())
				.orElseThrow(() -> new UsernameNotFoundException(String.format("No found user:%s", user.getUsername())));
        var token = tokenUtil.createToken(userDetails.getUsername());
		return new JwtResponseDTO(userDetails.getUsername(), token);
	}
	
    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
