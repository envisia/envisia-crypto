package de.envisia.crypto;

import org.junit.Test;

import java.util.Base64;

public class EnvisiaRandomTest {

    private EnvisiaRandom random = new EnvisiaRandomImpl();

    @Test(timeout = 100)
    public void generateTokenNotTakeLongerThan100ms() throws InterruptedException {
        random.token(12);
    }

    @Test(timeout = 100)
    public void generateTokenAlsoNotTakeLongerThan100ms() {
        random.token(Base64.getUrlEncoder(), 20);
    }

    @Test(timeout = 100)
    public void multipleCallsInLessThan100ms() {
        for(int i = 0; i < 12; i++) {
            random.token(Base64.getUrlEncoder(), 20);
        }
    }

}
