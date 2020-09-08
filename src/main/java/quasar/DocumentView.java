/**
   Created: September 7, 2020

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
public class DocumentView extends ViewService
{
    private enum Fields { PATH, PAGE, AUTHOR, PUBLISH_DATE };
    private EnumMap<Fields, String> fieldLookup = new EnumMap<Fields, String>(Fields.class);
    private Document data;
    
    public DocumentView()
    {
        // map enums to strings
        fieldLookup.put(Fields.PATH, "Path");
        fieldLookup.put(Fields.PAGE, "Page Number");
        fieldLookup.put(Fields.AUTHOR, "Author");
        fieldLookup.put(Fields.PUBLISH_DATE, "Publish Date");
        
        // initialize UI data object and create keys based on mapped strings
        uiData = new HashMap<String, String>(fieldLookup.size());
        
        for(String s : fieldLookup.values())
            uiData.put(s, "");
        
        gd = new GenericDisplay(uiData);
        gd.setEditable(false);
    }

    void display(Document d)
    {
        data = d;
        uiData.put(fieldLookup.get(Fields.PATH), data.getPath());
        uiData.put(fieldLookup.get(Fields.PAGE), data.getPageNumber());
        uiData.put(fieldLookup.get(Fields.AUTHOR), data.getAuthor());
        uiData.put(fieldLookup.get(Fields.PUBLISH_DATE), data.getPublishDate());
        
        gd.writeTo(uiData);
    }
    
    @Override
    void update()
    {
        gd.readFrom(uiData);
        
        data.setPath(uiData.get(fieldLookup.get(Fields.PATH)));
        data.setPageNumber(uiData.get(fieldLookup.get(Fields.PAGE)));
        data.setAuthor(uiData.get(fieldLookup.get(Fields.AUTHOR)));
        data.setPublishDate(uiData.get(fieldLookup.get(Fields.PUBLISH_DATE)));
    }

}
