/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viettel.utils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.StringTokenizer;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author tuantm18
 */

public class EncryptionUtils {

    private static final String ENC_KEY = "191-251-228-45-195-23-211-153";
    static Logger LOGGER = LoggerFactory.getLogger(EncryptionUtils.class);
    public static RSAPublicKey loadPublicKey_X509_RSA(String fileName)
            throws GeneralSecurityException, IOException {
        File publicKeyFile = new File(fileName);
        byte[] keyBytes = new byte[(int) publicKeyFile.length()];
        FileInputStream fis = new FileInputStream(publicKeyFile);
        fis.read(keyBytes);
        
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        RSAPublicKey pubKey = (RSAPublicKey) factory.generatePublic(publicKeySpec);
        fis.close();
        return pubKey;
    }

    public static Key getKey() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            StringTokenizer st = new StringTokenizer(ENC_KEY, "-", false);

            while (st.hasMoreTokens()) {
                int i = Integer.parseInt(st.nextToken());
                bos.write((byte) i);
            }

            byte[] bytes = bos.toByteArray();

            DESKeySpec pass = new DESKeySpec(bytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            SecretKey s = skf.generateSecret(pass);

            return s;
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public static String decrypt(String source, Key key) {
        try {
            Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            byte[] ciphertext = getBytes(source);

            desCipher.init(2, key);

            byte[] cleartext = desCipher.doFinal(ciphertext);

            return new String(cleartext);
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public static String encrypt(String source, Key key) {
        try {
            Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            desCipher.init(1, key);

            byte[] cleartext = source.getBytes();

            byte[] ciphertext = desCipher.doFinal(cleartext);

            return getString(ciphertext);
        } catch (Exception e) {
        	LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    private static byte[] getBytes(String str) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        StringTokenizer st = new StringTokenizer(str, "-", false);
        while (st.hasMoreTokens()) {
            int i = Integer.parseInt(st.nextToken());
            bos.write((byte) i);
        }
        return bos.toByteArray();
    }

    private static String getString(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            sb.append(0xFF & b);
            if (i + 1 < bytes.length) {
                sb.append("-");
            }
        }
        return sb.toString();
    }
}
