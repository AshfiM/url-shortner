package my.urlshortener.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "url_mapping")
public class UrlMappingEntity implements Serializable {
    @Id
    private long id;

    @Column(name = "short_code", unique = true)
    private String shortCode;
    @Column(name = "url_hash", unique = true)
    private String urlHash;
    @Column(name = "long_url")
    private String longUrl;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public UrlMappingEntity(long id, String shortCode, String urlHash, String longUrl) {
        this.id = id;
        this.shortCode = shortCode;
        this.urlHash = urlHash;
        this.longUrl = longUrl;
    }

    public UrlMappingEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getUrlHash() {
        return urlHash;
    }

    public void setUrlHash(String urlHash) {
        this.urlHash = urlHash;
    }
}
