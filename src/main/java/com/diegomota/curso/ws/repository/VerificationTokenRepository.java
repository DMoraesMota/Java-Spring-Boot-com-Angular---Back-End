package com.diegomota.curso.ws.repository;

import com.diegomota.curso.ws.domain.User;
import com.diegomota.curso.ws.domain.VerificationToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends MongoRepository<VerificationToken, String> {

    Optional<VerificationToken> findByToken(String token);

    Optional<VerificationToken> findByUser(User user);
}
