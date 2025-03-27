package com.mx.aleon.aemployee.ex;

/**
 * ApiException is a custom exception class used to handle API-related errors.
 * This exception includes an error code and a message describing the error.
 */
public class ApiException extends RuntimeException {

    /** 
     * Serial version UID for ensuring compatibility during the deserialization process.
     */
    private static final long serialVersionUID = 2403853015801911181L;

    /** 
     * The error code associated with the exception.
     */
    private final int code;

    /**
     * Constructs a new ApiException with the specified error code and message.
     *
     * @param code    the error code representing the specific error
     * @param message the detail message explaining the cause of the exception
     */
    public ApiException(int code, String message) {
        super(message);
        this.code = code;
    }

    /**
     * Retrieves the error code associated with this exception.
     *
     * @return the error code representing the specific error
     */
    public int getCode() {
        return code;
    }
    
}
