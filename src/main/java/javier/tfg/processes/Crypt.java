package javier.tfg.processes;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Crypt {
	
	static String IV = "thisIsA16bytesIV";
	static String encryptionKey = "thisIsEncriptKey";
	
	
    public static boolean comprueba(String salted, String password) throws Exception {
    	String pass = salt(password);
        return pass.equals(salted);
    }
     
    public static String salt(String password) throws Exception {
    	byte[] cipher = encrypt(password, encryptionKey);
    	String crypted = "";
    	for (int i=0; i<cipher.length; i++)
    		crypted += new Integer(cipher[i]);
    	
    	return crypted;
    }
    
    private static byte[] encrypt(String plainText, String encryptionKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key,new IvParameterSpec(IV.getBytes("UTF-8")));
        return cipher.doFinal(plainText.getBytes("UTF-8"));
      }

	
}
