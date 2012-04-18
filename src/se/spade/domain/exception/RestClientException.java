package se.spade.domain.exception;

public class RestClientException extends RuntimeException{

	public RestClientException(String message, Exception exception) {
		super(message, exception);
	}
}
