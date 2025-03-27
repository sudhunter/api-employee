package com.mx.aleon.aemployee.ex;

/**
 * Exception thrown to indicate a bad request error.
 * This class extends the ApiException class to handle specific cases of bad requests.
 */
public class BadRequestException extends ApiException {

    /**
     * Serial version unique identifier for serialization.
     */
    private static final long serialVersionUID = 7242734215316204423L;

    /**
     * Constructs a new BadRequestException with the specified details.
     *
     * @param code           the error code representing the specific error.
     * @param message        the detailed error message.
     */
    public BadRequestException(int code, String message) {
        super(code, message);
    }
}
