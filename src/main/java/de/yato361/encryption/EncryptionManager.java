package main.java.de.yato361.encryption;

import main.java.de.yato361.util.ChunkSplicer;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.PublicKey;



public class EncryptionManager {
    private int maxNonDecryptedLength;
    private int maxCryptedLength;
    private KeyPair keyPair;

    public EncryptionManager(KeyPair keyPair, int keyLength){
        this.keyPair = keyPair;
        this.maxNonDecryptedLength = (keyLength/8)-11;
        this.maxCryptedLength = (keyLength/8);
    }

    public byte[] encrypt(byte[] bytes) throws Exception {
        try {
            PublicKey publicKey = keyPair.getPublic();
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            ChunkSplicer chunkSplicer = new ChunkSplicer(bytes, maxNonDecryptedLength);
            BufEncrypt bufEncrypt = new BufEncrypt(chunkSplicer,cipher,maxCryptedLength);
            return bufEncrypt.encryptData();
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception("Error while encrypting");
        }
    }
     public byte[] decrypt(byte[] bytes) throws Exception {
         try {
             Cipher cipher = Cipher.getInstance("RSA");
             cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
             ChunkSplicer chunkSplicer = new ChunkSplicer(bytes, maxCryptedLength);
             BufDecrypt bufDecrypt = new BufDecrypt(chunkSplicer, cipher, maxNonDecryptedLength);
             return bufDecrypt.decryptData();
         }catch (Exception e){
             e.printStackTrace();
             throw new Exception("Error while decrypting");
         }
    }
}
