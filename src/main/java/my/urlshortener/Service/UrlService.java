package my.urlshortener.Service;

import lombok.extern.slf4j.Slf4j;
import my.urlshortener.models.UrlMappingEntity;
import my.urlshortener.repository.UrlRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static io.lettuce.core.ShutdownArgs.Builder.save;

@Service
@Slf4j
public class UrlService {
    private final UrlRepository urlRepository;
    private final IdRangeService idRangeService;
    public UrlService(UrlRepository urlRepository, IdRangeService idRangeService){
        this.idRangeService = idRangeService;
        this.urlRepository = urlRepository;

    }

    public String createShortUrl(String longUrl) {
        var urlHash = HashingService.hashUrl(longUrl);
        UrlMappingEntity urlMappingEntity = UrlMappingEntity.builder()
                .longUrl(longUrl)
                .urlHash(urlHash)
                .build();
        try {
            UrlMappingEntity urlMapping = urlRepository.save(urlMappingEntity);
            var shortCode = Base62Encoder.encode(urlMapping.getId());
            urlMapping.setShortCode(shortCode);
            urlRepository.save(urlMapping);
            return shortCode;
        } catch (DataIntegrityViolationException ex) {
            return urlRepository.findByUrlHash(urlHash).getShortCode();
        }


    }

//    @Cacheable(value = "url_cache", key = "#shortCode")
    public String findShortCode(String shortCode) {
        try {
            return urlRepository.findByShortCode(shortCode).getLongUrl();
        } catch (RuntimeException ex) {
            log.info("invalid short code :{}", ex.getMessage());
            return "";
        }

    }
}
