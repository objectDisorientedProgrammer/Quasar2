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

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Vector;

import javax.swing.*;

public class MainWindow
{
    private final String author = "Douglas Chidester";
    private final String windowTitle = Quasar.applicationName;
    private final int frameWidth = 450;
    private final int frameHeight = 400;
    
    private final String imagePath = "/images/";    // path in jar file
    
    private JFrame mainWindow;
    private JPanel mainPanel;
    private JPanel aboutPane;
    
    // Variables
    private Data entry;
    
    private String quasarLicenseText = "Quasar";
    private String quasarLicenseUrl = "https://github.com/objectDisorientedProgrammer/Quasar2/blob/master/license.txt";
    private String commonsIoLicenseText = "commons-io";
    private String commonsIoLicenseUrl = "https://www.apache.org/licenses/LICENSE-2.0.txt";
    private String LGoodDatePickerLicenseText = "LGoodDatePicker";
    private String LGoodDatePickerLicenseUrl = "https://github.com/LGoodDatePicker/LGoodDatePicker/blob/v11.1.0-Standard/LICENSE";
    private String urlErrorWindowTitle = "Error openning URL";
    
    // GUI
    private JTextField searchTF;
    private JLabel filterLbl;
    private JButton searchBtn;
    
    private JList<Data> dataList;
    private JScrollPane scrollPane;
    private int clickcount = 0;
    private Data previouslySelected;
    
    private JComboBox<String> filterComboBox;
    private JButton newBtn;
    private JButton viewBtn;
    private JButton deleteBtn;
    private String licenseMenuText = "Licenses";
    private String databaseFilePath;

    private ActionListener searchListener;

    public MainWindow(Data model, String filepath)
    {
        super();
        this.entry = model;
        databaseFilePath = filepath;
        initializeMainWindowAndPanel();
        
        createAboutPanel();
        
        createAndAddMenuBar();
        
        createGUIElements();
        
        updateListDisplay();
        
        addGUIElements();

        mainWindow.setVisible(true);
    }

    private void addGUIElements()
    {
        mainPanel.add(searchTF);
        mainPanel.add(searchBtn);
        mainPanel.add(scrollPane);
        mainPanel.add(filterLbl);
        mainPanel.add(filterComboBox);
        mainPanel.add(newBtn);
        mainPanel.add(viewBtn);
        mainPanel.add(deleteBtn);
    }

