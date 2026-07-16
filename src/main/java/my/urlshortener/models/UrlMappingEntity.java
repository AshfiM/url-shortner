package my.urlshortener.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "url_mapping")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UrlMappingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "short_code", unique = true)
    private String shortCode;
    @Column(name = "url_hash", unique = true)
    private String urlHash;
    @Column(name = "long_url")
    private String longUrl;

}
