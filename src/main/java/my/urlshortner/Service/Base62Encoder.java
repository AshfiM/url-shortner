package my.urlshortner.Service;

public class Base62Encoder {
    private static final String alpahbet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static String encode(Long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(alpahbet.charAt((int) (num % 62)));
            num /=62;
        }
        return sb.reverse().toString();
    }
}
