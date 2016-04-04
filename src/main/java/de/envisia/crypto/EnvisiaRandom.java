package de.envisia.crypto;

import java.util.Base64;

public interface EnvisiaRandom {

    String token();

    String token(Base64.Encoder encoder);

    String token(int size);

    String token(Base64.Encoder encoder, int size);

}
