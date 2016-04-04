package de.envisia.crypto;

public interface PasswordValidator {

    boolean verify(String password, String encoded);

}
