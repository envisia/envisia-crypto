package de.envisia.crypto;

public interface PasswordHasher {

    String encoder(String password, String salt);

    String encoder(String password, String salt, int iterations);

    boolean verify(String password, String hash, String salt, int iterations);

    String newSalt();

}
