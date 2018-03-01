package webapp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CustomValidator {
	@NotNull(message = "Zip Code cannot be null")
	@Pattern(regexp = "^[0-9]{5}?[-.][0-9]{4}?$",message="Pattern mismatch")
	//@Pattern(regexp = "[0-9]{5}$",message="Pattern mismatch")
	private String zipcode;
	
	@NotNull(message = "Invalid email address")

	private String email;
	

	public String getZipcode() {
		return zipcode;
	}

	public CustomValidator(String zipcode, String email) {
		super();
		this.zipcode = zipcode;
		this.email = email;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	

}
