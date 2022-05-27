package br.com.danilomr.redis.controller;

import br.com.danilomr.redis.controller.dto.PersonDTO;
import br.com.danilomr.redis.mapper.PersonMapper;
import br.com.danilomr.redis.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(path = "/cache")
public class CacheController {

    @Autowired
    private PersonService personService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public PersonDTO findByName(@RequestParam("name") final String name) {
        return PersonMapper.map(personService.findByName(name));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    public List<PersonDTO> findAll() {
        return PersonMapper.map(personService.findAll());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@Valid @RequestBody final PersonDTO personDTO) {
        personService.save(personDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping
    public void deleteByName(@RequestParam("name") final String name) {
        personService.deleteByName(name);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/all")
    public void deleteAll() {
        personService.deleteAll();
    }
}
