package de.envisia.crypto;

import com.google.inject.ConfigurationException;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.ProvisionException;
import com.google.inject.name.Names;

import javax.inject.Inject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidatorImpl implements PasswordValidator {
    private final Pattern pattern = Pattern.compile("(.*)\\$(\\d+)\\$(.*)\\$(.*)");
    private final Injector injector;

    @Inject
    public PasswordValidatorImpl(Injector injector) {
        this.injector = injector;
    }

    @Override
    public boolean verify(String password, String encoded) throws HasherNotFoundException {
        Matcher m = pattern.matcher(encoded);
        if (m.matches()) {
            String algorithm = m.group(1);
            int iterations = new Integer(m.group(2));
            String salt = m.group(3);
            String hash = m.group(4);
            try {
                Key<PasswordHasher> hasherKey = Key.get(PasswordHasher.class, Names.named(algorithm));
                PasswordHasher hasher = injector.getInstance(hasherKey);
                return hasher.verify(password, hash, salt, iterations);
            } catch (ConfigurationException | ProvisionException e) {
                throw new HasherNotFoundException("Hasher with Algorithm: " + algorithm + " not found!");
            }
        } else {
            return false;
        }
    }

}
