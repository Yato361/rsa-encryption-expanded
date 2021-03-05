package main.java.de.yato361.encryption;

import main.java.de.yato361.util.ArrayUtils;
import main.java.de.yato361.util.ChunkSplicer;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

public class BufDecrypt {
    private Cipher cipher;
    private ChunkSplicer chunkSplicer;
    private int maxEncryptedLength;

    public BufDecrypt(ChunkSplicer chunkSplicer, Cipher cipher, int maxEncryptedLength){
        this.chunkSplicer = chunkSplicer;
        this.cipher = cipher;
        this.maxEncryptedLength = maxEncryptedLength;
    }

    public byte[] decryptData() throws BadPaddingException, IllegalBlockSizeException {
        byte[][] decryptedArrays = new byte[this.chunkSplicer.getDimensionalByteArray().length-1][this.maxEncryptedLength];
        byte[] lastArray = this.chunkSplicer.getDimensionalByteArray()[this.chunkSplicer.getDimensionalByteArray().length-1];
        for(int i = 0; i<this.chunkSplicer.getDimensionalByteArray().length-1; i++){
            byte[] decrypted = this.cipher.doFinal(this.chunkSplicer.getDimensionalByteArray()[i]);
            for(int h = 0; h< this.maxEncryptedLength; h++){
                decryptedArrays[i][h] = decrypted[h];
            }
        }
        return ArrayUtils.meltArray(ArrayUtils.arrayFromMatrixArray(decryptedArrays, this.maxEncryptedLength),this.cipher.doFinal(lastArray));
    }
}
