package no.hvl.dat108.oblig4;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

// Implementation is PBKDF2WithHmacSHA256, saltgen algorithm is SHA1PRNG
public class HashUtil {

	private static final int ITERATIONS = 1000;
	private static final int KEY_BIT_LENGTH = 256;
	
	private SecureRandom sr;
	private SecretKeyFactory skf;
	
	public HashUtil() {
		try {
			sr = SecureRandom.getInstance(Globals.HASHUTIL_SALT_ALGORITHM);
			skf = SecretKeyFactory.getInstance(Globals.HASHUTIL_HASH_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	//We are not using String.equals(String) here due to timing attacks. 
	//Worst case scenario if such is that a potential hacker could get a hold of the hash, 
	//which they could then try and crack on their own machine instead of going via the server. 
	/** checks the password for validity in a timing-attack safe manner */
	public boolean check(String password, String salt, String hash) {
		String hash2 = hash(password, salt);
		return MessageDigest.isEqual(hash.getBytes(StandardCharsets.UTF_8), hash2.getBytes(StandardCharsets.UTF_8));
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
