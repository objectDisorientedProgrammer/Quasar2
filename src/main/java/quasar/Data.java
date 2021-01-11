/*
 * Created: May 28, 2013
 * 
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

public class Data
{
    protected String title = "";
    private static String defaultTitle = ">no title<";
    protected String description = "";
    protected String date = "";
    protected String keywords = "";
    protected int type = Quasar.ALL;
    private static int defaultType = Quasar.ALL;
    
    /**
     * Creates a data object with title set empty
     */
    public Data()
    {
        this(defaultTitle, "", "", "", defaultType);
    }

    /**
     * Create a data object with a type.
     * @param type - one character string
     */
    public Data(int type)
    {
        this(defaultTitle, "", "", "", type);
    }

    /**
     * @param title - title for the data
     * @param description - description of the data
     * @param date - relevant date
     * @param keywords - search keywords
     * @param type - one character string
     */
    public Data(String title, String description, String date, String keywords,
            int type) {
        super();
        this.title = title;
        this.description = description;
        this.date = date;
        this.keywords = keywords;
        setType(type);
    }

    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        // TODO enforce date format; will be done with https://github.com/objectDisorientedProgrammer/Quasar2/issues/52
        this.date = date;
    }
    
    public String getKeywords() {
        return keywords;
    }
    
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    
    public int getType() {
        return type;
    }
    
    /**
     * Case insensitive type setter.
     * @param type - a valid type of data
     */
    public void setType(int type) {
        if(!isValidType(type))
            System.err.println("Error undefined type in Data.setType(): " + type);
        else
            this.type = type;
    }
    
    private boolean isValidType(int t) {
        for(int i : Quasar.entryTypes)
            if(i == t)
                return true;
        return false;
    }

    /**
     * Convert a Data object to a String object.
     * @return a string with all populated fields
     */
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        
        // Only add title
        if(title != null && title.length() > 0)
            sb.append(title);
        else
            sb.append(defaultTitle);
        
        return sb.toString();
    }
    
    /**
     * 
     * @return String that can be written to a save file.
     */
    public String toSaveString()
    {
        return type + Quasar.sep + title + Quasar.sep + description + Quasar.sep + date + Quasar.sep + keywords;
    }
}
