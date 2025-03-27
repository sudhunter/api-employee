package com.mx.aleon.aemployee.ex;

/**
 * Exception thrown to indicate that a requested resource could not be found.
 * This class extends the ApiException class to handle specific cases of missing resources.
 */
public class ResourceNotFoundException extends ApiException {

    /**
     * Serial version unique identifier for serialization.
     */
    private static final long serialVersionUID = 2274280812000530405L;

    /**
     * Constructs a new ResourceNotFoundException with the specified details.
     *
     * @param code           the error code representing the specific error.
     * @param message        the detailed error message.
     */
    public ResourceNotFoundException(int code, String message) {
        super(code, message);
    }
}
