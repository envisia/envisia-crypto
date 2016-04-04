package de.envisia.crypto;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PasswordValidatorTest {

    private String hash = "pbkdf2_sha256$24000$ESdo1tMjzlaSDX8H$TRIq5V0m4f9+2THo3L/y5G+syVv1OL7TJ/UGRScWwwE=";
    private Injector injector = Guice.createInjector(new CryptoModule());
    private PasswordValidator validator = new PasswordValidatorImpl(injector);

    @Test
    public void returnTrueOnCorrectPassword() {
        assertEquals(true, validator.verify("helloworld", hash));
    }

    @Test
    public void returnFalseOnIncorrectPassword() {
        assertEquals(false, validator.verify("hello", hash));
    }

}
