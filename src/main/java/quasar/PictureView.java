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
public class PictureView extends ViewService
{
    private enum Fields { PATH, PHOTOGRAPHER, WIDTH, HEIGHT };
    private EnumMap<Fields, String> fieldLookup = new EnumMap<Fields, String>(Fields.class);
    private Picture data;
    
    public PictureView()
    {
        // map enums to strings
        fieldLookup.put(Fields.PATH, "Path");
        fieldLookup.put(Fields.PHOTOGRAPHER, "Photographer");
        fieldLookup.put(Fields.WIDTH, "Width");
        fieldLookup.put(Fields.HEIGHT, "Height");
        
        // initialize UI data object and create keys based on mapped strings
        uiData = new HashMap<String, String>(fieldLookup.size());
        
        for(String s : fieldLookup.values())
            uiData.put(s, "");
        
        gd = new GenericDisplay(uiData);
        gd.setEditable(false);
    }

    void display(Picture p)
    {
        data = p;
        uiData.put(fieldLookup.get(Fields.PATH), data.getPath());
        uiData.put(fieldLookup.get(Fields.PHOTOGRAPHER), data.getPublisher());
        uiData.put(fieldLookup.get(Fields.WIDTH), Integer.toString(data.getWidth()));
        uiData.put(fieldLookup.get(Fields.HEIGHT), Integer.toString(data.getHeight()));
        
        gd.writeTo(uiData);
    }
    
    @Override
    void update()
    {
        gd.readFrom(uiData);
        
        data.setPath(uiData.get(fieldLookup.get(Fields.PATH)));
        data.setPublisher(uiData.get(fieldLookup.get(Fields.PHOTOGRAPHER)));
        
        String temp = uiData.get(fieldLookup.get(Fields.WIDTH));
        if(!temp.isEmpty())
            data.setWidth(Integer.parseInt(temp));
        
        temp = uiData.get(fieldLookup.get(Fields.HEIGHT));
        if(!temp.isEmpty())
            data.setHeight(Integer.parseInt(temp));
    }
    
    void setPicture(Picture p)
    {
        data = p;
    }

}
