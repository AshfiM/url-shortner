package my.urlshortener.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

public class HashingService {

    public static String hashUrl(String longUrl) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(
                    longUrl.getBytes(StandardCharsets.UTF_8)
            );
            return HexFormat.of().formatHex(encodedHash);
        } catch (Exception e) {
            throw new RuntimeException("invalid algo", e);
        }


    }
}
