package my.urlshortener.Service;

import my.urlshortener.models.UrlMappingEntity;
import my.urlshortener.repository.UrlRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    private final UrlRepository urlRepository;
    private final IdRangeService idRangeService;
    public UrlService(UrlRepository urlRepository, IdRangeService idRangeService){
        this.idRangeService = idRangeService;
        this.urlRepository = urlRepository;

    }

    public String addMapping(String longUrl) {
        String urlHash = HashingService.hashUrl(longUrl);
        UrlMappingEntity exists = urlRepository.findByUrlHash(urlHash);
        if (exists != null) {
            return exists.getShortCode();
        }
        try{
            long id = idRangeService.getNextId();
            String shortCode = Base62Encoder.encode(id);
            UrlMappingEntity urlMapping = new UrlMappingEntity(id,shortCode,urlHash,longUrl );
            urlRepository.save(urlMapping);
            return shortCode;
        } catch (Exception e) {
            System.out.println("save error");
        }
        return "";


    }

    @Cacheable(value = "url_cache", key = "#shortCode")
    public UrlMappingEntity getShortCode(String shortCode) {
        UrlMappingEntity mapping = urlRepository.findByShortCode(shortCode);
        if (mapping == null ) throw new RuntimeException("invalid shortcode");
        return mapping;

    }
}
