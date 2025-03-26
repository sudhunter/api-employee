package com.mx.aleon.aemployee.util;

import org.junit.jupiter.api.Test;

import com.mx.aleon.aemployee.ex.ApiException;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DateMapperUtilTest {

    @Test
    void toDateShouldReturnDateWhenValidDateString() throws Exception {
        // Arrange
        String validDate = "15-03-2025";
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date expectedDate = formatter.parse(validDate);

        // Act
        Date result = DateMapperUtil.toDate(validDate);

        // Assert
        assertEquals(expectedDate, result);
    }

    @Test
    void toDateShouldThrowApiExceptionWhenInvalidDateString() {
        // Arrange
        String invalidDate = "15/03/2025"; // Wrong format

        // Act & Assert
        ApiException exception = assertThrows(ApiException.class, () -> {
            DateMapperUtil.toDate(invalidDate);
        });

        assertEquals("Invalid date format: " + invalidDate, exception.getMessage());
        assertEquals(-1, exception.getCode());
    }

    @Test
    void toDateShouldThrowApiExceptionWhenDateIsEmpty() {
        // Arrange
        String emptyDate = "";

        // Act & Assert
        ApiException exception = assertThrows(ApiException.class, () -> {
            DateMapperUtil.toDate(emptyDate);
        });

        assertEquals("Invalid date format: " + emptyDate, exception.getMessage());
        assertEquals(-1, exception.getCode());
    }

    @Test
    void toDateShouldThrowApiExceptionWhenDateIsNull() {
        // Arrange
        String nullDate = null;

        // Act & Assert
        ApiException exception = assertThrows(ApiException.class, () -> {
            DateMapperUtil.toDate(nullDate);
        });

        assertEquals("Invalid date format: null", exception.getMessage());
        assertEquals(-1, exception.getCode());
    }
}
