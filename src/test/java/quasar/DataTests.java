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

import org.junit.jupiter.api.Test;

class DataTests
{

    @Test
    void testData()
    {
        Data d = new Data();
        assertNotNull(d);
        assertEquals("no title", d.getTitle());
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
        assertEquals(Quasar.CONTACT, d.getType());
        
        Data d3 = new Data(Quasar.DOCUMENT);
        assertNotNull(d3);
        assertEquals(Quasar.DOCUMENT, d.getType());
        
        Data d4 = new Data(Quasar.PICTURE);
        assertNotNull(d4);
        assertEquals(Quasar.PICTURE, d.getType());
        
        Data d5 = new Data(Quasar.WEBSITE);
        assertNotNull(d5);
        assertEquals(Quasar.WEBSITE, d.getType());
    }

    @Test
    void testDataStringStringStringStringInt()
    {
        fail("Not yet implemented");
    }

    @Test
    void testGetKeywords()
    {
        fail("Not yet implemented");
    }

    @Test
    void testSetKeywords()
    {
        fail("Not yet implemented");
    }

    @Test
    void testToString()
    {
        fail("Not yet implemented");
    }

    @Test
    void testToSaveString()
    {
        fail("Not yet implemented");
    }

}
