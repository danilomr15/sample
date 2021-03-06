package br.com.danilomr.cache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        Caffeine<Object, Object> caffeineCacheBuilder =
                Caffeine.newBuilder()
                        .maximumSize(500)
                        .expireAfterAccess(10, TimeUnit.MINUTES);

        CaffeineCacheManager cacheManager = new CaffeineCacheManager("characters");
        cacheManager.setCaffeine(caffeineCacheBuilder);
        return cacheManager;
    }
}
