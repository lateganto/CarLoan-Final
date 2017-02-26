package utility.security;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by salvatore on 05/10/15.
 */
public class Cryptographer {

    private static SecretKeySpec secretKey;
    private static final String CARLOAN_KEY = "carloan2015";


    private static void setKey(String carloanKey) {
        try {
            byte [] key = carloanKey.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); // use only first 128 bit
            secretKey = new SecretKeySpec(key, "AES");

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static String encrypt(String value) {
        setKey(CARLOAN_KEY);

        String encrypted = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            encrypted = Base64.encodeBase64String(cipher.doFinal(value.getBytes("UTF-8")));

        } catch (Exception e) {
            //TODO gestire eccezione
            e.printStackTrace();
        }

        return encrypted;
    }

    public static String decrypt(String value) {
        setKey(CARLOAN_KEY);

        String decrypted = null;

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            decrypted = new String(cipher.doFinal(Base64.decodeBase64(value)));

        } catch (Exception e) {
            //TODO gestire eccezione, implementare logger
            e.printStackTrace();
        }

        return decrypted;
    }




    /*public static void main(String args[]) {

        Connector connector = ConnectorFactory.getConnector();

        connector.openConnection();

        connector.executeUpdate("INSERT INTO utenti VALUES(DEFAULT, 'admin','" + Cryptographer.encrypt("password") + "', 'S')");


    }*/


}
