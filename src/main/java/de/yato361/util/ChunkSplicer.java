package main.java.de.yato361.util;

import static java.lang.Math.abs;

public class ChunkSplicer {
    private byte[][] dimensionalByteArray;
    private byte[] remainderArray;

    private int roundUp(int num, int divisor) {
        return (abs(num) + abs(divisor)) / abs(divisor);
    }
    public ChunkSplicer(byte[] bytes, int chunkLength){
        byte[][] dimensionalByteArray = new byte[roundUp(bytes.length,chunkLength)-1][chunkLength];
        byte[] remainderArray = new byte[bytes.length%chunkLength];

        for(int i = 0; i<bytes.length; i++){
            if((i/chunkLength)<(roundUp(bytes.length,chunkLength)-1)){
                dimensionalByteArray[i/chunkLength][i%chunkLength] = bytes[i];
            }else{
                remainderArray[i%chunkLength] = bytes[i];
            }
        }
        this.dimensionalByteArray = dimensionalByteArray;
        this.remainderArray = remainderArray;
    }

    public byte[][] getDimensionalByteArray() {
        return this.dimensionalByteArray;
    }

    public byte[] getRemainderArray() {
        return this.remainderArray;
    }
}