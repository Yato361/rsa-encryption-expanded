public class Main implements Constants {
    public static void main(String[] args) throws Exception {
        //initializing KeyGen
        KeyGen keyGen = new KeyGen();

        //PROGRAMM START
        long ctWhole = System.currentTimeMillis();
        System.out.println("[Krypto] Project started. Key length will be " + keyGen.getKeyLength()  + " bits");

        long ct = System.currentTimeMillis();
        //generating keypair
        keyGen.generateKey();
        //-
        System.out.println("[Crypto] KeyPair generated TOOK: " + (System.currentTimeMillis()-ct) + " ms");

        ct = System.currentTimeMillis();
        //initializing own security obj
        Security sec = new Security(keyGen.getKeyPair(), keyGen.getKeyLength());
        //-
        System.out.println("[Crypto] Security defined TOOK: " + (System.currentTimeMillis()-ct) + " ms");

        ct = System.currentTimeMillis();
        //generating 200kb byte
        byte[] raw = new byte[BYTE_LENGTH];
        //-
        System.out.println("[Crypto] Data generated LENGTH: " + raw.length + " TOOK: " + (System.currentTimeMillis()-ct) + " ms");

        ct = System.currentTimeMillis();
        //encrypting raw data
        byte[] encrypted = sec.encrypt(raw);
        //-
        System.out.println("[Crypto] Data encrypted LENGTH: " + encrypted.length + " TOOK: " + (System.currentTimeMillis()-ct) + " ms");

        ct = System.currentTimeMillis();
        //decrypting raw data
        byte[] decryoted = sec.decrypt(encrypted);
        //-
        System.out.println("[Crypto] Data decrypted LENGTH: " + decryoted.length + " TOOK: " + (System.currentTimeMillis()-ct) + " ms");

        //PROGRAMM END
        System.out.println("[Crypto] -END- Runtime: " + (System.currentTimeMillis()-ctWhole) + " ms");
    }
}
