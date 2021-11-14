package no.hvl.dat108.oblig4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HashUtilTest {
	
	private HashUtil hashUtil;
	
	@BeforeEach
	public void setupHashUtil() { this.hashUtil = new HashUtil(); }
	
	@Test
	public void hashesTest() {
		assertEquals("hash mismatch normal", "F9C275531B697FED69296D7437635C3664E08E6BB7C4A6C73D026D34654A063C", hashUtil.hash("password123", "example-salt"));
		assertEquals("hash mismatch normal", "F4F6F6B4365E151C22940AB50D1E1977B3D5D2F35304D99F29DCEA513B555EEF", hashUtil.hash("supersecret", "very salty"));
		assertEquals("hash mismatch only salt", "FD257F5067ABFE9B83C7CBBF86B571FF629489619831753280D1197A2BD45940", hashUtil.hash("", "only salt"));
		assertEquals("hash mismatch emoticon sparkles", "A57B3E814E5673B500DF70EAC1630692908023CF9A8DE8F98D8D01716C08F3A0", hashUtil.hash("✨", "salt"));
		
	}
	
	@Test
	public void randomPasswordsHashLengthTest() {
		for (int i = 0; i < 10; ++i) {
			String pass = hashUtil.genSalt(); //cheatsy, but works fine
			String salt = hashUtil.genSalt();
			String hash = hashUtil.hash(pass, salt);
			assertEquals("hash should be 64 characters long", 64, hash.length());
		}
	}
	
	@Test
	public void genSaltLengthTest() {
		for (int i = 0; i < 10; ++i) {
			assertEquals("salt should be 32 characters long", 32, hashUtil.genSalt().length());
		}
	}
	
	@Test
	public void nosaltDisallowedTest() {
		assertThrows(IllegalArgumentException.class, () -> { hashUtil.hash("", ""); },  "empty salt did not throw IllegalArgumentException");
		assertThrows(IllegalArgumentException.class, () -> { hashUtil.hash("no salt", ""); }, "no salt did not throw IllegalArgumentException");
	}
	
	@Test
	public void checkPasswordsTest() {
		assertTrue("check failure for password123", hashUtil.check("password123", "example-salt", "F9C275531B697FED69296D7437635C3664E08E6BB7C4A6C73D026D34654A063C"));
		assertTrue("check failure for supersecret", hashUtil.check("supersecret", "very salty", "F4F6F6B4365E151C22940AB50D1E1977B3D5D2F35304D99F29DCEA513B555EEF"));
		assertTrue("check failure for empty password", hashUtil.check("", "only salt", "FD257F5067ABFE9B83C7CBBF86B571FF629489619831753280D1197A2BD45940"));
		assertTrue("check failure for emoticon sparkles", hashUtil.check("✨", "salt", "A57B3E814E5673B500DF70EAC1630692908023CF9A8DE8F98D8D01716C08F3A0"));
		
		assertFalse("'pass' should not pass", hashUtil.check("pass", "salt123", "C73DD743F9C275929636531B697FED664A676D34654A06E08E6BB7C43C35C026"));
		assertFalse("empty pass should not pass", hashUtil.check("", "pepper", "629257F50628FABFE9BD11973C7CBB83177A2886B589619071FFBD459404FD53"));
		
		assertThrows(IllegalArgumentException.class, () -> { hashUtil.check("anything", "", "anything"); }, "empty salt did not throw IllegalArgumentException (anything)");
		assertThrows(IllegalArgumentException.class, () -> { hashUtil.check("anything2", "", "A2886B58FABFE9BD11978969071FFBD42923C7CBFD53657F50B8317215940467"); }, "empty salt did not throw IllegalArgumentException (anything2)");
		
		assertFalse("check failure, allowed pass for empty hash", hashUtil.check("pass", "salt123", ""));
	}
	
}
