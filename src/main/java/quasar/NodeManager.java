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

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

public class NodeManager
{
    private Node head;
    private Node current;
    private Node tail;
    private int documentCount;
    private int websiteCount;
    private int pictureCount;
    private int contactCount;
    private int totalCount;
    private String dataFile;
    //private HashMap<Data, String> dataContainer = new HashMap<Data, String>();
    private ArrayList<Data> dataContainer = new ArrayList<Data>(20);
    String[] items;

    /**
     * Creates a NodeManager with the default load/save file of Quasar.dat.
     */
    public NodeManager()
    {
        super();
        initializeVariables();
        // TODO load database on program start
        //this("Quasar.dat");
    }

    /**
     * Creates a NodeManager with a specified load/save file.
     * @param dataFile - filename for the loading/saving
     */
    public NodeManager(String dataFile) {
        super();
        this.dataFile = dataFile;
        initializeVariables();
        
        if(this.dataFile != null || !this.dataFile.isEmpty())
        {
            try {
                loadFile(this.dataFile);
            } catch (IOException e) {
                System.err.println("NodeManager(String) - "+e.getMessage());
                e.printStackTrace();
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
        head = null;
        tail = null;
        
        //head = new Node(new Data("Head Node"));   // create head with title
        //tail = new Node(new Data("Tail Node"));   // create tail with title
    }

    /**
     * 
     * @param String
     * @throws IOException
     */
    public void loadFile(String filename) throws IOException {
        String file = FileUtils.readFileToString(new File(filename));//FileUtils.readFileToString(new File(filename), "UTF-8");
        String[] fileLines = file.split("\n");
        
        for(String line : fileLines)
        {
            String[] tokens = line.split("¶");
            Data d;
            
            // TODO this is a mess but works for now.
            // somehow initialize the data object and set the common attributes...
            switch(Integer.parseInt(tokens[0]))
            {
            case 1: // doc
                d = new Document(tokens[6]);
                d.setTitle(tokens[1]);
                d.setDescription(tokens[2]);
                d.setDate(tokens[3]);
                d.setKeywords(tokens[4]);
                d.setType(tokens[5]);
                
                System.out.println("Adding " + d.toString());
                
                createEntry(d);
                break;
            case 2: // web
                d = new Website(tokens[6]);
                d.setTitle(tokens[1]);
                d.setDescription(tokens[2]);
                d.setDate(tokens[3]);
                d.setKeywords(tokens[4]);
                d.setType(tokens[5]);
                
                System.out.println("Adding " + d.toString());
                
                createEntry(d);
                break;
            case 4: // pic
                d = new Picture(tokens[6]);
                d.setTitle(tokens[1]);
                d.setDescription(tokens[2]);
                d.setDate(tokens[3]);
                d.setKeywords(tokens[4]);
                d.setType(tokens[5]);
                
                System.out.println("Adding " + d.toString());
                
                createEntry(d);
                
                break;
            case 8: // contact
                d = new Contact(tokens[6], tokens[7], tokens[8], tokens[9]);
                d.setTitle(tokens[1]);
                d.setDescription(tokens[2]);
                d.setDate(tokens[3]);
                d.setKeywords(tokens[4]);
                d.setType(tokens[5]);
                
                System.out.println("Adding " + d.toString());
                
                createEntry(d);
                break;
            default:
                System.out.println("Unknown: " + line);
                break;
            }
        }
    }

    /**
     * 
     * @param String
     */
    public void saveToFile() {
        throw new UnsupportedOperationException();
    }

//  /**
//   * 
//   * @param Node
//   */
//  public void addNode(Data rawData) {
//      throw new UnsupportedOperationException();
//  }
    
    public void createEntry(Data d)
    {
        dataContainer.add(d);
    }

    public void goFirst() {
        throw new UnsupportedOperationException();
    }

    public void goNext() {
        throw new UnsupportedOperationException();
    }

    public void goPrev() {
        throw new UnsupportedOperationException();
    }

    public void goLast() {
        throw new UnsupportedOperationException();
    }

    public boolean outOfBounds() {
        throw new UnsupportedOperationException();
    }

    public boolean isEmpty() {
        return dataContainer.isEmpty();
    }
    
    public String[] getAllDataTitles()
    {
        items = new String[dataContainer.size()];
        int i = 0;
        
        for (Data data : dataContainer) {
            items[i] = data.title;
            ++i;
        }

        return items;
    }

    public Data getEntry(String title)
    {
        Data v = null;
        //return dataContainer.get(title);
        for (Data data : dataContainer) {
            if(data.title.equalsIgnoreCase(title))
            {
                v = data;
                break;
            }
        }
        return v;
    }

}
