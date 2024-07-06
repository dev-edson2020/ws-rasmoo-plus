package com.client.ws.rasmooplus.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.client.ws.rasmooplus.controller.SubscriptionTypeController;
import com.client.ws.rasmooplus.dto.SubscriptionTypeDto;
import com.client.ws.rasmooplus.exception.BadRequestException;
import com.client.ws.rasmooplus.exception.NotFoundException;
import com.client.ws.rasmooplus.mapper.SubscriptionTypeMapper;
import com.client.ws.rasmooplus.model.SubscriptionType;
import com.client.ws.rasmooplus.repository.jpa.SubscriptionTypeRepository;
import com.client.ws.rasmooplus.service.SubscriptionTypeService;

@Service
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

	private static final String UPDATE = "update";
	private static final String DELETE = "delete";

	private final SubscriptionTypeRepository subscriptionTypeRepository;

	SubscriptionTypeServiceImpl(SubscriptionTypeRepository subscriptionTypeRepository) {
		this.subscriptionTypeRepository = subscriptionTypeRepository;
	}

	@Override
	public List<SubscriptionType> findAll() {
		return subscriptionTypeRepository.findAll();
	}

	@Override
	@Cacheable(value = "subscriptionType", key = "#id")
	public SubscriptionType findById(Long id) {
		return getSubscriptionType(id).add(WebMvcLinkBuilder.linkTo(
						WebMvcLinkBuilder.methodOn(SubscriptionTypeController.class).findById(id))
				.withSelfRel()
		).add(WebMvcLinkBuilder.linkTo(
						WebMvcLinkBuilder.methodOn(SubscriptionTypeController.class).update(id,new SubscriptionTypeDto()))
				.withRel(UPDATE)
		).add(WebMvcLinkBuilder.linkTo(
						WebMvcLinkBuilder.methodOn(SubscriptionTypeController.class).delete(id))
				.withRel(DELETE)
		);
	}

	@Override
	public SubscriptionType create(SubscriptionTypeDto dto) {
		if(Objects.nonNull(dto.getId())) {
			throw new BadRequestException("Id deve ser nulo");
		}
		return subscriptionTypeRepository.save(SubscriptionTypeMapper.fromDtoToEntity(dto));
	}

	@Override
	public SubscriptionType upadate(Long id, SubscriptionTypeDto dto) {
		getSubscriptionType(id);
		dto.setId(id);
		return subscriptionTypeRepository.save(SubscriptionTypeMapper.fromDtoToEntity(dto));
	}

	@Override
	public void delete(Long id) {
		getSubscriptionType(id);
		subscriptionTypeRepository.deleteById(id);
	}

	private SubscriptionType getSubscriptionType(Long id) {
		Optional<SubscriptionType> optionalSubscriptionType = subscriptionTypeRepository.findById(id);
		if(optionalSubscriptionType.isEmpty()) {
			throw  new NotFoundException("SubscriptionType não encontrado");
		}
		return optionalSubscriptionType.get();
	}

}
