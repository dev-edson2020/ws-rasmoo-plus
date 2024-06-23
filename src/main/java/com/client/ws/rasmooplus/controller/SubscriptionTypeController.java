package com.client.ws.rasmooplus.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.client.ws.rasmooplus.dto.SubscriptionTypeDto;
import com.client.ws.rasmooplus.exception.NotFoundException;
import com.client.ws.rasmooplus.model.SubscriptionType;
import com.client.ws.rasmooplus.service.SubscriptionTypeService;

@RestController
@RequestMapping("/subscription-type")
public class SubscriptionTypeController {

	@Autowired
	private SubscriptionTypeService subscriptionTypeService;

	@GetMapping
	public ResponseEntity<List<SubscriptionType>> findAll() {
		return ResponseEntity.status(HttpStatus.OK)
				.body(subscriptionTypeService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubscriptionType> findById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(subscriptionTypeService.findById(id));
	}

	@PostMapping
	public ResponseEntity<SubscriptionType> create(@RequestBody SubscriptionTypeDto dto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(subscriptionTypeService.create(dto));
	}

}
