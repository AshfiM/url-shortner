package my.urlshortener.Controller;



import jakarta.validation.Valid;
import my.urlshortener.Service.Base62Encoder;
import my.urlshortener.Service.IdRangeService;
import my.urlshortener.Service.UrlService;
import my.urlshortener.models.LinkForm;
import my.urlshortener.models.UrlMappingEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    private final UrlService urlService;


    @Value("${app.base.url}")
    private String baseUrl;


    public HomeController(UrlService urlService){
        this.urlService = urlService;

    }
    @GetMapping("/")
    public String goHome(Model model){
        model.addAttribute("message", "");
        model.addAttribute("linkForm", new LinkForm());
        return "app";

    }

    @PostMapping("/submit")
    public String submitForm(@Valid @ModelAttribute LinkForm linkForm, BindingResult result,
                             Model model) {
        if (result.hasErrors()) {
            return "app";
        }
        String longUrl = linkForm.getUrl();
        String shortCode = urlService.addMapping(longUrl);
        String shortUrl = "/api/" + shortCode;
        model.addAttribute("message", shortUrl);
        model.addAttribute("linkForm", new LinkForm());
        return "app";
    }

}
