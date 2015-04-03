package uk.org.freedonia.jsparsefiles.creator.validator;

public class ValidationResult {
	
	private boolean isValid;
	private String message;

	public ValidationResult( String message, boolean isValid ) {
		this.setValid(isValid);
		this.setMessage(message);
	}
	
	public ValidationResult()  {
		this.setValid(true);
		this.setMessage("");
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
