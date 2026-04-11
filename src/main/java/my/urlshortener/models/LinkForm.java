package my.urlshortener.models;

import org.hibernate.validator.constraints.URL;

public class LinkForm {

    @URL
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
