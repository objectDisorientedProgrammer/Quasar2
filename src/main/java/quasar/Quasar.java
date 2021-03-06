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

import java.awt.Font;
//import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

public class Quasar
{
    public static final String applicationName = "Quasar-zeta";
    public static final String applicationVersion = "0.7.29";
    
    private static final String defaultFilename = "quasar.dat";
    private static final String defaultFilepath = System.getProperty("user.home") + File.separator + defaultFilename;
    public static final String sep = "\u00B6"; // ¶, but use unicode for cross-platform compatibility
    
    public static final String imagePath = "/images/"; // resource path images

    public static final int ALL = 0;
    public static final int CONTACT = 1;
    public static final int DOCUMENT = 2;
    public static final int PICTURE = 3;
    public static final int WEBSITE = 4;
    
    public static final int[] entryTypes = new int[] { ALL, CONTACT, DOCUMENT, PICTURE, WEBSITE };
    public static final String[] entryTypeStrings = new String[]{ "All", "Contact", "Document", "Picture", "Website" };
    
    public static final Font defaultLabelFont = new Font("SansSerif", Font.BOLD, 12);
    
    private static EntryController controller;
    private static MainWindow mainWindow;
    private static EditWindow editWindow = null;
    
    public static void main(String[] args)
    {
        Data entry = null;
    	
    	controller = new EntryController(defaultFilepath);
    	mainWindow = new MainWindow(entry, defaultFilepath);
    	
//        javax.swing.SwingUtilities.invokeLater(new Runnable()
//        {
//            public void run()
//            {
//            	
//            }
//        });
    	
//    	for(Font f : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts())
//    	    System.out.println(f.getFontName());
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
        editWindow = new EditWindow(d);
    }

    public static void refreshEntryList()
    {
        mainWindow.requestListDisplayUpdate();
    }

    public static void createNewEntry()
    {
        editWindow = new EditWindow(null);
    }
    
    public static void addNewEntry(Data d)
    {
        controller.addEntry(d);
    }
    
    public static void removeEntry(Data d)
    {
        controller.removeEntry(d);
    }
    
    public static void quitApplication()
    {
        mainWindow.quit();
        if(editWindow != null)
            editWindow.quit();
    }
    
    public static MainWindow getMainWindowReference()
    {
        return mainWindow;
    }
    
    /**
     * Get the number of entries based on entry type.
     * @param type One of the valid Quasar entry types or -1 for total count.
     * @return
     */
    public static int getEntryCount(int type)
    {
        int count = 0;
        
        switch(type)
        {
            case ALL: count = controller.getTypeAllCount(); break;
            case CONTACT: count = controller.getTypeContactCount(); break;
            case DOCUMENT: count = controller.getTypeDocumentCount(); break;
            case PICTURE: count = controller.getTypePictureCount(); break;
            case WEBSITE: count = controller.getTypeWebsiteCount(); break;
            default:
                // return count of total entries
                count = controller.getTotalEntryCount();
                break;
        }
        
        return count;
    }
}
