package com.gist.graduation.utils.converter;

import org.apache.commons.codec.binary.Base64;

import java.security.spec.X509EncodedKeySpec;

public class StringKeySpecUtils {

    public static X509EncodedKeySpec stringToX509EncodedKeySpec(String string) {
        byte[] publicBytes = Base64.decodeBase64(string);
        return new X509EncodedKeySpec(publicBytes);
    }
}
