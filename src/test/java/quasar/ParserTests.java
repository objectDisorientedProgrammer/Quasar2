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

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

/**
 * @author doug
 *
 */
class ParserTests
{

    /**
     * Test method for {@link quasar.Parser#splitLine(java.lang.String, java.lang.String)}.
     */
    @Test
    void testSplitLine()
    {
        String sep = Quasar.sep;
        
        // Quasar.ALL type with no data
        String lineToParse = "0"+sep+sep+sep+"    -  -  "+sep;
        ArrayList<String> answers = new ArrayList<String>();
        answers.add("0");
        answers.add("");
        answers.add("");
        answers.add("    -  -  ");
        answers.add("");
        ArrayList<String> list = Parser.splitLine(lineToParse, sep);
        assertEquals(answers, list);
        
        // Quasar.ALL type
         lineToParse = "0"+sep+"TEST"+sep+sep+sep+"key word, and, something";
        answers = new ArrayList<String>();
        answers.add("0");
        answers.add("TEST");
        answers.add("");
        answers.add("");
        answers.add("key word, and, something");
        list = Parser.splitLine(lineToParse, sep);
        assertEquals(answers, list);
        
        // Quasar.ALL type with full data
        lineToParse = "0"+sep+"title"+sep+"desc"+sep+"2020-08-22"+sep+"key wor dz";
        answers = new ArrayList<String>();
        answers.add("0");
        answers.add("title");
        answers.add("desc");
        answers.add("2020-08-22");
        answers.add("key wor dz");
        list = Parser.splitLine(lineToParse, sep);
        assertEquals(answers, list);
        
        // Quasar.CONTACT with no data
        lineToParse = "4"+sep+sep+sep+sep+sep+sep+sep+sep;
        answers = new ArrayList<String>();
        answers.add("4");
        answers.add("");
        answers.add("");
        answers.add("");
        answers.add("");
        answers.add("");
        answers.add("");
        answers.add("");
        answers.add("");
        list = Parser.splitLine(lineToParse, sep);
        assertEquals(answers, list);
        
        // Quasar.CONTACT with full data
        lineToParse = "4"+sep+"Test cont"+sep+"decr"+sep+"1400-02-13"+sep+"kwrd, another,again"+sep+
                "Mr. Van Hass"+sep+"GuttenBergz"+sep+"888-555-1234"+sep+"Test9@Test10.com";
        answers = new ArrayList<String>();
        answers.add("4");
        answers.add("Test cont");
        answers.add("decr");
        answers.add("1400-02-13");
        answers.add("kwrd, another,again");
        answers.add("Mr. Van Hass");
        answers.add("GuttenBergz");
        answers.add("888-555-1234");
        answers.add("Test9@Test10.com");
        list = Parser.splitLine(lineToParse, sep);
        assertEquals(answers, list);
    }

}
