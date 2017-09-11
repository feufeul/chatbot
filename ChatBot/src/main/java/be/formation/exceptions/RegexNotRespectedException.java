package be.formation.exceptions;

@SuppressWarnings("serial")
public class RegexNotRespectedException extends Exception {
	
	String message;
	
	public RegexNotRespectedException(String str) {
		message = str;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
