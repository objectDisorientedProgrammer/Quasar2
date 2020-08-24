/**
   Created: August 23, 2020

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

import javax.swing.JPanel;

/**
 * @author doug
 *
 */
public class DocumentDisplay extends JPanel
{
    private GenericDisplay gd;
    private enum Fields { PATH, PAGE, AUTHOR, PUBLISH_DATE };
    private EnumMap<Fields, String> fieldLookup = new EnumMap<Fields, String>(Fields.class);
    private HashMap<String, String> uiData = new HashMap<String, String>(5);
    
    public DocumentDisplay()
    {
        // map enums to strings
        fieldLookup.put(Fields.PATH, "Path");
        fieldLookup.put(Fields.PAGE, "Page Number");
        fieldLookup.put(Fields.AUTHOR, "Author");
        fieldLookup.put(Fields.PUBLISH_DATE, "Publish Date");
        
        // create keys for ui data based on mapped strings
        for(String s : fieldLookup.values())
            uiData.put(s, "");
        
        gd = new GenericDisplay(uiData);
        gd.setEditable(false);
        this.add(gd);
    }
    
    public JPanel getComponent()
    {
        return gd;
    }
    
    /**
     * Display each field that is specific to a document.
     * @param d
     */
    public void displayDocument(Document d)
    {
        uiData.put(fieldLookup.get(Fields.PATH), d.getPath());
        uiData.put(fieldLookup.get(Fields.PAGE), d.getPageNumber());
        uiData.put(fieldLookup.get(Fields.AUTHOR), d.getAuthor());
        uiData.put(fieldLookup.get(Fields.PUBLISH_DATE), d.getPublishDate());
        
        gd.writeTo(uiData);
    }
    
    /**
     * Update each field that is specific to a document.
     * @param d The document to update
     */
    public void updateDocument(Document d)
    {
        gd.readFrom(uiData);
        
        d.setPath(uiData.get(fieldLookup.get(Fields.PATH)));
        d.setPageNumber(uiData.get(fieldLookup.get(Fields.PAGE)));
        d.setAuthor(uiData.get(fieldLookup.get(Fields.AUTHOR)));
        d.setPublishDate(uiData.get(fieldLookup.get(Fields.PUBLISH_DATE)));
    }

    public void setEditable(boolean editable)
    {
        if(gd != null)
            gd.setEditable(editable);
    }
}
