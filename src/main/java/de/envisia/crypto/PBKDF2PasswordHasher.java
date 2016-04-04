package de.envisia.crypto;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.regex.Pattern;

@Singleton
public class PBKDF2PasswordHasher implements PasswordHasher {
    public static String algorithmName = "pbkdf2_sha256";
    private final Pattern pattern = Pattern.compile("(.*)\\$(\\d+)\\$(.*)\\$(.*)");
    private final Base64.Encoder base64encoder = Base64.getEncoder();
    private final EnvisiaRandom random;

    @Inject
    public PBKDF2PasswordHasher(EnvisiaRandom random) {
        this.random = random;
    }

    private String pbkdf2(String password, String salt, int iterations) {
        try {
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), iterations, 256);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return base64encoder.encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("There isn't any algorithm with Name" + algorithmName);
        }
    }

    @Override
    public String encoder(String password, String salt) {
        return encoder(password, salt, 24000);
    }

    @Override
    public String encoder(String password, String salt, int iterations) {
        if (salt.contains("$")) {
            throw new RuntimeException("Salt shouldn't contain any $");
        }
        String encodedHash = pbkdf2(password, salt, iterations);
        return algorithmName + "$" + iterations + "$" + salt + "$" + encodedHash;
    }

    @Override
    public boolean verify(String password, String hash, String salt, int iterations) {
        String newlyEncoded = pbkdf2(password, salt, iterations);
        return MessageDigest.isEqual(newlyEncoded.getBytes(), hash.getBytes());
    }

    @Override
    public String newSalt() {
        return random.token(base64encoder);
    }
}
