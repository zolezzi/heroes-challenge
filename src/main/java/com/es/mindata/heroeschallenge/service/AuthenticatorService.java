package com.es.mindata.heroeschallenge.service;

import com.es.mindata.heroeschallenge.dto.JwtResponseDTO;
import com.es.mindata.heroeschallenge.dto.UserDTO;

public interface AuthenticatorService {

	public JwtResponseDTO login(UserDTO user);
}
