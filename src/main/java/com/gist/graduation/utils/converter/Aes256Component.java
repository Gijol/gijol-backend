package com.gist.graduation.utils.converter;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class Aes256Component {
    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5PADDING";
    static final Base64.Decoder DECODER = Base64.getDecoder();
    static final Base64.Encoder ENCODER = Base64.getEncoder();
    private SecretKeySpec keySpec;
    private static final byte[] IV = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    private String encodedIv;

    @Value("${aes.key}")
    private String rawKey;

    @PostConstruct
    void init() {
        byte[] key = DECODER.decode(rawKey);
        keySpec = new SecretKeySpec(key, ALGORITHM);
        this.encodedIv = ENCODER.encodeToString(IV);
    }

    @SneakyThrows
    String encrypt(String plainText) {
        Cipher cipher = getCipher(encodedIv, Cipher.ENCRYPT_MODE);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        return ENCODER.encodeToString(encrypted);
    }

    @SneakyThrows
    String decrypt(String cipherText) {
        Cipher cipher = getCipher(encodedIv, Cipher.DECRYPT_MODE);
        byte[] encrypted = DECODER.decode(cipherText);
        byte[] decrypted = cipher.doFinal(encrypted);
        return new String(decrypted);
    }

    private Cipher getCipher(String encodedIv, int decryptMode)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        IvParameterSpec ivSpec = new IvParameterSpec(DECODER.decode(encodedIv));
        cipher.init(decryptMode, keySpec, ivSpec);
        return cipher;
    }
}
