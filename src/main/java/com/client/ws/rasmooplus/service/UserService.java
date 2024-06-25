package com.client.ws.rasmooplus.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.client.ws.rasmooplus.dto.UserDto;
import com.client.ws.rasmooplus.model.User;

public interface UserService {

	User create(UserDto dto);
//	User uploadPhoto(Long id, MultipartFile file) throws IOException;
//
//	byte[] downloadPhoto(Long id);

}
