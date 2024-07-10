package com.client.ws.rasmooplus;

import java.util.List;

import com.client.ws.rasmooplus.dto.UserDto;
import com.client.ws.rasmooplus.model.jpa.User;

public interface UserService {

	User create(UserDto dto);

	List<User> findAll();

	//	User uploadPhoto(Long id, MultipartFile file) throws IOException;
//
//	byte[] downloadPhoto(Long id);

}
