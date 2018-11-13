package com.viettel.ktts2.common;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;

public class UEncrypt {

    private static ResourceBundle rb = ResourceBundle.getBundle("config");
    ;
	private static String UPLOAD_ENCRYPT_KEY = rb.getString("default_upload_encode_key");

    private static String UID_QUERY_CRYPT = "UID_QUERY_CRYPT";

    public static String decrypt(String encryptedMessage, String encryptKey) throws Exception {

        try {
            byte[] shaKey = Arrays.copyOf(encryptKey.getBytes(), 16);
            SecretKeySpec key = new SecretKeySpec(shaKey, "AES");
            Cipher cipher;

            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");

            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] input = Base64.getDecoder().decode(encryptedMessage);
            byte[] encryptedByte = cipher.doFinal(input);
            return new String(encryptedByte);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException ex) {
            throw new Exception(ex);
        }

    }

    public static String decryptFileUploadPath(String path) throws Exception{
        return decrypt(path, UPLOAD_ENCRYPT_KEY);
    }

    public static String encryptFileUploadPath(String encryptedPath) throws Exception {
        return encrypt(encryptedPath, UPLOAD_ENCRYPT_KEY);
    }

    public static String encrypt(String decryptMessage, String encryptKey) throws Exception {
        try{
            byte[] shaKey = Arrays.copyOf(encryptKey.getBytes(), 16);
            SecretKeySpec key = new SecretKeySpec(shaKey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] input = decryptMessage.getBytes();
            byte[] encryptedByte = cipher.doFinal(input);

            return Base64.getEncoder().encodeToString(encryptedByte);
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | NoSuchPaddingException ex) {
            throw new Exception(ex);
        }
    }

    public static String decrypt(String encryptedMessage, HttpServletRequest request) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        String encryptKey;

        if (request.getSession().getAttribute(UID_QUERY_CRYPT) == null) {
            return "";
        } else {
            encryptKey = (String) request.getSession().getAttribute(UID_QUERY_CRYPT);
        }

        byte[] shaKey = Arrays.copyOf(encryptKey.getBytes(), 16);
        SecretKeySpec key = new SecretKeySpec(shaKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] input = Base64.getDecoder().decode(encryptedMessage);
        byte[] encryptedByte = cipher.doFinal(input);
        return new String(encryptedByte);
    }

    public static String encrypt(String plainText, HttpServletRequest request) throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String encryptKey;

        if (request.getSession().getAttribute(UID_QUERY_CRYPT) == null) {
            encryptKey = UUID.randomUUID().toString();
            request.getSession().setAttribute(UID_QUERY_CRYPT, encryptKey);
        } else {
            encryptKey = (String) request.getSession().getAttribute(UID_QUERY_CRYPT);
        }
        byte[] shaKey = Arrays.copyOf(encryptKey.getBytes(), 16);
        SecretKeySpec key = new SecretKeySpec(shaKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding", "SunJCE");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] input = plainText.getBytes();
        byte[] encryptedByte = cipher.doFinal(input);

        return Base64.getEncoder().encodeToString(encryptedByte);
    }
}
