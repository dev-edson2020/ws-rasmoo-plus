package com.client.ws.rasmooplus.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.client.ws.rasmooplus.dto.PaymentProcessDto;
import com.client.ws.rasmooplus.dto.wsraspay.CustomerDto;
import com.client.ws.rasmooplus.dto.wsraspay.OrderDto;
import com.client.ws.rasmooplus.dto.wsraspay.PaymentDto;
import com.client.ws.rasmooplus.exception.NotFoundException;
import com.client.ws.rasmooplus.exception.handler.BusinessException;
import com.client.ws.rasmooplus.integration.MailIntegration;
import com.client.ws.rasmooplus.integration.WsRaspayIntegration;
import com.client.ws.rasmooplus.mapper.UserPaymentInfoMapper;
import com.client.ws.rasmooplus.mapper.wsraspay.CreditCardMapper;
import com.client.ws.rasmooplus.mapper.wsraspay.CustomerMapper;
import com.client.ws.rasmooplus.mapper.wsraspay.OrderMapper;
import com.client.ws.rasmooplus.mapper.wsraspay.PaymentMapper;
import com.client.ws.rasmooplus.model.User;
import com.client.ws.rasmooplus.model.UserPaymentInfo;
import com.client.ws.rasmooplus.repository.UserPaymentInfoRepository;
import com.client.ws.rasmooplus.repository.UserRepository;
import com.client.ws.rasmooplus.service.PaymentInfoService;

@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {

	private final UserRepository userRepository;
	private final UserPaymentInfoRepository userPaymentInfoRepository;
	private final WsRaspayIntegration wsRaspayIntegration;
	private final MailIntegration mailIntegration;

	PaymentInfoServiceImpl(UserRepository userRepository, UserPaymentInfoRepository userPaymentInfoRepository,
			WsRaspayIntegration wsRaspayIntegration, MailIntegration mailIntegration) {
		this.userRepository = userRepository;
		this.userPaymentInfoRepository = userPaymentInfoRepository;
		this.wsRaspayIntegration = wsRaspayIntegration;
		this.mailIntegration = mailIntegration;
	}

	@Override
	public Boolean process(PaymentProcessDto dto) {
		//Verifica usuário por Id e verifica se existe assinatura
		var userOpt = userRepository.findById(dto.getUserPaymentInfoDto()
				.getUserId());
		if(userOpt.isEmpty()) {
			throw new NotFoundException("Usuário não encontrado");
		}
		User user = userOpt.get();
		if(Objects.nonNull(user.getSubscriptionType())) {
			throw new BusinessException("Pagamento não pode ser processado porque o usuário já possui assinatura");
		}

		//Cria ou atualiza usuári no Raspay
		CustomerDto customerDto = wsRaspayIntegration.createCustomer(CustomerMapper.build(user));
		//Cria o pedido de pagamento
		OrderDto orderDto = wsRaspayIntegration.createOrder(OrderMapper.build(customerDto.getId(), dto));
		//Processa o pagamento
		PaymentDto paymentDto = PaymentMapper.build(customerDto.getId(), orderDto.getId(),
				CreditCardMapper.build(dto.getUserPaymentInfoDto(), user.getCpf()));
		Boolean successPayment = wsRaspayIntegration.processPayment(paymentDto);
		if(Boolean.TRUE.equals(successPayment)) {
			//Salvar informações de pagamento
			UserPaymentInfo userPaymentInfo = UserPaymentInfoMapper.fromDtoToEntity(dto.getUserPaymentInfoDto(), user);
			userPaymentInfoRepository.save(userPaymentInfo);
			mailIntegration.send(user.getEmail(), "Usuário: "+user.getEmail()+" - Senha: alunorasmoo", "Acesso liberado");
		    return true;
		}
		//Enviar email de confirmação de conta
		//Retorna sucesso ou não do pagamento

		return false;
	}
}
