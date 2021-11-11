package no.hvl.dat108.oblig4;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

// Implementation is PBKDF2WithHmacSHA256, saltgen algorithm is SHA1PRNG
public class HashUtil {

	private static final String SALT_ALGORITHM = "SHA1PRNG";
	private static final String HASH_ALGORITHM = "PBKDF2WithHmacSHA256";
	private static final int ITERATIONS = 1000;
	private static final int KEY_BIT_LENGTH = 256;
	
	private SecureRandom sr;
	private SecretKeyFactory skf;

	//TODO JUnit
	
	public HashUtil() {
		try {
			sr = SecureRandom.getInstance(SALT_ALGORITHM);
			skf = SecretKeyFactory.getInstance(HASH_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	// TODO Timing attacks
	public boolean check(String password, String salt, String hash) {
		String hash2 = hash(password, salt);
		return hash2.equals(hash);
	}

	public String hash(String password, String salt) {
		if (password == null || salt == null) throw new IllegalArgumentException();

		char[] passchar = password.toCharArray();
		byte[] saltbytes = salt.getBytes(StandardCharsets.UTF_8);

		byte[] keyhash = null;
		try {
			PBEKeySpec pks = new PBEKeySpec(passchar, saltbytes, ITERATIONS, KEY_BIT_LENGTH);
			keyhash = skf.generateSecret(pks).getEncoded();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return DatatypeConverter.printHexBinary(keyhash);
	}

	public String genSalt() {
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return DatatypeConverter.printHexBinary(salt);
	}

}