    private void createGUIElements()
    {
        searchListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // get the query text
                String searchString = searchTF.getText();
                // get filter
                int filter = filterComboBox.getSelectedIndex();
                // return results based on query text and filter
                Vector<Data> temp = new Vector<Data>(10);

                if(Quasar.search(searchString, filter, temp))
                {
                    // update the display list
                    dataList.setListData(temp);
                    dataList.setSelectedIndex(0);
                }
                else
                    // tell the user there are no matches
                    JOptionPane.showMessageDialog(null, "No results.", "Search",  JOptionPane.INFORMATION_MESSAGE, null);
            }
        };
        
        searchTF = new JTextField();
        searchTF.setToolTipText("Search here");
        searchTF.setBounds(10, 11, 315, 20);
        searchTF.setColumns(10);
        searchTF.addActionListener(searchListener);
        
        searchBtn = new JButton("Search");
        searchBtn.setBounds(341, 10, 91, 23);
        searchBtn.addActionListener(searchListener);
        
        filterLbl = new JLabel("Search in:");
        filterLbl.setBounds(10, 42, 80, 14);

        filterComboBox = new JComboBox<String>();
        filterComboBox.setModel(new DefaultComboBoxModel<String>(Quasar.entryTypeStrings));
        filterComboBox.setSelectedIndex(0);
        filterComboBox.setMaximumRowCount(Quasar.entryTypeStrings.length);
        filterComboBox.setBounds(95, 38, 120, 22);
        
        // create the data container for the UI
        dataList = new JList<Data>();
        dataList.setLayoutOrientation(JList.VERTICAL);
        // create a scrollable area to display the data
        scrollPane = new JScrollPane(dataList);
        scrollPane.setBounds(10, 80, 315, 188);
        
        dataList.addMouseListener(new MouseListener()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {}
            
            @Override
            public void mousePressed(MouseEvent e)
            {}
            
            @Override
            public void mouseExited(MouseEvent e)
            {}
            
            @Override
            public void mouseEntered(MouseEvent e)
            {}
            
            @Override
            public void mouseClicked(MouseEvent e)
            {
                ++clickcount;
                // display list item on double click
                if(clickcount >= 2 && previouslySelected == dataList.getSelectedValue())
                {
                    if(dataList.getSelectedValue() != null)
                        Quasar.displayEntry(dataList.getSelectedValue());
                    
                    // reset double click logic
                    clickcount = 0;
                    previouslySelected = null;
                }
                else // First click. Save selected list item.
                    previouslySelected = dataList.getSelectedValue();
            }
        });

        newBtn = new JButton("New");
        newBtn.setToolTipText("Create a new entry");
        newBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arrrrg) {
                Quasar.createNewEntry();
            }
        });
        newBtn.setBounds(341, 77, 91, 23);

        viewBtn = new JButton("View");
        viewBtn.setToolTipText("View or edit the selected entry");
        viewBtn.setBounds(341, 111, 91, 23);
        viewBtn.setEnabled(false);
        viewBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // Display selected entry in an edit window
                Quasar.displayEntry(dataList.getSelectedValue());
            }
        });
        
        deleteBtn = new JButton("Delete");
        deleteBtn.setBounds(341, 244, 91, 23);
        deleteBtn.setEnabled(false);
        deleteBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // remove the selected entry and refresh the UI
                Quasar.removeEntry(dataList.getSelectedValue());
                updateListDisplay();
            }
        });
    }
    
    public void requestListDisplayUpdate()
    {
        filterComboBox.setSelectedIndex(Quasar.ALL);
        updateListDisplay();
    }

    private void updateListDisplay()
    {
        // update the list data
        dataList.setListData(Quasar.getAllData());
        if(Quasar.isEmpty())
        {
            // disable buttons
            viewBtn.setEnabled(false);
            deleteBtn.setEnabled(false);
        }
        else
        {
            // enable buttons
            viewBtn.setEnabled(true);
            deleteBtn.setEnabled(true);
            // select the top item
            dataList.setSelectedIndex(0); // TODO should select next highest element when deleting...
        }
    }

    private void initializeMainWindowAndPanel()
    {
        mainWindow = new JFrame(windowTitle);
        mainWindow.setSize(frameWidth, frameHeight);
        mainWindow.setMinimumSize(new Dimension(frameWidth >> 1, frameHeight >> 1)); // enough to access the File->Quit menu
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null); // center the frame relative to the desktop
        
        mainPanel = new JPanel(null); // TODO change layout manager; also no need to create a JPanel here.. use .getContentPane()
        
        mainWindow.add(mainPanel);
    }
    
    private void createAboutPanel()
    {
        aboutPane = new JPanel();
        aboutPane.setLayout(new BoxLayout(aboutPane, BoxLayout.PAGE_AXIS));
        
        JLabel applicationInfo = new JLabel("Created by " + author);
        applicationInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel versionInfo = new JLabel("Version " + Quasar.applicationVersion);
        versionInfo.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel licensesText = new JLabel(licenseMenuText + ":");
        licensesText.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // create a hyperlink to the Quasar License (TODO the license might need to be embedded into the application)
        JLabel quasarLicense = new JLabel(quasarLicenseText);
        quasarLicense.setForeground(Color.blue.darker());
        quasarLicense.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        quasarLicense.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            
            @Override
            public void mousePressed(MouseEvent e) {}
            
            @Override
            public void mouseExited(MouseEvent e) {}
            
            @Override
            public void mouseEntered(MouseEvent e) {}
            
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(quasarLicenseUrl));
                } catch (IOException | URISyntaxException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), urlErrorWindowTitle,
                            JOptionPane.ERROR_MESSAGE, null);
                }
            }
        });
    
        // create a hyperlink to the commons-io License (TODO the license might need to be embedded into the application)
        JLabel commons_ioLicense = new JLabel(commonsIoLicenseText);
        commons_ioLicense.setForeground(Color.blue.darker());
        commons_ioLicense.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        commons_ioLicense.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            
            @Override
            public void mousePressed(MouseEvent e) {}
            
            @Override
            public void mouseExited(MouseEvent e) {}
            
            @Override
            public void mouseEntered(MouseEvent e) {}
            
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(commonsIoLicenseUrl));
                } catch (IOException | URISyntaxException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), urlErrorWindowTitle,
                            JOptionPane.ERROR_MESSAGE, null);
                }
            }
        });

        // create a hyperlink to the commons-io License (TODO the license might need to be embedded into the application)
        JLabel LGoodDatePickerLicense = new JLabel(LGoodDatePickerLicenseText);
        LGoodDatePickerLicense.setForeground(Color.blue.darker());
        LGoodDatePickerLicense.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        LGoodDatePickerLicense.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(LGoodDatePickerLicenseUrl));
                } catch (IOException | URISyntaxException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), urlErrorWindowTitle,
                            JOptionPane.ERROR_MESSAGE, null);
                }
            }
        });


        // Add all UI components
        aboutPane.add(licensesText);
        // put the license links side by side
        JPanel licenseLinks = new JPanel();
        licenseLinks.setLayout(new BoxLayout(licenseLinks, BoxLayout.PAGE_AXIS));
        licenseLinks.add(quasarLicense);
        licenseLinks.add(commons_ioLicense);
        licenseLinks.add(LGoodDatePickerLicense);
        aboutPane.add(licenseLinks);
        
        // add a space to start a new section of application info
        JLabel spacer = new JLabel("\n");
        spacer.setAlignmentX(Component.LEFT_ALIGNMENT);
        aboutPane.add(spacer);
        aboutPane.add(applicationInfo);
        aboutPane.add(versionInfo);
    }
    
    private void createAndAddMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        mainWindow.setJMenuBar(menuBar);
        
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        menuBar.add(fileMenu);
        
        JMenuItem loadMenuItem = new JMenuItem("Load...");
        loadMenuItem.setMnemonic(KeyEvent.VK_D);
        loadMenuItem.addActionListener(new ActionListener()
        {    
            @Override
            public void actionPerformed(ActionEvent e) {
                // open a directory navigation window
                JFileChooser fileWindow = new JFileChooser();
                int returnVal = fileWindow.showOpenDialog(mainPanel);

                // if the user selects a file, attempt to load it
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    try {
                        databaseFilePath = fileWindow.getSelectedFile().getAbsolutePath();
                        Quasar.loadFile(databaseFilePath);
                    } catch (IOException e1) {
                        // TODO display an error window
                        e1.printStackTrace();
                    }
                    requestListDisplayUpdate();
                }
            }
        });
        fileMenu.add(loadMenuItem);
        
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setMnemonic(KeyEvent.VK_S);
        saveMenuItem.setIcon(new ImageIcon(this.getClass().getResource(imagePath + "save.png")));
        saveMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // File -> Save
                // use the existing database
                try
                {
                    Quasar.saveToFile(databaseFilePath);
                } catch(UnsupportedOperationException ex)
                {
                    JOptionPane.showMessageDialog(null, "Save failed.", "Save error!",
                            JOptionPane.ERROR_MESSAGE, null);
                }
            }
        });
        fileMenu.add(saveMenuItem);
        
        JMenuItem saveAs = new JMenuItem("Save as...");
        saveAs.setMnemonic(KeyEvent.VK_A);
        saveAs.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // open a directory navigation window
                JFileChooser fileWindow = new JFileChooser();
                int returnVal = fileWindow.showOpenDialog(mainPanel);
                String savePath = databaseFilePath;
                // if the user selects a file, get the path
                if (returnVal == JFileChooser.APPROVE_OPTION)
                {
                    savePath = fileWindow.getSelectedFile().getAbsolutePath();
                }
                
                try
                {
                    Quasar.saveToFile(savePath);
                } catch(UnsupportedOperationException ex)
                {
                    JOptionPane.showMessageDialog(null, "Save as failed.", "Save as error!",
                            JOptionPane.ERROR_MESSAGE, null);
                }
            }
        });
        fileMenu.add(saveAs);
        
        JMenuItem quitMenuItem = new JMenuItem("Quit", new ImageIcon(this.getClass().getResource(imagePath+"exit.png")));
        quitMenuItem.setMnemonic(KeyEvent.VK_Q);
        quitMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Quasar.quitApplication();
            }
        });
        fileMenu.addSeparator();
        fileMenu.add(quitMenuItem);
        
        JMenu viewMenu = new JMenu("System");
        viewMenu.setMnemonic(KeyEvent.VK_S);
        menuBar.add(viewMenu);
        
        JMenuItem statsMenuItem = new JMenuItem("Statistics");
        statsMenuItem.setMnemonic(KeyEvent.VK_A);
        statsMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String msg = "Total entries: " + Quasar.getEntryCount(-1) + "\n"
                        + "Contacts: " + Quasar.getEntryCount(Quasar.CONTACT) + "\n"
                        + "Documents: " + Quasar.getEntryCount(Quasar.DOCUMENT) + "\n"
                        + "Pictures: " + Quasar.getEntryCount(Quasar.PICTURE) + "\n"
                        + "Websites: " + Quasar.getEntryCount(Quasar.WEBSITE) + "\n"
                        + "Other: " + Quasar.getEntryCount(Quasar.ALL);
                
                JOptionPane.showMessageDialog(null, msg, "Entry Stats", JOptionPane.PLAIN_MESSAGE, null);
            }
        });
        viewMenu.add(statsMenuItem);
        
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        menuBar.add(helpMenu);
        
        JMenuItem helpMenuItem = new JMenuItem("Getting Started", new ImageIcon(this.getClass().getResource(imagePath+"help.png")));
        helpMenuItem.setMnemonic(KeyEvent.VK_G);
        helpMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // show basic use instructions if user clicks: Help -> Getting Started
                JOptionPane.showMessageDialog(null, "Welcome to Quasar.\nQuasar enables you to categorize data in a way\n"
                        + "that makes sense to you. Enjoy this organization\nand memory aid tool.", "Usage",
                        JOptionPane.PLAIN_MESSAGE, new ImageIcon(this.getClass().getResource(imagePath+"help64.png")));
            }
        });
        helpMenu.add(helpMenuItem);
        
        JMenuItem updatesMenuItem = new JMenuItem("Check for updates");
        updatesMenuItem.setMnemonic(KeyEvent.VK_C);
        updatesMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    // Set up a REST GET query to the github API
                    final String urlCommon = "objectDisorientedProgrammer/Quasar2/";
                    final String urlBase = "https://api.github.com/repos/" + urlCommon;
                    URL tags = new URL(urlBase + "tags");
                    HttpURLConnection conn = (HttpURLConnection) tags.openConnection();
                    conn.setRequestMethod("GET");
                    
                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK)
                    {
                        // Collect the response
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuffer response = new StringBuffer();
                        String line = null;
                        while((line = in.readLine()) != null)
                        {
                            response.append(line);
                        }
                        in.close();
                        
                        // Parse the json blob to find the most recent release version
                        /* Currently need to skip over the first entry because
                           I changed tag naming convention from vX.Y.Z to X.Y.Z...
                        */
                        int ver = response.toString().indexOf("name");
                        String sub = response.substring(ver+1); // skip tag "vX.Y.Z"
                        ver = sub.indexOf("name");
                        sub = sub.substring(ver+7);
                        ver = sub.indexOf('"');
                        String latest = sub.substring(0, ver);
                        latest = latest.trim();
                        final String urlVersion = latest;
                        
                        // was thinking this contains the new .jar file, but it may not... need further testing
                        /*
                        int zipIndex = response.toString().indexOf("zipball_url");
                        String zipUrl = response.toString().substring(zipIndex);
                        System.out.println("\n" + zipUrl);
                        String temp = zipUrl.substring(zipUrl.indexOf(':') + 2, zipUrl.indexOf(',') - 2);
                        System.out.println("\n"+ temp);
                        */
                        
                        // parse each version string to compare X.Y.Z in order to determine "up to date-ness"
                        String[] currentVersion = Quasar.applicationVersion.trim().split("\\.");
                        String[] latestVersion = latest.split("\\.");

                        // if the queried latest version is larger than the current application version, prompt the user to update
                        if(Integer.parseInt(latestVersion[0]) > Integer.parseInt(currentVersion[0])
                                || Integer.parseInt(latestVersion[1]) > Integer.parseInt(currentVersion[1])
                                || Integer.parseInt(latestVersion[2]) > Integer.parseInt(currentVersion[2]))
                        {
                            // Create a fancy panel to show current and new versions along with a
                            // button to take the user to the download page
                            JPanel update = new JPanel();
                            update.setLayout(new BoxLayout(update, BoxLayout.Y_AXIS));
                            JLabel curver = new JLabel("Current version: " + Quasar.applicationVersion);
                            curver.setAlignmentX(Component.CENTER_ALIGNMENT);
                            update.add(curver);

                            JLabel newver = new JLabel("New version: " + latest);
                            newver.setAlignmentX(Component.CENTER_ALIGNMENT);
                            update.add(newver);

                            //Dimension d = new Dimension(40, 40);
                            //update.add(Box.Filler(d, d, d));
                            update.add(new JLabel(" ")); // poor man's padding

                            JButton download = new JButton(new ImageIcon(this.getClass().getResource(imagePath+"download24.png")));
                            download.setAlignmentX(Component.CENTER_ALIGNMENT);
                            download.addActionListener(new ActionListener()
                            {
                                @Override
                                public void actionPerformed(ActionEvent e)
                                {
                                    try {
                                        final String dl = "https://www.github.com/" + urlCommon + "releases/download/" + urlVersion + "/" + Quasar.applicationName + ".jar";
                                        Desktop.getDesktop().browse(new URI(dl));
                                    } catch (IOException | URISyntaxException e1) {
                                        JOptionPane.showMessageDialog(null, e1.getMessage(), urlErrorWindowTitle,
                                                JOptionPane.ERROR_MESSAGE, null);
                                    }
                                }
                            });
                            update.add(download);

                            update.add(new JLabel(" ")); // poor man's padding

                            // Display the update message window
                            Object[] options = { "Cancel" };
                            JOptionPane.showOptionDialog(null, update, "Update Available", JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                        }
                        else
                        {
                            // Program is up to date - inform the user
                            Object[] opt = { "Great" };
                            JOptionPane.showOptionDialog(null, "Version: "+ Quasar.applicationVersion, "Up to date",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opt, opt[0]);
                        }
                    }
                    else
                    {
                        System.out.println("REST GET error...");
                    }
                } catch(IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        });
        helpMenu.add(updatesMenuItem);
        
        JMenuItem aboutMenuItem = new JMenuItem("About", new ImageIcon(this.getClass().getResource(imagePath+"about.png")));
        aboutMenuItem.setMnemonic(KeyEvent.VK_B);
        aboutMenuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // show author and version if user clicks: Help -> About
                JOptionPane.showMessageDialog(null, aboutPane, "About",
                        JOptionPane.INFORMATION_MESSAGE, new ImageIcon(this.getClass().getResource(imagePath+"person.png")));
            }
        });
        helpMenu.add(aboutMenuItem);
    }

    /**
     * Save all data to a file and destroy the window.
     */
    public void quit()
    {
        // Save and quit
        Quasar.saveToFile(databaseFilePath);
        mainWindow.dispose();
    }
    
    public JFrame getComponent()
    {
        return this.mainWindow;
    }
}
