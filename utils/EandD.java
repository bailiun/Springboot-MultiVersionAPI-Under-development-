package org.bailiun.multipleversionscoexist.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

public class EandD {
    //加密模式之 ECB，算法/模式/补码方式
    private static final String AES_ECB = "AES/ECB/PKCS5Padding";
    //加密模式之 CBC，算法/模式/补码方式
    private static final String AES_CBC = "AES/CBC/PKCS5Padding";
    //加密模式之 CFB，算法/模式/补码方式
    private static final String AES_CFB = "AES/CFB/PKCS5Padding";
    //AES 中的 IV 必须是 16 字节（128位）长
    private static final Integer IV_LENGTH = 16;
    private static final String RSA_KEY_ALGORITHM = "RSA";
    private static final String RSA_SIGNATURE_ALGORITHM = "SHA1withRSA";
    private static final String RSA2_SIGNATURE_ALGORITHM = "SHA256withRSA";
    private static final int KEY_SIZE = 2048;

    public static String string2MD5(String inStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuilder hexValue = new StringBuilder();
        for (byte md5Byte : md5Bytes) {
            int val = ((int) md5Byte) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static String convertMD5(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        return new String(a);
    }

    public static String getAES_CBC() {
        return AES_CBC;
    }

    public static String getAES_CFB() {
        return AES_CFB;
    }

    public static boolean AESisEmpty(Object str) {
        return null == str || "".equals(str);
    }

    public static byte[] AESgetBytes(String str) {
        if (AESisEmpty(str)) {
            return null;
        }
        try {
            return str.getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String AESgetIV() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < IV_LENGTH; i++) {
            int number = random.nextInt(str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static SecretKeySpec AESgetSecretKeySpec(String key) {
        return new SecretKeySpec(AESgetBytes(key), "AES");
    }

    public static String AESencrypt(String text, String key) {
        if (AESisEmpty(text) || AESisEmpty(key)) {
            return null;
        }
        try {
            // 创建AES加密器
            Cipher cipher = Cipher.getInstance(AES_ECB);
            SecretKeySpec secretKeySpec = AESgetSecretKeySpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            // 加密字节数组
            byte[] encryptedBytes = cipher.doFinal(AESgetBytes(text));
            // 将密文转换为 Base64 编码字符串
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String AESdecrypt(String text, String key) {
        if (AESisEmpty(text) || AESisEmpty(key)) {
            return null;
        }
        // 将密文转换为16字节的字节数组
        byte[] textBytes = Base64.getDecoder().decode(text);
        try {
            // 创建AES加密器
            Cipher cipher = Cipher.getInstance(AES_ECB);
            SecretKeySpec secretKeySpec = AESgetSecretKeySpec(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            // 解密字节数组
            byte[] decryptedBytes = cipher.doFinal(textBytes);
            // 将明文转换为字符串
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String AESencrypt(String text, String key, String iv, String mode) {
        if (AESisEmpty(text) || AESisEmpty(key) || AESisEmpty(iv)) {
            return null;
        }
        try {
            // 创建AES加密器
            Cipher cipher = Cipher.getInstance(mode);
            SecretKeySpec secretKeySpec = AESgetSecretKeySpec(key);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(AESgetBytes(iv)));
            // 加密字节数组
            byte[] encryptedBytes = cipher.doFinal(Objects.requireNonNull(AESgetBytes(text)));
            // 将密文转换为 Base64 编码字符串
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String AESdecrypt(String text, String key, String iv, String mode) {
        if (AESisEmpty(text) || AESisEmpty(key) || AESisEmpty(iv)) {
            return null;
        }
        // 将密文转换为16字节的字节数组
        byte[] textBytes = Base64.getDecoder().decode(text);
        try {
            // 创建AES加密器
            Cipher cipher = Cipher.getInstance(mode);
            SecretKeySpec secretKeySpec = AESgetSecretKeySpec(key);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(Objects.requireNonNull(AESgetBytes(iv))));
            // 解密字节数组
            byte[] decryptedBytes = cipher.doFinal(textBytes);
            // 将明文转换为字符串
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String desEncript(String clearText, String originKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("DES"); /*提供加密的方式：DES*/
        SecretKeySpec key = getKey(originKey);  /*对密钥进行操作，产生16个48位长的子密钥*/
        cipher.init(Cipher.ENCRYPT_MODE, key); /*初始化cipher，选定模式，这里为加密模式，并同时传入密钥*/
        byte[] doFinal = cipher.doFinal(clearText.getBytes());   /*开始加密操作*/
        return Base64.getEncoder().encodeToString(doFinal);
    }

    public static String desDecript(String cipherText, String originKey) throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance("DES");   /*初始化加密方式*/
        Key key = getKey(originKey);  /*获取密钥*/
        cipher.init(Cipher.DECRYPT_MODE, key);  /*初始化操作方式*/
        byte[] decode = Base64.getDecoder().decode(cipherText);  /*按照Base64解码*/
        byte[] doFinal = cipher.doFinal(decode);   /*执行解码操作*/
        return new String(doFinal);   /*转换成相应字符串并返回*/
    }

    private static SecretKeySpec getKey(String originKey) {
        byte[] buffer = new byte[8];
        byte[] originBytes = originKey.getBytes();
        for (int i = 0; i < 8 && i < originBytes.length; i++) {
            buffer[i] = originBytes[i];  /*如果originBytes不足8,buffer剩余的补零*/
        }
        return new SecretKeySpec(buffer, "DES");  /*返回操作之后得到的密钥*/
    }

    public static Map<String, String> generateKey() {
        KeyPairGenerator keygen;
        try {
            keygen = KeyPairGenerator.getInstance(RSA_KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("RSA初始化密钥出现错误,算法异常");
        }
        SecureRandom secrand = new SecureRandom();
        secrand.setSeed("Alian".getBytes());
        keygen.initialize(KEY_SIZE, secrand);
        KeyPair keyPair = keygen.genKeyPair();
        byte[] pub_key = keyPair.getPublic().getEncoded();
        String publicKeyStr = Base64.getEncoder().encodeToString(pub_key);
        byte[] pri_key = keyPair.getPrivate().getEncoded();
        String privateKeyStr = Base64.getEncoder().encodeToString(pri_key);
        Map<String, String> keyPairMap = new HashMap<>();
        keyPairMap.put("publicKeyStr", publicKeyStr);
        keyPairMap.put("privateKeyStr", privateKeyStr);
        return keyPairMap;
    }

    public static String encryptByPublicKey(String data, String publicKeyStr) throws Exception {
        byte[] pubKey = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encrypt = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypt);
    }

    public static String decryptByPrivateKey(String data, String privateKeyStr) throws Exception {
        byte[] priKey = Base64.getDecoder().decode(privateKeyStr);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(data));
        return new String(decrypt);
    }

    public static String encryptByPrivateKey(String data, String privateKeyStr) throws Exception {
        byte[] priKey = Base64.getDecoder().decode(privateKeyStr);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encrypt = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encrypt);
    }

    public static String decryptByPublicKey(String data, String publicKeyStr) throws Exception {
        byte[] pubKey = Base64.getDecoder().decode(publicKeyStr);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decrypt = cipher.doFinal(Base64.getDecoder().decode(data));
        return new String(decrypt);
    }

    public static String sign(byte[] data, byte[] priKey, String signType) throws Exception {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        String algorithm = RSA_KEY_ALGORITHM.equals(signType) ? RSA_SIGNATURE_ALGORITHM : RSA2_SIGNATURE_ALGORITHM;
        Signature signature = Signature.getInstance(algorithm);
        signature.initSign(privateKey);
        signature.update(data);
        byte[] sign = signature.sign();
        return Base64.getEncoder().encodeToString(sign);
    }

    public static boolean verify(byte[] data, byte[] sign, byte[] pubKey, String signType) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        String algorithm = RSA_KEY_ALGORITHM.equals(signType) ? RSA_SIGNATURE_ALGORITHM : RSA2_SIGNATURE_ALGORITHM;
        Signature signature = Signature.getInstance(algorithm);
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(sign);
    }
}
