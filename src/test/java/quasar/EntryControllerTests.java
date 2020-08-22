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

/**
 * @author doug
 *
 */
class EntryControllerTests
{

    /**
     * Test method for {@link quasar.EntryController#EntryController(java.lang.String)}.
     */
    @Test
    void testEntryController()
    {
        EntryController ec = new EntryController(null);
        assertEquals(0, ec.getTotalEntryCount());
    }

    /**
     * Test method for {@link quasar.EntryController#loadFile(java.lang.String)}.
     */
    @Disabled
    @Test
    void testLoadFile()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link quasar.EntryController#saveToFile(java.lang.String)}.
     */
    @Disabled
    @Test
    void testSaveToFile()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link quasar.EntryController#addEntry(quasar.Data)}.
     */
    @Disabled
    @Test
    void testAddEntry()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link quasar.EntryController#isEmpty()}.
     */
    @Test
    void testIsEmpty()
    {
        EntryController ec = new EntryController(null);
        assertEquals(true, ec.isEmpty());
    }

    /**
     * Test method for {@link quasar.EntryController#getAllData()}.
     */
    @Disabled
    @Test
    void testGetAllData()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link quasar.EntryController#getAllDataTitles()}.
     */
    @Disabled
    @Test
    void testGetAllDataTitles()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link quasar.EntryController#getEntry(java.lang.String)}.
     */
    @Disabled
    @Test
    void testGetEntryString()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link quasar.EntryController#getEntry(int)}.
     */
    @Disabled
    @Test
    void testGetEntryInt()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link quasar.EntryController#search(java.lang.String, int, java.util.Vector)}.
     */
    @Disabled
    @Test
    void testSearch()
    {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link quasar.EntryController#getEntry(quasar.Data)}.
     */
    @Disabled
    @Test
    void testGetEntryData()
    {
        fail("Not yet implemented");
    }

}
