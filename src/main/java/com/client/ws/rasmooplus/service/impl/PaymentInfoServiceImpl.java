package com.client.ws.rasmooplus.service.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.client.ws.rasmooplus.dto.PaymentProcessDto;
import com.client.ws.rasmooplus.dto.UserPaymentInfoDto;
import com.client.ws.rasmooplus.exception.NotFoundException;
import com.client.ws.rasmooplus.exception.handler.BusinessException;
import com.client.ws.rasmooplus.mapper.UserPaymentInfoMapper;
import com.client.ws.rasmooplus.model.User;
import com.client.ws.rasmooplus.model.UserPaymentInfo;
import com.client.ws.rasmooplus.repository.UserPaymentInfoRepository;
import com.client.ws.rasmooplus.repository.UserRepository;
import com.client.ws.rasmooplus.service.PaymentInfoService;

@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {

	private final UserRepository userRepository;
	private final UserPaymentInfoRepository userPaymentInfoRepository;

	PaymentInfoServiceImpl(UserRepository userRepository, UserPaymentInfoRepository userPaymentInfoRepository) {
		this.userRepository = userRepository;
		this.userPaymentInfoRepository = userPaymentInfoRepository;
	}

	@Override
		public Boolean process(PaymentProcessDto dto) {
			//Verifica usuário por Id e verifica se existe assinatura
			var userOpt = userRepository.findById(dto.getUserPaymentInfoDto().getUserId());
			if(userOpt.isEmpty()) {
				throw new NotFoundException("Usuário não encontrado");
			}
			User user = userOpt.get();
			if(Objects.nonNull(user.getSubscriptionType())) {
				throw new BusinessException("Pagamento não pode ser processado porque o usuário já possui assinatura");
			}

		//Cria ou atualiza usuári no Raspay
		//Cria o pedido de pagamento
		//Processa o pagamento

		//Salvar informações de pagamento
		UserPaymentInfo userPaymentInfo = UserPaymentInfoMapper.fromDtoToEntity(dto.getUserPaymentInfoDto(), user);
		userPaymentInfoRepository.save(userPaymentInfo);

		//Enviar email de confirmação de conta
		//Retorna sucesso ou não do pagamento

		return null;
	}
}
