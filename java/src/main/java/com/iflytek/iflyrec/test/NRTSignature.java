package com.iflytek.iflyrec.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class NRTSignature {

    private final static String CHARSET_UTF8 = "UTF-8";
    private final static String ALGORITHM = "UTF-8";

    public static String gernerateSignature(Map<String, Object> map, String accessKeySecret)
            throws Exception {
        String signature = "";
        try {
            String formUrlEncodedString = formUrlEncodedParameters(map);
            byte[] signBytes = hmacSHA1Signature(accessKeySecret, formUrlEncodedString);
            signature = newStringByBase64(signBytes);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
            signature = "";
        } 
        return signature;
    }

    public static byte[] hmacSHA1Signature(String secret, String baseString)
            throws Exception {
        if (isEmpty(secret)) {
            throw new IOException("secret can not be empty");
        }
        if (isEmpty(baseString)) {
            return null;
        }
        Mac mac = Mac.getInstance("HmacSHA1");
        SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(CHARSET_UTF8),
                ALGORITHM);
        mac.init(keySpec);
        return mac.doFinal(baseString.getBytes(CHARSET_UTF8));
    }

    private static boolean isEmpty(String str) {
        return (str == null || str.length() == 0);
    }

    public static String newStringByBase64(byte[] bytes)
            throws UnsupportedEncodingException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        return Base64.getEncoder().encodeToString(bytes);
    }

    private static String formUrlEncodedParameters(Map<String, Object> params)
            throws UnsupportedEncodingException {
        StringBuilder canonicalizedQueryString = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
        	if(entry.getValue() != null){
        		canonicalizedQueryString
        		.append(URLEncoder.encode(entry.getKey(), CHARSET_UTF8))
        		.append("=")
        		.append(URLEncoder.encode(String.valueOf(entry.getValue()),
        				CHARSET_UTF8))
        		.append("&");
        	}
        }
        if (canonicalizedQueryString.length() > 1) {
            canonicalizedQueryString
                    .setLength(canonicalizedQueryString.length() - 1);
        }
        return canonicalizedQueryString.toString();
    }

    public static String formUrlEncodedValueParameters(
            Map<String, Object> params) throws UnsupportedEncodingException {
        StringBuilder canonicalizedQueryString = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
        	if(entry.getValue() != null){
        		canonicalizedQueryString.append(entry.getKey()).append("=")
        		.append(URLEncoder.encode(String.valueOf(entry.getValue()),
        				CHARSET_UTF8))
        		.append("&");
        	}
        }
        if (canonicalizedQueryString.length() > 1) {
            canonicalizedQueryString
                    .setLength(canonicalizedQueryString.length() - 1);
        }
        return canonicalizedQueryString.toString();
    }
    
}
