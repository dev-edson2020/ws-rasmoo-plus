package com.client.ws.rasmooplus.integration;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.client.ws.rasmooplus.dto.wsraspay.CreditCardDto;
import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.ws.rasmooplus.dto.wsraspay.OrderDto;
import com.client.ws.rasmooplus.dto.wsraspay.PaymentDto;

@SpringBootTest
 class WsRaspayIntegrationImplTest {

	@Autowired
	private WsRaspayIntegration wsRaspayIntegration;

	@Test
	void createCustomerWhenDtoOK() {
		CustomerDto dto = new CustomerDto(null, "27079871824", "edsonanga@hotmail.com", "Edson", "Meira");
		wsRaspayIntegration.createCustomer(dto);
	}

//	@Test
//	void createOrderWhenDtoOK() {
//		OrderDto dto = new OrderDto(null, "6680222138ec4a410cc4f4be", BigDecimal.ZERO , "PERPET22");
//		wsRaspayIntegration.createOrder(dto);
//	}

	@Test
	void processPaymentWhenDtoOK() {
		CreditCardDto creditCardDto = new CreditCardDto(123L, "27079871824", 0L, 06L, "1234123412341234", 2025L);
		PaymentDto paymentDto = new PaymentDto(creditCardDto, "6680222138ec4a410cc4f4be", "6682ed5a38ec4a410cc4f4bf");
		wsRaspayIntegration.processPayment(paymentDto);
	}

}
