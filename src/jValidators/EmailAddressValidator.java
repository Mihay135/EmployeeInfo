package jValidators;

public class EmailAddressValidator {
	private boolean stricterFilter;
	
	public EmailAddressValidator() {
		this.stricterFilter = true;
	}
	
	public EmailAddressValidator setStricterFilter(boolean value) {
		this.stricterFilter = value;
		return this;
	}
	
	public boolean isValidEmailAddress(String email) {
	    String stricterFilterString = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}";
	    String laxString = ".+@.+\\.[A-Za-z]{2}[A-Za-z]*";
	    String emailRegex = stricterFilter ? stricterFilterString : laxString;
	    java.util.regex.Pattern p = java.util.regex.Pattern.compile(emailRegex);
	    java.util.regex.Matcher m = p.matcher(email);
	    return m.matches();
	}
}
