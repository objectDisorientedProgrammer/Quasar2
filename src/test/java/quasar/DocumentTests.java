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

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author doug
 *
 */
class DocumentTests
{

    /**
     * Test method for {@link quasar.Document#Document()}.
     */
    @Test
    @DisplayName("Empty document object")
    void testDocument()
    {
        Document d = new Document();
        assertNotNull(d);
        assertEquals(Quasar.DOCUMENT, d.getType());
        assertTrue(d.getDescription().isEmpty());
        assertTrue(d.getPath().isEmpty());
    }

    /**
     * Test method for {@link quasar.Document#Document(java.lang.String)}.
     */
    @Test
    @DisplayName("Document with path")
    void testDocumentString()
    {
        String unixPath = "/home/user/Documents";
        String windowsPath = "C:\\Users\\user\\Documents";
        
        Document d = new Document(unixPath);
        assertNotNull(d);
        assertEquals(Quasar.DOCUMENT, d.getType());
        assertFalse(d.getPath().isEmpty());
        
        d.setPath(windowsPath);
        assertEquals(windowsPath, d.getPath());
    }

    /**
     * Test method for {@link quasar.Document#Document(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
     */
    @Test
    @DisplayName("Document with all fields")
    void testDocumentStringStringStringString()
    {
        Document d = new Document("somepath", "666", "Famous Author", "The Publishing House");
        assertNotNull(d);
        assertEquals(Quasar.DOCUMENT, d.getType());
        assertEquals("somepath", d.getPath());
        assertEquals("666", d.getPageNumber());
        assertEquals("Famous Author", d.getAuthor());
        assertEquals("The Publishing House", d.getPublishDate());
    }

}
