package com.client.ws.rasmooplus.integration.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.ws.rasmooplus.dto.wsraspay.OrderDto;
import com.client.ws.rasmooplus.dto.wsraspay.PaymentDto;
import com.client.ws.rasmooplus.integration.WsRaspayIntegration;

@Component
public class WsRaspayIntegrationImpl implements WsRaspayIntegration {

	private final RestTemplate restTemplate;

	public WsRaspayIntegrationImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public CustomerDto createCustomer(CustomerDto dto) {
		try {
			HttpEntity<CustomerDto> request = new HttpEntity<>(dto);
			ResponseEntity<CustomerDto> response = restTemplate.exchange(
					"http://localhost:8081/ws-raspay/v1/customer",
					HttpMethod.POST,
					request,
					CustomerDto.class
			);
			return response.getBody();
		} catch (Exception e) {
			throw new RuntimeException("Failed to create customer", e);
		}
	}

	@Override
	public OrderDto createOrder(OrderDto dto) {
		// Implement the logic for creating an order here
		return null;
	}

	@Override
	public Boolean processPayment(PaymentDto dto) {
		// Implement the logic for processing a payment here
		return null;
	}
}
