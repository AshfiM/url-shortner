package my.urlshortner.repository;

import my.urlshortner.models.UrlMappingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlMappingEntity, Long> {

    public UrlMappingEntity findByShortCode(String shortCode);
}
