package com.br.edu.ifpb.deps.autoevents.security;

import com.br.edu.ifpb.deps.autoevents.exceptions.EncryptedDataException;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.stereotype.Component;

@Component
public class DatabaseEncryptionComponent {

    private final String passwordKey = "AutoEventsEncryptPassword";

    public DatabaseEncryptionComponent() {}

    public String encryptPassword(String text){
        try {
            AES256TextEncryptor encryptor = initEncryptor();
            return encryptor.encrypt(text);
        }catch (EncryptedDataException e){
            throw new EncryptedDataException("Não foi possível criptografar a senha");
        }
    }

    public String decryptPassword(String text){
        try {
            AES256TextEncryptor encryptor = initEncryptor();
            return encryptor.decrypt(text);
        } catch (EncryptedDataException e){
            throw new EncryptedDataException("Não foi possível decriptografar a senha");
        }
    }

    private AES256TextEncryptor initEncryptor(){
        AES256TextEncryptor encryptor = new AES256TextEncryptor();
        encryptor.setPassword(passwordKey);
        return encryptor;
    }

    public static class Encryption {

        private String databasePassword;

        public String getDatabasePassword() {
            return databasePassword;
        }

        public void setDatabasePassword(String databasePassword) {
            this.databasePassword = databasePassword;
        }
    }
}
