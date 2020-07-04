/*
 * Created: May 28, 2013
 * 
 * 
    The MIT License (MIT)
    
    Copyright (c) 2013 Gamma (Douglas Chidester, James Howard, Steve Corbette)
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
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
        this(" ", " ", " ", " ");
    }
    
    /**
     * Constructor with first name only.
     * @param fname - first name
     */
    public Contact(String fname)
    {
        this(fname, " ", " ", " ");
    }
    
    /**
     * Constructor with first and last name only.
     * @param fname - first name
     * @param lname - last name
     */
    public Contact(String fname, String lname)
    {
        this(fname, lname, " ", " ");
    }
    
    /**
     * Constructor with first name, last name, and phone number only.
     * @param fname - first name
     * @param lname - last name
     * @param phoneNum - phone number
     */
    public Contact(String fname, String lname, String phoneNum)
    {
        this(fname, lname, phoneNum, " ");
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
    
    @Override
    public String toSaveString()
    {
        return super.toSaveString() + Quasar.sep + 
                firstName + Quasar.sep + lastName + Quasar.sep + phoneNumber + Quasar.sep + email;
    }
}