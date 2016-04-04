package de.envisia.crypto;

import org.junit.Test;
import static org.junit.Assert.*;

public class PBKDF2PasswordHasherTest {

    private String salt = "ESdo1tMjzlaSDX8H";
    private String hash = "pbkdf2_sha256$24000$ESdo1tMjzlaSDX8H$TRIq5V0m4f9+2THo3L/y5G+syVv1OL7TJ/UGRScWwwE=";
    private String passwordHash = "TRIq5V0m4f9+2THo3L/y5G+syVv1OL7TJ/UGRScWwwE=";
    private PasswordHasher hasher = new PBKDF2PasswordHasher(new EnvisiaRandomImpl());

    @Test
    public void returnAlwaysTheSamePassword() {
        String hashed = hasher.encoder("helloworld", salt, 24000);
        assertEquals(hash, hashed);
    }

    @Test
    public void returnAlwaysTheSamePasswordWithDefaultIterations() {
        String hashed = hasher.encoder("helloworld", salt);
        assertEquals(hash, hashed);
    }

    @Test
    public void yieldAnotherPasswordWithAdifferentHash() {
        String hashed = hasher.encoder("helloworld", "hase");
        assertNotEquals(hash, hashed);
    }

    @Test
    public void yieldAnotherPasswordSameHashDifferentPassword() {
        String hashed = hasher.encoder("hase", salt);
        assertNotEquals(hash, hashed);
    }

    @Test
    public void verifyCorrectPassword() {
        assertEquals(true, hasher.verify("helloworld", passwordHash, salt, 24000));
    }

    @Test
    public void returnFalseWrongSalt() {
        assertEquals(false, hasher.verify("helloworld", passwordHash, "hase", 24000));
    }

    @Test
    public void returnFalseWrongIterations() {
        assertEquals(false, hasher.verify("helloworld", passwordHash, salt, 23000));
    }

    @Test
    public void returnFalseWrongPassword() {
        assertEquals(false, hasher.verify("hase", passwordHash, salt, 24000));
    }

}
