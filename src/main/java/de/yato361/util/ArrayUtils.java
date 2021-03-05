package main.java.de.yato361.util;

public class ArrayUtils {
    public static byte[] arrayFromMatrixArray(byte[][] bytesArray, int byteLength){
        byte[] result = new byte[bytesArray.length*byteLength];
        for(int x = 0; x<bytesArray.length; x++){
            for(int y2 = 0; y2<byteLength; y2++){
                result[(x*byteLength)+y2] = bytesArray[x][y2];
            }
        }
        return result;
    }
    public static byte[] meltArray(byte[] one, byte[] two){
        byte[] combined = new byte[one.length + two.length];

        for (int i = 0; i < combined.length; ++i)
        {
            combined[i] = i < one.length ? one[i] : two[i - one.length];
        }
        return combined;
    }
}
