package com.client.ws.rasmooplus.service.impl;

import java.io.IOException;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.client.ws.rasmooplus.dto.UserDto;
import com.client.ws.rasmooplus.exception.BadRequestException;
import com.client.ws.rasmooplus.exception.NotFoundException;
import com.client.ws.rasmooplus.mapper.UserMapper;
import com.client.ws.rasmooplus.model.User;
import com.client.ws.rasmooplus.model.UserType;
import com.client.ws.rasmooplus.repository.UserRepository;
import com.client.ws.rasmooplus.repository.UserTypeRepository;
import com.client.ws.rasmooplus.service.UserService;

@Service
public class UserServiceImpl implements UserService {

//	private static final String PNG = ".png";
//	private static final String JPEG = ".jpeg";

	private final UserRepository userRepository;
	private final UserTypeRepository userTypeRepository;

	UserServiceImpl(UserRepository userRepository, UserTypeRepository userTypeRepository){
		this.userRepository = userRepository;
		this.userTypeRepository = userTypeRepository;
	}

	@Override
	public User create(UserDto dto) {

		if (Objects.nonNull(dto.getId())) {
			throw new BadRequestException("id deve ser nulo");
		}

		var userTypeOpt = userTypeRepository.findById(dto.getUserTypeId());

		if (userTypeOpt.isEmpty()) {
			throw new NotFoundException("userTypeId não encontrado");
		}

		UserType userType = userTypeOpt.get();
		User user = UserMapper.fromDtoToEntity(dto, userType, null);
		return userRepository.save(user);
	}

//	@Override
//	public User uploadPhoto(Long id, MultipartFile file) throws IOException {
//		return null;
//	}
//
//	@Override
//	public byte[] downloadPhoto(Long id) {
//		User user = findById(id);
//		if (Objects.isNull(user.getPhoto())) {
//			throw new BadRequestException("Usuário não possui foto");
//		}
//		return user.getPhoto();
//	}

//	private User findById(Long id) {
//		return userRepository.findById(id).orElseThrow(() -> new NotFoundException("Usuário não encontrado"));
//	}

}
