/*
 * Created: May 28, 2013
 */

package quasar;

public class Contact extends Data
{
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String email;
	
	/**
	 * Constructor with all blank fields.
	 */
	public Contact()
	{
		this("", "", "", "");
	}
	
	/**
	 * Constructor with first name only.
	 * @param fname - first name
	 */
	public Contact(String fname)
	{
		this(fname, "", "", "");
	}
	
	/**
	 * Constructor with first and last name only.
	 * @param fname - first name
	 * @param lname - last name
	 */
	public Contact(String fname, String lname)
	{
		this(fname, lname, "", "");
	}
	
	/**
	 * Constructor with first name, last name, and phone number only.
	 * @param fname - first name
	 * @param lname - last name
	 * @param phoneNum - phone number
	 */
	public Contact(String fname, String lname, String phoneNum)
	{
		this(fname, lname, phoneNum, "");
	}

	/**
	 * Constructor with all fields.
	 * @param firstName
	 * @param lastName
	 * @param phoneNumber
	 * @param email
	 */
	public Contact(String firstName, String lastName, String phoneNumber,
			String email)
	{
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}