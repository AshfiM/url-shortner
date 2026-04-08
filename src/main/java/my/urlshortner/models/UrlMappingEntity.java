package my.urlshortner.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "url_mapping")
public class UrlMappingEntity {
    @Id
    private long id;

    @Column(name = "short_code")
    private String shortCode;
    @Column(name = "long_url")
    private String longUrl;
    @Column(name = "created_at")
    private Timestamp createdAt;

    public UrlMappingEntity() {
    }

    public UrlMappingEntity(Long id, String shortCode, String longUrl) {
        this.id = id;
        this.shortCode = shortCode;
        this.longUrl = longUrl;
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

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
