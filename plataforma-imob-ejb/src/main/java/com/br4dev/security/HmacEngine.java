package com.br4dev.security;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.br4dev.util.Codec;

public enum HmacEngine {

	MD5("HmacMD5"),
	SHA1("HmacSHA1"),
	SHA256("HmacSHA256"),
	SHA384("HmacSHA384"),
	SHA512("HmacSHA512");
	
	private String algorithm;
	
	private HmacEngine(String algorithm) {
		this.algorithm = algorithm;
	}
	
	public byte[] digest(byte[] input, byte[] key) throws MethodDigestException {
		try {
			SecretKeySpec signingKey = new SecretKeySpec(key, algorithm);
			Mac mac = Mac.getInstance(algorithm);
			mac.init(signingKey);
			return mac.doFinal(input);
		} catch (NoSuchAlgorithmException e) {
			throw new MethodDigestException(e);
		} catch (InvalidKeyException e) {
			throw new MethodDigestException(e);
		}
	}
	
	public Hash hash(byte[] input, byte[] key) throws MethodDigestException {
		byte[] output = digest(input, key);
		return new Hash(name(), output);
	}
	
	public Hash hash(String input, String key) throws MethodDigestException {
		byte[] output = digest( Codec.bytes(input), Codec.bytes(key));
		return new Hash(name(), output, null);
	}
	
}
