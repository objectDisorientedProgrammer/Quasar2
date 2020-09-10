/**
   Created: August 21, 2020

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
import org.junit.jupiter.api.Test;

class DataTests
{

    @Test
    void testData()
    {
        Data d = new Data();
        assertNotNull(d);
        assertEquals(">no title<", d.getTitle());
        assertEquals(Quasar.ALL, d.getType());
    }

    @Test
    void testDataInt()
    {
        Data d = new Data(Quasar.ALL);
        assertNotNull(d);
        assertEquals(Quasar.ALL, d.getType());
        
        Data d2 = new Data(Quasar.CONTACT);
        assertNotNull(d2);
        assertEquals(Quasar.CONTACT, d2.getType());
        
        Data d3 = new Data(Quasar.DOCUMENT);
        assertNotNull(d3);
        assertEquals(Quasar.DOCUMENT, d3.getType());
        
        Data d4 = new Data(Quasar.PICTURE);
        assertNotNull(d4);
        assertEquals(Quasar.PICTURE, d4.getType());
        
        Data d5 = new Data(Quasar.WEBSITE);
        assertNotNull(d5);
        assertEquals(Quasar.WEBSITE, d5.getType());
    }

    @Test
    void testDataStringStringStringStringInt()
    {
        Data d = new Data("title", "desc", "2020-08-22", "dummy testing blah", Quasar.DOCUMENT);
        assertNotNull(d);
        assertEquals("title", d.getTitle());
        assertEquals("desc", d.getDescription());
        assertEquals("2020-08-22", d.getDate());
        
        assertEquals("dummy testing blah", d.getKeywords());
        assertEquals("dummy", d.getKeywords().split(" ")[0]);
        assertEquals("testing", d.getKeywords().split(" ")[1]);
        assertEquals("blah", d.getKeywords().split(" ")[2]);
        
        assertEquals(Quasar.DOCUMENT, d.getType());
    }

    @Test
    void testToString()
    {
        Data d = new Data();
        assertEquals(">no title<", d.toString());
        
        d.setTitle("a different title");
        assertEquals("a different title", d.toString());
    }

    @Disabled
    @Test
    void testToSaveString()
    {
        fail("Not yet implemented");
    }

}
