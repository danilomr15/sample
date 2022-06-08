package br.com.danilomr.cache.service;

import br.com.danilomr.cache.entity.Character;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class TestCacheService {

    @Autowired
    private CacheManager cacheManager;

    private static List<Character> chars = Arrays.asList(
            Character.builder().id(1L).name("Spider Man").description("A man that also is a spider").build(),
            Character.builder().id(2L).name("Wolverine").description("A man that also is a wolf").build(),
            Character.builder().id(3L).name("Batman").description("A man that also is a bat").build()
    );

    @Cacheable(value = "characters", key = "#id")
    public Character getCharacter(final Long id) {
        return findCharById(id);
    }

    @CachePut(value = "characters", key = "#id")
    public Character updateCharacter(final Long id, final Character character) {
        final Character existingCharacter = findCharById(id);
        existingCharacter.setName(character.getName());
        existingCharacter.setDescription(character.getDescription());
        return existingCharacter;
    }

    @CacheEvict(value = "characters", allEntries = true)
    public void deleteAllCache() {

    }

    @CacheEvict(value = "characters", key = "#id")
    public void deleteCacheById(final Long id) {

    }

    public Map<Object, Object> getCaches() {
        CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache("characters");
        Cache<Object, Object> nativeCache = caffeineCache.getNativeCache();
        return nativeCache.asMap();
    }

    private Character findCharById(final Long id) {
        return chars.stream().filter(c -> id.equals(c.getId())).findFirst().orElseThrow(RuntimeException::new);
    }
}
