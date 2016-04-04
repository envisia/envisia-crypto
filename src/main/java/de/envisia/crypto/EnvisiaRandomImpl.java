package de.envisia.crypto;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.security.SecureRandom;
import java.util.Base64;

@Singleton
public class EnvisiaRandomImpl implements EnvisiaRandom {

    private final SecureRandom random;

    @Inject
    public EnvisiaRandomImpl() {
        random = new SecureRandom();
        random.nextBytes(new byte[55]);
    }

    @Override
    public String token() {
        return token(Base64.getEncoder(), 12);
    }

    @Override
    public String token(Base64.Encoder encoder) {
        return token(encoder, 12);
    }

    @Override
    public String token(int size) {
        return token(Base64.getEncoder(), size);
    }

    @Override
    public String token(Base64.Encoder encoder, int size) {
        byte[] bytes = new byte[size];
        random.nextBytes(bytes);
        return encoder.encodeToString(bytes);
    }

}
