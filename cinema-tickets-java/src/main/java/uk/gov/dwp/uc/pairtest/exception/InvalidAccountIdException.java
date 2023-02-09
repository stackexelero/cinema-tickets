package uk.gov.dwp.uc.pairtest.exception;

public class InvalidAccountIdException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidAccountIdException(String errorMessage) {
		super(errorMessage);
	}
}
