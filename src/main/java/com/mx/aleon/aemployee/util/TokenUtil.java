package com.mx.aleon.aemployee.util;

import java.security.SecureRandom;
import java.util.Base64;

import org.slf4j.MDC;

public class TokenUtil {

	private TokenUtil() {}
	
	/**
	 * Generates a secure random token for transactions.
	 * 
	 * @return a unique token in Base64 URL-safe format
	 */
	public static String generateSecureTokenTransaction() {
		
	    SecureRandom secureRandom = new SecureRandom();
	    byte[] randomBytes = new byte[32];
	    secureRandom.nextBytes(randomBytes);
	    String tokenTransaction =  Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
	    MDC.put("tokenTransaction", tokenTransaction);
	    return tokenTransaction;
	}

}
