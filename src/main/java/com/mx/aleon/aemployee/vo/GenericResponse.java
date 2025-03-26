package com.mx.aleon.aemployee.vo;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents a generic response object used for API responses.
 * This class encapsulates a response code, a message, a token for the operation, 
 * and additional data related to the response.
 */
@Schema(description = "Generic Value Object for API responses")
public class GenericResponse {

    /**
     * The response code indicating the status of the operation.
     * Example: 0 for success, -1 for client error, etc.
     */
	 @Schema(description = "Response code", example = "0")
    private int code;

    /**
     * A message providing details about the operation's outcome.
     * Example: "Operation successful" or "Validation failed."
     */
	@Schema(description = "Response message", example = "SUCCESS")
    private String message;

    /**
     * A token representing the operation performed.
     * This can be used to track or identify the specific operation (e.g., a unique operation ID).
     */
	@Schema(description = "This can be used to track or identify the specific operation (e.g., a unique operation ID).", example = "X_4bqWAS0aSR4p2Sx9n8HuqBi2Prq3mwfeG6AhhtExU")
    private String tokenOperation;

    /**
     * Additional data returned as part of the response.
     * This can be any object, such as an entity, a list, or null if not applicable.
     */
    @Schema(description = "Response data", example = "{...}")
    private Object data;

    /**
     * Constructs a GenericResponse object with the specified response code, message, 
     * operation token, and additional data.
     *
     * @param tokenOperation the token identifying the operation
     */
    public GenericResponse(String tokenOperation) {
        super();
        this.tokenOperation = tokenOperation;
        this.message = "SUCCESS";
    }

    /**
     * Retrieves the response code of this GenericResponse.
     *
     * @return the response code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets the response code of this GenericResponse.
     *
     * @param code the response code to set
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Retrieves the message of this GenericResponse.
     *
     * @return the response message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message of this GenericResponse.
     *
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Retrieves the operation token of this GenericResponse.
     *
     * @return the operation token
     */
    public String getTokenOperation() {
        return tokenOperation;
    }

    /**
     * Sets the operation token of this GenericResponse.
     *
     * @param tokenOperation the operation token to set
     */
    public void setTokenOperation(String tokenOperation) {
        this.tokenOperation = tokenOperation;
    }

    /**
     * Retrieves the additional data of this GenericResponse.
     *
     * @return the data object
     */
    public Object getData() {
        return data;
    }

    /**
     * Sets the additional data of this GenericResponse.
     *
     * @param data the data object to set
     */
    public void setData(Object data) {
        this.data = data;
    }

	@Override
	public String toString() {
		return "GenericResponse [code=" + code + ", message=" + message + ", tokenOperation=" + tokenOperation
				+ ", data=" + data + "]";
	}
    
    
}
