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
class PictureTests
{

    /**
     * Test method for {@link quasar.Picture#toSaveString()}.
     */
    @Disabled
    @Test
    void testToSaveString()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link quasar.Picture#Picture(java.lang.String)}.
     */
    @Test
    @DisplayName("Constructor with path")
    void testPictureString()
    {
        String unixPath = "/home/user/Pictures";
        String windowsPath = "C:\\Users\\user\\Pictures";
        
        Picture p = new Picture(unixPath);
        assertNotNull(p);
        assertEquals(unixPath, p.getPath());
        assertEquals(0, p.getWidth());
        assertEquals(0, p.getHeight());
        assertEquals(Quasar.PICTURE, p.getType());
        
        p.setPath(windowsPath);
        assertEquals(windowsPath, p.getPath());
    }

    /**
     * Test method for {@link quasar.Picture#Picture(java.lang.String, java.lang.String)}.
     */
    @Test
    @DisplayName("Constructor with path and photographer")
    void testPictureStringString()
    {
        String unixPath = "/home/user/Pictures";
        String photographer = "Martin Vanguess";
        
        Picture p = new Picture(unixPath, photographer);
        assertNotNull(p);
        assertEquals(unixPath, p.getPath());
        assertEquals(photographer, p.getPublisher());
        assertEquals(0, p.getWidth());
        assertEquals(0, p.getHeight());
        assertEquals(Quasar.PICTURE, p.getType());
    }

    /**
     * Test method for {@link quasar.Picture#Picture(java.lang.String, java.lang.String, int, int)}.
     */
    @Test
    void testPictureStringStringIntInt()
    {
        String unixPath = "/home/user/Pictures";
        String photographer = "Fakie O'Madeup";
        int w = 1920;
        int h = 1080;
        
        Picture p1 = new Picture("somepath", "someone", -234, -11929);
        assertNotNull(p1);
        assertEquals(0, p1.getWidth());
        assertEquals(0, p1.getHeight());
        assertEquals(Quasar.PICTURE, p1.getType());
        
        Picture p = new Picture(unixPath, photographer, w, h);
        assertNotNull(p);
        assertEquals(unixPath, p.getPath());
        assertEquals(photographer, p.getPublisher());
        assertEquals(w, p.getWidth());
        assertEquals(h, p.getHeight());
        assertEquals(Quasar.PICTURE, p.getType());
    }

    /**
     * Test method for {@link quasar.Picture#setWidth(int)}.
     */
    @Test
    void testSetWidth()
    {
        int w = 234;
        
        Picture p = new Picture("somepath", "someone", w, -11929);
        assertNotNull(p);
        
        assertEquals(w, p.getWidth());
        
        p.setWidth(-1 * p.getWidth());
        assertEquals(w, p.getWidth());
        
        p.setWidth(0);
        assertEquals(w, p.getWidth());
        
        p.setWidth(w * w);
        assertEquals(w * w, p.getWidth());
    }

    /**
     * Test method for {@link quasar.Picture#setHeight(int)}.
     */
    @Test
    void testSetHeight()
    {
        int h = 11929;
        
        Picture p = new Picture("somepath", "someone", -234, h);
        assertNotNull(p);
        
        assertEquals(h, p.getHeight());
        
        p.setHeight(-1 * p.getHeight());
        assertEquals(h, p.getHeight());
        
        p.setHeight(0);
        assertEquals(h, p.getHeight());
        
        p.setHeight(h - (h >> 1));
        assertEquals(h - (h >> 1), p.getHeight());
    }

}
