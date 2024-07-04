package com.client.ws.rasmooplus.repository.redis;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.client.ws.rasmooplus.model.redis.UserRecoveryCode;

@Repository
public interface UserRecoveryCodeRepository extends CrudRepository<UserRecoveryCode, String> {

	Optional<UserRecoveryCode> findByEmail(String email);
}