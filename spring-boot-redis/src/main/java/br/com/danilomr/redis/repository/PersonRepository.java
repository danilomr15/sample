package br.com.danilomr.redis.repository;

import br.com.danilomr.redis.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends CrudRepository<Person, String> {

    Optional<Person> findByName(String name);

    List<Person> findAll();
}
