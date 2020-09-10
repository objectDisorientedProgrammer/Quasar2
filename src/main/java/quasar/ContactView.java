/**
   Created: August 24, 2020

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

import java.util.EnumMap;
import java.util.HashMap;

/**
 * @author Doug
 */
public class ContactView extends ViewService
{
    private enum Fields { FIRSTNAME, LASTNAME, PHONE, EMAIL };
    private EnumMap<Fields, String> fieldLookup = new EnumMap<Fields, String>(Fields.class);
    private Contact data;
    
    public ContactView()
    {
        // map enums to strings
        fieldLookup.put(Fields.FIRSTNAME, "First");
        fieldLookup.put(Fields.LASTNAME, "Last");
        fieldLookup.put(Fields.PHONE, "Phone");
        fieldLookup.put(Fields.EMAIL, "Email");
        
        // initialize UI data object and create keys based on mapped strings
        uiData = new HashMap<String, String>(fieldLookup.size());
        
        for(String s : fieldLookup.values())
            uiData.put(s, "");
        
        gd = new GenericDisplay(uiData);
        gd.setEditable(false);
    }
    
    void display(Contact c)
    {
        data = c;
        uiData.put(fieldLookup.get(Fields.FIRSTNAME), data.getFirstName());
        uiData.put(fieldLookup.get(Fields.LASTNAME), data.getLastName());
        uiData.put(fieldLookup.get(Fields.PHONE), data.getPhoneNumber());
        uiData.put(fieldLookup.get(Fields.EMAIL), data.getEmail());
        
        gd.writeTo(uiData);
    }
    
    @Override
    void update()
    {
        gd.readFrom(uiData);
        
        data.setFirstName(uiData.get(fieldLookup.get(Fields.FIRSTNAME)));
        data.setLastName(uiData.get(fieldLookup.get(Fields.LASTNAME)));
        data.setPhoneNumber(uiData.get(fieldLookup.get(Fields.PHONE)));
        data.setEmail(uiData.get(fieldLookup.get(Fields.EMAIL)));
    }
    
    void setContact(Contact c)
    {
        data = c;
    }

}
