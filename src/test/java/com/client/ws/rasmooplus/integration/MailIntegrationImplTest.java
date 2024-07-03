package com.client.ws.rasmooplus.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.client.ws.rasmooplus.dto.wsraspay.CreditCardDto;
import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.ws.rasmooplus.dto.wsraspay.PaymentDto;

@SpringBootTest
 class MailIntegrationImplTest {

	@Autowired
	private Mailintegration mailintegration;

	@Test
	void createCustomerWhenDtoOK() {
		mailintegration.send("rasmoocontateste@gmail.com", "Teste enviando email", "Acesso liberado");
	}

}
