package my.urlshortener.Controller;

import jakarta.servlet.http.HttpServletResponse;
import my.urlshortener.Service.Base62Encoder;
import my.urlshortener.Service.HashingService;
import my.urlshortener.Service.IdRangeService;
import my.urlshortener.Service.UrlService;
import my.urlshortener.models.UrlMappingEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UrlShortenerController {
    private final UrlService urlService;
    private final IdRangeService idRangeService;

    public UrlShortenerController(UrlService urlService, IdRangeService idRangeService){
        this.urlService = urlService;
        this.idRangeService = idRangeService;
    }



    @GetMapping("/hello")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("hello world");
    }

    @PostMapping(value = "/shorten")
    public String shortener(@RequestBody String longUrl){

        String shortCode = urlService.addMapping(longUrl);
        return "http://localhost:8080/" + shortCode;

    }

    @GetMapping("/{shortCode}")
    public void redirect(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        UrlMappingEntity mapping =  urlService.getShortCode(shortCode);
        response.sendRedirect(mapping.getLongUrl());
    }

}
