package br.com.danilomr.redis.service;

import br.com.danilomr.redis.controller.dto.PersonDTO;
import br.com.danilomr.redis.entity.Person;
import br.com.danilomr.redis.exception.APIException;
import br.com.danilomr.redis.mapper.PersonMapper;
import br.com.danilomr.redis.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static br.com.danilomr.redis.utils.Constants.PERSON_NOT_FOUND_WITH_NAME;

@Service
public class PersonService {

    @Value("${timeout}")
    private Long timeout;

    @Autowired
    private PersonRepository personRepository;

    public Person findByName(final String name) {
        Optional<Person> person = personRepository.findById(name);
        if(person.isPresent()) {
            return person.get();
        }
        throw new APIException(String.format(PERSON_NOT_FOUND_WITH_NAME, name), HttpStatus.NOT_FOUND);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public void deleteByName(final String name) {
        final Person person = findByName(name);
        personRepository.delete(person);
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }

    public void save(final PersonDTO personDTO) {
        personRepository.save(PersonMapper.map(personDTO, timeout));
    }
}
