package br.com.danilomr.cache.controller;

import br.com.danilomr.cache.entity.Character;
import br.com.danilomr.cache.service.TestCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/cache")
public class TestCacheController {

    @Autowired
    private TestCacheService testCacheService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    Map<Object, Object> getAll() {
        return testCacheService.getCaches();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    Character findById(@PathVariable("id") final Long id) {
        return testCacheService.getCharacter(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    Character update(@PathVariable("id") final Long id,
                     @RequestBody final Character character) {
        return testCacheService.updateCharacter(id, character);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    void deleteAll() {
        testCacheService.deleteAllCache();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") final Long id) {
        testCacheService.deleteCacheById(id);
    }
}
