package uk.gov.dwp.uc.pairtest.exception;

public class InvalidPurchaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidPurchaseException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
	public InvalidPurchaseException(String errorMessage) {
		super(errorMessage);
	}
}
