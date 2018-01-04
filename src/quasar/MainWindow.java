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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class MainWindow
{
	private NodeManager nm;
	private EditWindow editWindow;
	
	private final String author = "Douglas Chidester";
	private final String version = " v0.6.10";
	private final String windowTitle = "Quasar";
	private final int frameWidth = 450;
	private final int frameHeight = 400;
	
	private final String license = "The MIT License (MIT)\n\n" +
			"Copyright (c) 2013 Gamma (Douglas Chidester, James Howard, Steve Corbette)\n\n" +
			"Permission is hereby granted, free of charge, to any person obtaining a copy\n" +
			"of this software and associated documentation files (the \"Software\"), to deal\n" +
			"in the Software without restriction, including without limitation the rights\n" +
			"to use, copy, modify, merge, publish, distribute, sublicense, and/or sell\n" +
			"copies of the Software, and to permit persons to whom the Software is\n" +
			"furnished to do so, subject to the following conditions:\n\n" +
			"The above copyright notice and this permission notice shall be included in\n" +
			"all copies or substantial portions of the Software.\n\n" +
			"THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR\n" +
			"IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,\n" +
			"FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE\n" +
			"AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER\n" +
			"LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,\n" +
			"OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN\n" +
			"THE SOFTWARE.";
	
	private final String imagePath = "/images/";	// path in jar file
	
	private JFrame mainWindow;
	private JPanel mainPanel;
	
	// Variables
	private boolean editWinVisible = false;
	private final String[] dataTypeList = new String[]{ "All", "Documents", "Websites", "Pictures", "Contacts" };
	
	// GUI
	private JTextField searchTF;
	private JLabel filterLbl;
	private JButton searchBtn;
	private JList<String> dataList;
//	private JTextArea dataList;
	private JComboBox<String> filterComboBox;
	private JButton newNodeBtn;
	private JButton editBtn;
	private JButton saveBtn;

	public MainWindow()
	{
		super();
		
		this.nm = new NodeManager();
		this.editWindow = new EditWindow(this);
		
		initializeMainWindowAndPanel();
		
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
		mainPanel.add(dataList);
		mainPanel.add(filterLbl);
		mainPanel.add(filterComboBox);
		mainPanel.add(newNodeBtn);
		mainPanel.add(editBtn);
		mainPanel.add(saveBtn);
	}

	private void createGUIElements()
	{
		searchTF = new JTextField();
		searchTF.setToolTipText("Search here");
		searchTF.setBounds(10, 11, 315, 20);
		searchTF.setColumns(10);
		
		searchBtn = new JButton("Search");
		searchBtn.setBounds(341, 10, 91, 23);
		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Search not implemented yet.", "Unavailable",
						JOptionPane.INFORMATION_MESSAGE, null);
			}
		});
		
		//String[] values = new String[10];
//		dataList = new JTextArea();
		
		dataList = new JList<String>();
