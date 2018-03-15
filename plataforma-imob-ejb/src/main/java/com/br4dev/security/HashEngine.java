package com.br4dev.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Objects;

import com.br4dev.util.Codec;

public enum HashEngine {
	
	MD2("MD2"),
	MD5("MD5"),
	SHA1("SHA-1"),
	SHA256("SHA-256"),
	SHA384("SHA-384"),
	SHA512("SHA-512");
	
	private static final int DEFAULT_SIZE_SALT = 8; // bytes
	
	private String algorithm;
	
	private HashEngine(String algorithm) {
		this.algorithm = algorithm;
	}
	
	public static byte[] genSalt() {
		return SecureRandom.getSeed(DEFAULT_SIZE_SALT);
	}
	
	public byte[] digest(byte[] input, byte[] salt) throws MethodDigestException {
		Objects.requireNonNull(input);
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(input);
			if (salt != null) {
				md.update(salt);
			}
			return md.digest();
		} catch (NoSuchAlgorithmException e) {
			throw new MethodDigestException(e);
		}
	}
	
	public byte[] digest(byte[] input) throws MethodDigestException {
		return digest(input, null);
	}
	
	public Hash hash(byte[] input, byte[] salt) throws MethodDigestException {
		byte[] output = digest(input, salt);
		return new Hash(name(), output, salt);
	}
	
	public Hash hash(byte[] input) throws MethodDigestException {
		return hash(input, null);
	}
	
	public Hash hashWithoutSalt(String input) throws MethodDigestException {
		byte[] output = digest( Codec.bytes(input), null);
		return new Hash(name(), output, null);
	}
	
	public Hash hashWithRandomSalt(String input) throws MethodDigestException {
		return hash( Codec.bytes(input), genSalt());
	}
	
	public static void main(String[] args) {
		Hash hash1 = SHA256.hashWithRandomSalt("marcelo");
		Hash hash2 = SHA256.hashWithRandomSalt("marcelo");
		
		System.out.println(hash1 + " " + hash1.checkHash("marcelo"));
		System.out.println(hash2 + " " + hash2.checkHash("marcelo"));
		
		System.out.println(MD2.hashWithRandomSalt("marcelo"));
		System.out.println(MD5.hashWithRandomSalt("marcelo"));
		System.out.println(SHA1.hashWithRandomSalt("marcelo"));
		System.out.println(SHA256.hashWithRandomSalt("marcelo"));
		System.out.println(SHA384.hashWithRandomSalt("marcelo"));
		System.out.println(SHA512.hashWithRandomSalt("marcelo"));
		
		System.out.println(SHA256.hashWithoutSalt("marcelo"));
		System.out.println(SHA256.hashWithoutSalt("marcelo"));

	}
	
}
