package br.com.danilomr.mongodb.repository;

import br.com.danilomr.mongodb.document.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