//		resultsList.setModel(new AbstractListModel<String>()
//		{
//			
//
//			public int getSize()
//			{
//				return values.length;
//			}
//
//			public String getElementAt(int index)
//			{
//				return values[index];
//			}
//		});
		dataList.setBounds(10, 80, 315, 188);

		filterLbl = new JLabel("Search in:");
		filterLbl.setBounds(10, 42, 80, 14);

		filterComboBox = new JComboBox<String>();
		filterComboBox.setModel(new DefaultComboBoxModel<String>(dataTypeList));
		filterComboBox.setSelectedIndex(0);
		filterComboBox.setMaximumRowCount(dataTypeList.length);
		filterComboBox.setBounds(95, 38, 120, 22);

		newNodeBtn = new JButton("New");
		newNodeBtn.setToolTipText("Create a new entry.");
		final NewEntryFrame nef = new NewEntryFrame(nm, dataTypeList, this);
		newNodeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arrrrg) {
				// have the node manager add a node
				//new Thread().start() {
				nef.setVisible(true);
					//updateListDisplay(); // TODO move this to run() so it will update regularly
				//};
			}
		});
		newNodeBtn.setBounds(341, 77, 91, 23);

		editBtn = new JButton("View");
		editBtn.setToolTipText("View and edit the selected entry.");
		editBtn.setBounds(341, 111, 91, 23);
		editBtn.setEnabled(false);
		editBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				// thread this TODO issue #9
				// Display selected entry in an edit window
				editWindow.displayEntry(nm.getEntry(dataList.getSelectedValue()));
				editWindow.showFrame();
			}
		});

		saveBtn = new JButton("Save");
		saveBtn.setToolTipText("Save current list.");
		saveBtn.setBounds(341, 144, 89, 23);
		saveBtn.setEnabled(false);
		// nm.saveToFile(); // TODO
	}
	
	public void requestListDisplayUpdate()
	{
		updateListDisplay();
	}

	private void updateListDisplay()
	{
		if(nm.isEmpty())
		{	
			dataList.setListData(new String[] {});
			editBtn.setEnabled(false);
		}
		else
		{
			dataList.setListData(nm.getAllData());
			editBtn.setEnabled(true);
		}
		
		// select the first item
		dataList.setSelectedIndex(0);
	}

	private void initializeMainWindowAndPanel()
	{
		mainWindow = new JFrame(windowTitle);
		mainWindow.setSize(frameWidth, frameHeight);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setLocationRelativeTo(null);
		
		mainPanel = new JPanel(null); // TODO change layout manager
		
		mainWindow.add(mainPanel);
	}
	
	private void createAndAddMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		mainWindow.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileMenu);
		
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setMnemonic(KeyEvent.VK_S);
		saveMenuItem.setIcon(new ImageIcon(this.getClass().getResource(imagePath + "save.png")));
		saveMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO save to file
				//writeToFile(filenameTextfield.getText()); // File -> Save
			}
		});
		fileMenu.add(saveMenuItem);
		
		JMenuItem quitMenuItem = new JMenuItem("Quit", new ImageIcon(this.getClass().getResource(imagePath+"exit.png")));
		quitMenuItem.setMnemonic(KeyEvent.VK_Q);
		quitMenuItem.addActionListener(new ActionListener()
		{
            public void actionPerformed(ActionEvent e)
            {
            	// TODO save to file then quit
                // save data and close program if user clicks: File -> Quit
            	//writeToFile(filenameTextfield.getText());
                mainWindow.dispose();
            }
		});
		fileMenu.add(quitMenuItem);
		
		/* TODO is there a need for an 'options' menu?
		JMenu optionsMenu = new JMenu("Options");
		optionsMenu.setMnemonic(KeyEvent.VK_O);
		menuBar.add(optionsMenu);
		*/
		
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
                JOptionPane.showMessageDialog(null, "Something helpful...", "Usage",
						JOptionPane.PLAIN_MESSAGE, new ImageIcon(this.getClass().getResource(imagePath+"help64.png")));
			}
		});
		helpMenu.add(helpMenuItem);
		
		JMenuItem licenseMenuItem = new JMenuItem("License"); // TODO add image?
		licenseMenuItem.setMnemonic(KeyEvent.VK_L);
		licenseMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// display the license
				JOptionPane.showMessageDialog(null, license, "License",
						JOptionPane.PLAIN_MESSAGE, null/*new ImageIcon(this.getClass().getResource(imagePath+"todo.png"))*/);
			}
		});
		helpMenu.add(licenseMenuItem);
		
		JMenuItem aboutMenuItem = new JMenuItem("About", new ImageIcon(this.getClass().getResource(imagePath+"about.png")));
		aboutMenuItem.setMnemonic(KeyEvent.VK_A);
		aboutMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// show author and version if user clicks: Help -> About
				JOptionPane.showMessageDialog(null, "Created by " + author + "\nVersion " + version, "About",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon(this.getClass().getResource(imagePath+"person.png")));
			}
		});
		helpMenu.add(aboutMenuItem);
	}

}