/**
   Created: August 22, 2020

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

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author doug
 *
 */
class ContactTests
{

    /**
     * Test method for {@link quasar.Contact#toSaveString()}.
     */
    @Disabled
    @Test
    void testToSaveString()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link quasar.Contact#Contact()}.
     */
    @Test
    @DisplayName("Empty Contact")
    void testContact()
    {
        Contact c = new Contact();
        assertNotNull(c);
        assertEquals(Quasar.CONTACT, c.getType());
        assertTrue(c.getFirstName().isEmpty());
        assertTrue(c.getLastName().isEmpty());
    }

    /**
     * Test method for {@link quasar.Contact#Contact(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    void testContactStringStringStringString()
    {
        String phone = "1-555-444-3333";
        String email = "janethebane-ofourexistance@doh.net";
        
        Contact c = new Contact("Jane", "D'oh", phone, email);
        assertNotNull(c);
        assertEquals(Quasar.CONTACT, c.getType());
        assertEquals("Jane", c.getFirstName());
        assertEquals("D'oh", c.getLastName());
        assertEquals(phone, c.getPhoneNumber());
        assertEquals(email, c.getEmail());
    }

}
