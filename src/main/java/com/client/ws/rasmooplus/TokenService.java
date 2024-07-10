package com.client.ws.rasmooplus;

public interface TokenService {

	String getToken(Long userId);

	Boolean isValid(String token);

	Long getUserId(String token);

}