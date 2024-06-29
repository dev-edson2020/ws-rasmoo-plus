package com.client.ws.rasmooplus.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;

@SpringBootTest
 class WsRaspayIntegrationImplTest {

	@Autowired
	private WsRaspayIntegration wsRaspayIntegration;

	@Test
	void createCustomerWhenDtoOK() {
		CustomerDto dto = new CustomerDto(null, "27079871824", "edsonanga@hotmail.com", "Edson", "Meira");
		wsRaspayIntegration.createCustomer(dto);
	}

}
