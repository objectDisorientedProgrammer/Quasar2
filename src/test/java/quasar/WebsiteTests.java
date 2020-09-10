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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WebsiteTests
{

    @Test
    @DisplayName("All fields populated")
    void testToSaveString()
    {
        String[] filler = { "mysite.com", "2020-08-21", "a description of my site", "internet website www", "bookmark" };
        Website w = new Website(filler[0]);
        w.setDate(filler[1]);
        w.setDescription(filler[2]);
        w.setKeywords(filler[3]);
        w.setTitle(filler[4]);
        
        String expectedStr = Quasar.WEBSITE + Quasar.sep + filler[4] + Quasar.sep + filler[2] + Quasar.sep + filler[1] +
                Quasar.sep + filler[3] + Quasar.sep + filler[0];
        
        assertEquals(expectedStr, w.toSaveString());
    }
    
    @Test
    @DisplayName("Blank URL")
    void testToSaveStringNoUrl()
    {
        String[] filler = { "", "2020-08-21", "a description of my site", "internet website www", "bookmark" };
        Website w = new Website(filler[0]);
        w.setDate(filler[1]);
        w.setDescription(filler[2]);
        w.setKeywords(filler[3]);
        w.setTitle(filler[4]);
        
        String expectedStr = Quasar.WEBSITE + Quasar.sep + filler[4] + Quasar.sep + filler[2] + Quasar.sep + filler[1] +
                Quasar.sep + filler[3] + Quasar.sep + filler[0];
        
        assertEquals(expectedStr, w.toSaveString());
    }

    @Test
    void testWebsite()
    {
        String s = "www.junit.org";
        Website w = new Website(s);
        assertNotNull(w);
        assertEquals(s, w.getUrl());
        assertEquals(">no title<", w.getTitle());
        assertEquals(Quasar.WEBSITE, w.getType());
    }

    @Test
    void testGetUrl()
    {
        String s = "www.junit.org";
        Website w = new Website(s);
        assertEquals(s, w.getUrl());
    }

    @Test
    void testSetUrl()
    {
        String s = "ddg.gg";
        Website w = new Website("www.junit.org");
        w.setUrl(s);
        assertEquals(s, w.getUrl());
    }

}
