package my.urlshortener.repository;

import my.urlshortener.models.UrlMappingEntity;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlMappingEntity, Long> {

    UrlMappingEntity findByShortCode(String shortCode);

    @Cacheable(value = "hash_cache", key = "#urlHash")
    UrlMappingEntity findByUrlHash(String urlHash);
}
