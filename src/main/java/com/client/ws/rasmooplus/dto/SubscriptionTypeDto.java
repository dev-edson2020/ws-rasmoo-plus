package com.client.ws.rasmooplus.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
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

	@NotBlank(message = "O campo name não pode ser vazio")
	@Size(min = 5, max = 30, message = "Deve ter o tamanho entre 5 e 30")
	private String name;

	@Max(value= 12, message = "O campo accessMonth não pode ser maior do que 12")
	private Long accessMonths;

	@NotNull(message = "O campo preço não pode ser nulo")
	private BigDecimal price;

	@NotBlank(message = "O campo productKey não pode ser vazio")
	@Size(min = 5, max = 15, message = "Deve ter tamanho entre 5 e 15")
	private String productKey;
}