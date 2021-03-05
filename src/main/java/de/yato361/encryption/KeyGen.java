package main.java.de.yato361.encryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;


public class KeyGen implements Constants {
    private KeyPair keyPair;

    public KeyGen(){}

    public void generateKey() throws NoSuchAlgorithmException {
        KeyPairGenerator gen = KeyPairGenerator.getInstance("RSA");
        gen.initialize(this.KEY_LENGTH);
        keyPair = gen.generateKeyPair();
    }

    public KeyPair getKeyPair() {
        return keyPair;
    }

    public int getKeyLength() {
        return this.KEY_LENGTH;
    }
}
