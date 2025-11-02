package org.bailiun.multipleversionscoexist.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class HmacUtil {

    private static final String HMAC_ALGO = "HmacSHA256";
    private static final String SECRET = "chui123KHAL"; 
    public static String sign(String data) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(SECRET.getBytes(), HMAC_ALGO);
        Mac mac = Mac.getInstance(HMAC_ALGO);
        mac.init(secretKey);
        byte[] hmacBytes = mac.doFinal(data.getBytes());
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hmacBytes);
    }

    public static boolean verify(String data, String signature) throws Exception {
        return sign(data).equals(signature);
    }
}
