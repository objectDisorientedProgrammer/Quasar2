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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

public class EntryController
{
    private boolean DEBUG_PRINT = false;
    private String encoding = "UTF-8";
    private int documentCount;
    private int websiteCount;
    private int pictureCount;
    private int contactCount;
    private int totalCount;
    private String dataFile;
    private ArrayList<Data> dataContainer = new ArrayList<Data>(20);
    private String[] items;
    

    /**
     * Creates a EntryController with a specified load/save file.
     * @param dataFile - filename for loading/saving
     */
    public EntryController(String dataFile) {
        super();
        this.dataFile = dataFile;
        initializeVariables();
        
        if(this.dataFile != null || !this.dataFile.isEmpty())
        {
            try {
                loadFile(this.dataFile);
            } catch (IOException e) {
                // TODO write to log file instead of console. This message can happen if 1) first time use or 2) save file deleted
                System.err.println("EntryController(String) - " + e.getMessage() + ".");
            }
        }
        
    }

    /**
     * Reset counts and set up head/tail nodes.
     */
    private void initializeVariables() {
        this.documentCount = 0;
        this.websiteCount = 0;
        this.pictureCount = 0;
        this.contactCount = 0;
        this.totalCount = 0;
    }

    /**
     * 
     * @param String
     * @throws IOException
     */
    public void loadFile(String filename) throws IOException {
        String file = FileUtils.readFileToString(new File(filename));//FileUtils.readFileToString(new File(filename), encoding);
        String[] fileLines = file.split("\n");
        
        for(String line : fileLines)
        {
            String[] tokens = line.split(Quasar.sep);
            Data d;
            int type = Integer.parseInt(tokens[0]);
            
            // TODO this is a mess but works for now.
            // somehow initialize the data object and set the common attributes...
            switch(type)
            {
            default:
            case Quasar.ALL:
                d = new Data(tokens[1], tokens[2], tokens[3], tokens[4], type);
                
                if(DEBUG_PRINT)
                    System.out.println("Adding " + d.toString());
            	++totalCount;
                break;
            case Quasar.DOCUMENT: // doc
                d = new Document(tokens[5], tokens[6], tokens[7], tokens[8]);
                d.setTitle(tokens[1]);
                d.setDescription(tokens[2]);
                d.setDate(tokens[3]);
                d.setKeywords(tokens[4]);
                d.setType(type);
                
                if(DEBUG_PRINT)
                    System.out.println("Adding " + d.toString());
                ++documentCount;
                addEntry(d);
                break;
            case Quasar.WEBSITE: // web
                d = new Website(tokens[5]);
                d.setTitle(tokens[1]);
                d.setDescription(tokens[2]);
                d.setDate(tokens[3]);
                d.setKeywords(tokens[4]);
                d.setType(type);
                
                if(DEBUG_PRINT)
                    System.out.println("Adding " + d.toString());
                ++websiteCount;
                addEntry(d);
                break;
            case Quasar.PICTURE: // pic
                d = new Picture(tokens[5]);
                d.setTitle(tokens[1]);
                d.setDescription(tokens[2]);
                d.setDate(tokens[3]);
                d.setKeywords(tokens[4]);
                d.setType(type);
                
                if(DEBUG_PRINT)
                    System.out.println("Adding " + d.toString());
                ++pictureCount;
                addEntry(d);
                
                break;
            case Quasar.CONTACT: // contact
                d = new Contact(tokens[5], tokens[6], tokens[7], tokens[8]);
                d.setTitle(tokens[1]);
                d.setDescription(tokens[2]);
                d.setDate(tokens[3]);
                d.setKeywords(tokens[4]);
                d.setType(type);
                
                if(DEBUG_PRINT)
                    System.out.println("Adding " + d.toString());
                ++contactCount;
                addEntry(d);
                break;
            
            }
        }
    }

    /**
     * 
     * @param filePath - path to the save file.
     * @throws IOException 
     */
    public void saveToFile(String filePath) throws IOException
    {
        StringBuilder b = new StringBuilder(dataContainer.size() * 4); // * 4 for the four default fields
        
        for(Data d : dataContainer)
        {
            if(d instanceof Contact)
            {
                Contact c = (Contact) d;
                b.append(c.toSaveString());
            }
            else if(d instanceof Document)
            {
                Document doc = (Document) d;
                b.append(doc.toSaveString());
            }
            else if(d instanceof Picture)
            {
                Picture p = (Picture) d;
                b.append(p.toSaveString());
            }
            else if(d instanceof Website)
            {
                Website w = (Website) d;
                b.append(w.toSaveString());
            }
            else
            {
                // regular Data
                b.append(d.toSaveString());
            }
            
            b.append('\n');
        }
        
        if(DEBUG_PRINT)
            System.out.println("Saving: '" + b.toString() + "' to file...");
        
        FileUtils.writeStringToFile(new File(filePath), b.toString(), encoding, false);
    }
    
    public void addEntry(Data d)
    {
        dataContainer.add(d);
    }

    public boolean isEmpty() {
        return dataContainer.isEmpty();
    }
    
    public Data[] getAllData()
    {
        Data ary[] = new Data[dataContainer.size()];
        int i = 0;
        for(Data d : dataContainer)
        {
            ary[i] = d;
            ++i;
        }
        return ary;
    }
    
    public String[] getAllDataTitles()
    {
        items = new String[dataContainer.size()];
        int i = 0;
        
        for (Data data : dataContainer)
        {
            items[i] = data.title;
            ++i;
        }
        
        return items;
    }

    /**
     * Get an entry by title string.
     * @param title Entry to look up.
     * @return Data object or {@code null} if not found.
     */
    public Data getEntry(String title)
    {
        for (Data data : dataContainer)
        {
            if(data.title.equalsIgnoreCase(title))
            {
                return data;
            }
        }
        return null;
    }
    
    /**
     * Get an entry by index.
     * @param index
     * @return Data object.
     */
    public Data getEntry(int index)
    {
        return dataContainer.get(index);
    }
    
    public boolean search(String searchString, int filter, Vector<Data> results)
    {
        for(Data data : dataContainer)
        {
            if (filter == Quasar.ALL)
            {
                // gather all entries
                if (searchString.equalsIgnoreCase(""))
                    results.add(data);
                //search only titles
                else if (data.getTitle().contains(searchString))
                {
                    results.add(data);
                }
            }
            else
            {
                //search by type, then by title
                if (filter == data.getType())
                {
                    // if blank search, gather all entries of the type
                    if (searchString.equalsIgnoreCase(""))
                    {
                        results.add(data);
                    }
                    // gather all entries that match the query text
                    else if (data.getTitle().contains(searchString))
                    {
                        results.add(data);
                    }
                }
            }
        }
        
        return results.size() > 0;
    }

    public Data getEntry(Data selectedValue)
    {
        for(Data d : dataContainer)
        {
            if(d.equals(selectedValue))
                return d;
        }
        return null;
    }
}
