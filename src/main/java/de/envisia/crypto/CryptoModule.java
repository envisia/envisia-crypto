package de.envisia.crypto;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class CryptoModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PasswordValidator.class).to(PasswordValidatorImpl.class);
        bind(EnvisiaRandom.class).to(EnvisiaRandomImpl.class);
        bind(PasswordHasher.class).to(PBKDF2PasswordHasher.class);
        bind(PasswordHasher.class)
                .annotatedWith(Names.named(PBKDF2PasswordHasher.algorithmName))
                .to(PBKDF2PasswordHasher.class);
    }

}
