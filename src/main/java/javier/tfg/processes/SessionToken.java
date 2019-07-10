package javier.tfg.processes;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class SessionToken {

    protected static SecureRandom random = new SecureRandom();
    
    public synchronized String generateToken(String id) {
        byte[] bytes = id.getBytes(StandardCharsets.UTF_8); 
        String base36 = new BigInteger(1, bytes).toString(36);
        return base36;
    }

}
