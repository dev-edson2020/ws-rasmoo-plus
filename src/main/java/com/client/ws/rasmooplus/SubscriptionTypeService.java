package com.client.ws.rasmooplus;

import java.util.List;

import com.client.ws.rasmooplus.dto.SubscriptionTypeDto;
import com.client.ws.rasmooplus.model.jpa.SubscriptionType;

public interface SubscriptionTypeService {

	List<SubscriptionType> findAll();

	SubscriptionType findById(Long id);

	SubscriptionType create(SubscriptionTypeDto dto);

	SubscriptionType upadate(Long id, SubscriptionTypeDto dto);

	void delete(Long id);

}
