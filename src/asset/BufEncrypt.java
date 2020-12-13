package asset;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

public class BufEncrypt {
    private Cipher cipher;
    private ChunkSplicer chunkSplicer;
    private int maxDecryptedLength;

    public BufEncrypt(ChunkSplicer chunkSplicer, Cipher cipher, int maxDecryptedLength){
        this.chunkSplicer = chunkSplicer;
        this.cipher = cipher;
        this.maxDecryptedLength = maxDecryptedLength;
    }

    public byte[] encryptData() throws BadPaddingException, IllegalBlockSizeException {
        byte[][] encryptedArrays = new byte[this.chunkSplicer.getDimensionalByteArray().length+1][this.maxDecryptedLength];
        for(int i = 0; i<=this.chunkSplicer.getDimensionalByteArray().length; i++){
            if(i<this.chunkSplicer.getDimensionalByteArray().length){
                byte[] encrypted = this.cipher.doFinal(this.chunkSplicer.getDimensionalByteArray()[i]);
                for(int h = 0; h< this.maxDecryptedLength; h++){
                    encryptedArrays[i][h] = encrypted[h];
                }
            }else {
                byte[] encrypted = this.cipher.doFinal(this.chunkSplicer.getRemainderArray());
                for(int h = 0; h< this.maxDecryptedLength; h++){
                    encryptedArrays[i][h] = encrypted[h];
                }
            }
        }
        return ArrayUtils.arrayFromMatrixArray(encryptedArrays, this.maxDecryptedLength);
    }
}
