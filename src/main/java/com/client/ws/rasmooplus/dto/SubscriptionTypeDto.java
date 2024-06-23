package com.client.ws.rasmooplus.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionTypeDto {

	private Long id;

	@Size(min = 5, max = 30, message = "deve ter tamanho entre 5 e 30")
	private String name;

	private Long accessMonths;

	@NotNull(message = "não pode ser nulo")
	private BigDecimal price;

	@Size(min = 5, max = 15, message = "deve ter tamanho entre 5 e 15")
	private String productKey;
}