/*
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
import java.util.Vector;

public class Quasar
{
    public static final String applicationVersion = " v0.7.6";
    
    private static final String defaultFilename = "quasar.dat";
    private static final String defaultFilepath = System.getProperty("user.home") + File.separator + defaultFilename;
    public static final String sep = "\u00A6";
    
    public static final int ALL = 0;
    public static final int CONTACT = 1;
    public static final int DOCUMENT = 2;
    public static final int PICTURE = 3;
    public static final int WEBSITE = 4;
    
    public static final int[] entryTypes = new int[] { ALL, CONTACT, DOCUMENT, PICTURE, WEBSITE };
    public static final String[] entryTypeStrings = new String[]{ "All", "Contact", "Document", "Picture", "Website" };
    
    private static EntryController controller;
    private static MainWindow mainWindow;
    private static EditWindow editWindow;
    
    public static void main(String[] args)
    {
        Data entry = null;
    	
    	controller = new EntryController(defaultFilepath);
    	mainWindow = new MainWindow(entry, defaultFilepath);
    	
    	editWindow = new EditWindow(entry);
    	
//        javax.swing.SwingUtilities.invokeLater(new Runnable()
//        {
//            public void run()
//            {
//            	
//            }
//        });
    }
    
    public static boolean search(String searchString, int filter, Vector<Data> results)
    {
        return controller.search(searchString, filter, results);
    }
    
    public static Data[] getAllData()
    {
        return controller.getAllData();
    }
    
    public static boolean isEmpty()
    {
        return controller.isEmpty();
    }

    public static void loadFile(String databaseFilePath) throws IOException
    {
        controller.loadFile(databaseFilePath);
    }

    public static void saveToFile(String databaseFilePath)
    {
        try
        {
            controller.saveToFile(databaseFilePath);
        } catch(IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }        
    }

    public static Data getEntry(Data selectedValue)
    {
        return controller.getEntry(selectedValue);
    }

    public static void displayEntry(Data d)
    {
        // TODO decode data by type and call a specific display method in editWindow
        editWindow.displayEntry(d);
        editWindow.showFrame();
    }

    public static void refreshEntryList()
    {
        mainWindow.requestListDisplayUpdate();
    }

    public static void createNewEntry()
    {
        editWindow.triggerNewEntry();
    }
    
    public static void addNewEntry(Data d)
    {
        controller.addEntry(d);
    }
    
    public static void quitApplication()
    {
        mainWindow.quit();
        editWindow.quit();
    }
}
