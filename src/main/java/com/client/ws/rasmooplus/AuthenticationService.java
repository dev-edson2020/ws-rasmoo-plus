package com.client.ws.rasmooplus;

import org.springframework.context.annotation.Bean;

import com.client.ws.rasmooplus.dto.LoginDto;
import com.client.ws.rasmooplus.dto.TokenDto;

public interface AuthenticationService {

	TokenDto auth(LoginDto dto);

}