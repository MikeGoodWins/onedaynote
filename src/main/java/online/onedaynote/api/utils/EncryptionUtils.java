package online.onedaynote.api.utils;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import online.onedaynote.api.exceptions.SomethingWentWrongException;

public final class EncryptionUtils {


    private EncryptionUtils() {
    }

    public static String encrypt(String password, String payload) {
        return crypt(password, payload, Cipher.ENCRYPT_MODE);
    }

    public static String encrypt(String password) {
        return crypt(password, password, Cipher.ENCRYPT_MODE);
    }

    public static String decrypt(String password, String payload) {
        return crypt(password, payload, Cipher.DECRYPT_MODE);
    }

    public static String decrypt(String password) {
        return crypt(password, password, Cipher.DECRYPT_MODE);
    }

    private static SecretKey getKey(String password) throws NoSuchAlgorithmException,
            InvalidKeySpecException {

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray());
        return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
    }


    private static String crypt(String password, String payload, int mode) {
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(mode, getKey(password));
            byte[] output = cipher.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            return new String(output);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SomethingWentWrongException("Encryption exception");
        }
    }
}
