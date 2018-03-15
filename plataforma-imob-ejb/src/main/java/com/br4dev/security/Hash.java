package com.br4dev.security;

import java.util.Arrays;
import java.util.Objects;

import com.br4dev.util.Codec;

public class Hash {

	private String algorithm;
	private byte[] value;
	private byte[] salt;
	
	public Hash(String algorithm, byte[] value, byte[] salt) {
		Objects.requireNonNull(algorithm);
		Objects.requireNonNull(value);
		this.algorithm = algorithm;
		this.value = value.clone();
		this.salt = (salt == null) ? null : salt.clone();
	}
	
	public Hash(String algorithm, byte[] value) {
		this(algorithm, value, null);
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public byte[] getValue() {
		return value.clone();
	}

	public byte[] getSalt() {
		return (salt == null) ? null : salt.clone();
	}
	
	public boolean checkHash(byte[] input) {
		HashEngine hEngine = HashEngine.valueOf(algorithm);
		Hash hResult = hEngine.hash(input, salt);
		return equals(hResult);
	}
	
	public boolean checkHash(String input) {
		return checkHash( Codec.bytes(input) );
	}
	
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append(algorithm).append(":");
		buf.append( Codec.hex(value) );
		if (salt != null) {
			buf.append(":").append( Codec.hex(salt) );
		}
		return buf.toString().toUpperCase();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((algorithm == null) ? 0 : algorithm.hashCode());
		result = prime * result + Arrays.hashCode(salt);
		result = prime * result + Arrays.hashCode(value);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Hash)) {
			return false;
		}
		Hash other = (Hash) obj;
		return algorithm.equals(other.algorithm)
			&& Arrays.equals(salt, other.salt)
			&& Arrays.equals(value, other.value);
	}
	
}
