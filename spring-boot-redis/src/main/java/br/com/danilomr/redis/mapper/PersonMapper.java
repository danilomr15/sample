package br.com.danilomr.redis.mapper;

import br.com.danilomr.redis.controller.dto.PersonDTO;
import br.com.danilomr.redis.entity.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;
import static org.springframework.util.CollectionUtils.isEmpty;

public class PersonMapper {

    public static List<PersonDTO> map(final List<Person> people) {
        if (isEmpty(people)) return new ArrayList<>();
        return people.stream()
                .filter(person -> nonNull(person))
                .map(PersonMapper::map)
                .collect(Collectors.toList());
    }

    public static PersonDTO map(final Person person) {
        return PersonDTO.builder()
                .name(person.getName())
                .age(person.getAge())
                .build();
    }

    public static Person map(final PersonDTO personDTO, final Long timeout) {
        return Person.builder()
                .name(personDTO.getName())
                .age(personDTO.getAge())
                .timeout(timeout)
                .build();
    }
}
