package my.urlshortner.Service;

import my.urlshortner.models.UrlMappingEntity;
import my.urlshortner.repository.UrlRepository;
import org.springframework.stereotype.Service;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }

    public UrlMappingEntity addMapping(UrlMappingEntity mapping) {
        return urlRepository.save(mapping);
    }

    public UrlMappingEntity getShortCode(String shortCode) {
        UrlMappingEntity mapping = urlRepository.findByShortCode(shortCode);
        if (mapping == null ) throw new RuntimeException("invalid shortcode");
        return mapping;

    }
}
