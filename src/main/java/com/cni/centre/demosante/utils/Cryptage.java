package com.cni.centre.demosante.utils;

import org.jasypt.util.text.BasicTextEncryptor;

public class Cryptage {
	public static String crypte(String myDataBasePassword) {
        String myEncryptionPassword = "key";
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

        textEncryptor.setPassword(myEncryptionPassword);
        String myEncryptedPassword = textEncryptor.encrypt(myDataBasePassword);
        return myEncryptedPassword;

}

public static String decrypte(String myEncryptedPassword) {
        String myEncryptionPassword = "key";
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(myEncryptionPassword);

        String plainText = textEncryptor.decrypt(myEncryptedPassword);

        return plainText;
}
}
