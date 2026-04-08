package my.urlshortner.controller;

import jakarta.servlet.http.HttpServletResponse;
import my.urlshortner.Service.Base62Encoder;
import my.urlshortner.Service.IdRangeService;
import my.urlshortner.Service.UrlService;
import my.urlshortner.models.UrlMappingEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UrlShortnerController {
    private final UrlService urlService;
    private final IdRangeService idRangeService;

    public UrlShortnerController(UrlService urlService, IdRangeService idRangeService){
        this.urlService = urlService;
        this.idRangeService = idRangeService;
    }


    @GetMapping("/hello")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("hello world");
    }

    @PostMapping(value = "/shorten")
    public String shortener(@RequestBody String longUrl){
        long id = idRangeService.getNextId();
        String shortCode = Base62Encoder.encode(id);
        UrlMappingEntity urlMapping = new UrlMappingEntity(id,shortCode,longUrl);
        urlService.addMapping(urlMapping);
        return "http://localhost:8080/" + shortCode;

    }

    @GetMapping("/{shortCode}")
    public void redirect(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        UrlMappingEntity mapping =  urlService.getShortCode(shortCode);
        response.sendRedirect(mapping.getLongUrl());
    }

}
