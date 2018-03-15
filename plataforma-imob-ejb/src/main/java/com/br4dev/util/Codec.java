package com.br4dev.util;

import java.io.UnsupportedEncodingException;

public final class Codec {

	private static final String CHATSET_NAME_UTF8 = "UTF-8";
	
	public static String hex(byte[] bytes) {
		StringBuilder buffer = new StringBuilder();
		for (byte b : bytes){
			int number = (b & 0xff) + 0x100;
			String hexChar = Integer.toString(number, 16).substring(1);
			buffer.append(hexChar);
		}
		return buffer.toString();
	}
	
	public static String utf8(byte[] bytes) {
		try {
			return new String(bytes, CHATSET_NAME_UTF8);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static byte[] bytes(String str) {
		try {
			return str.getBytes(CHATSET_NAME_UTF8);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Codec() {
		
	}
	
}
