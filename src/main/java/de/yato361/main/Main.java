package main.java.de.yato361.main;

import main.java.de.yato361.encryption.EncryptionManager;
import main.java.de.yato361.encryption.KeyGen;

public class Main implements Constants {
    public static void main(String[] args) throws Exception {
        //initializing keygen
        KeyGen keyGen = new KeyGen();

        //PROGRAM START
        long ctWhole = System.currentTimeMillis();
        System.out.println("[Crypto] Project started. Key length will be " + keyGen.getKeyLength()  + " bits.");

        long currentTimeMillis = System.currentTimeMillis();
        //generating keypair
        keyGen.generateKey();
        //-
        System.out.println("[Crypto] KeyPair generated TOOK: " + (System.currentTimeMillis()-currentTimeMillis) + " ms");

        currentTimeMillis = System.currentTimeMillis();
        //initializing encryption manager obj
        EncryptionManager encryptionManager = new EncryptionManager(keyGen.getKeyPair(), keyGen.getKeyLength());
        //-
        System.out.println("[Crypto] EncryptionManager defined TOOK: " + (System.currentTimeMillis()-currentTimeMillis) + " ms");

        currentTimeMillis = System.currentTimeMillis();
        //generating 200kb byte
        byte[] buffer = new byte[BYTE_LENGTH];
        //-
        System.out.println("[Crypto] Generated data length: " + buffer.length + " TOOK: " + (System.currentTimeMillis()-currentTimeMillis) + " ms");

        currentTimeMillis = System.currentTimeMillis();
        //encrypting raw data
        byte[] encryptedBuffer = encryptionManager.encrypt(buffer);
        //-
        System.out.println("[Crypto] Encrypted data length: " + encryptedBuffer.length + " TOOK: " + (System.currentTimeMillis()-currentTimeMillis) + " ms");

        currentTimeMillis = System.currentTimeMillis();
        //decrypting raw data
        byte[] decryptedBuffer = encryptionManager.decrypt(encryptedBuffer);
        //-
        System.out.println("[Crypto] Decrypted data length: " + decryptedBuffer.length + " TOOK: " + (System.currentTimeMillis()-currentTimeMillis) + " ms");

        //PROGRAM END
        System.out.println("[Crypto] -END- Runtime: " + (System.currentTimeMillis()-ctWhole) + " ms");
    }
}
