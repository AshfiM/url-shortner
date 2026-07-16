package my.urlshortener.Controller;

import jakarta.servlet.http.HttpServletResponse;
import my.urlshortener.Service.Base62Encoder;
import my.urlshortener.Service.HashingService;
import my.urlshortener.Service.IdRangeService;
import my.urlshortener.Service.UrlService;
import my.urlshortener.models.UrlMappingEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class UrlShortenerController {
    @Value("${DOMAIN}")
    private  String DOMAIN;
    @Value("${server.port}")
    private  String PORT;

    private final UrlService urlService;
    //private final IdRangeService idRangeService;

    public UrlShortenerController(UrlService urlService, IdRangeService idRangeService){
        this.urlService = urlService;
        //this.idRangeService = idRangeService;
    }



    @GetMapping("/hello")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("hello world");
    }

    @PostMapping(value = "/create-short-url")
    public String createShortUrl(@RequestBody String longUrl){

        String shortCode = urlService.createShortUrl(longUrl);
        return DOMAIN+":"+PORT+"/api/show-page/"+shortCode;

    }

    @GetMapping("/show-page/{shortCode}")
    public void redirect(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        var longUrl = urlService.findShortCode(shortCode);
        response.sendRedirect(longUrl);
    }

}
