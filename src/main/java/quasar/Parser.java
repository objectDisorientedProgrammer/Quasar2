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

import java.util.ArrayList;

/**
 * @author doug
 *
 */
public class Parser
{
    /**
     * Split a line into data fields.
     * @param line - line to split
     * @param token - seperator character
     * @return list of all data fields
     */
    static public ArrayList<String> splitLine(String line, String token)
    {
        ArrayList<String> list = new ArrayList<String>(4);
        StringBuilder sb = new StringBuilder();
        
        for(char c : line.toCharArray())
        {
            if(c != token.charAt(0))
                sb.append(c);
            else
            {
                list.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        list.add(sb.toString());
        
        return list;
    }
}
