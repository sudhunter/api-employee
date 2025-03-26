package com.mx.aleon.aemployee.util;

import org.junit.jupiter.api.Test;
import org.slf4j.MDC;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

class TokenUtilTest {

    @Test
    void generateSecureTokenTransactionShouldReturnUniqueTokens() {
        // Act
        String token1 = TokenUtil.generateSecureTokenTransaction();
        String token2 = TokenUtil.generateSecureTokenTransaction();

        // Assert
        assertNotNull(token1); // El token no debe ser nulo
        assertNotNull(token2);
        assertNotEquals(token1, token2); // Los tokens deben ser únicos
    }

    @Test
    void generateSecureTokenTransactionShouldReturnBase64UrlSafeToken() {
        // Act
        String token = TokenUtil.generateSecureTokenTransaction();

        // Assert
        assertNotNull(token);
        assertDoesNotThrow(() -> {
            Base64.getUrlDecoder().decode(token); // Decodes the token without throwing an exception
        });

    }

    @Test
    void generateSecureTokenTransactionShouldStoreTokenInMDC() {
        // Act
        String token = TokenUtil.generateSecureTokenTransaction();

        // Assert
        assertNotNull(token);
        assertEquals(token, MDC.get("tokenTransaction")); // Verifica que el token se haya almacenado en MDC
    }
}
