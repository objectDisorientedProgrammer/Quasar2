/*
 * Created: May 28, 2013
 * 
 * Copyright (c) 2013 Gamma (Douglas Chidester, James Howard, Steve Corbette)
 */

package quasar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class MainWindow
{
	private NodeManager nm;
	private EditWindow editWin;
	
	private final String author = "Douglas Chidester";
	private final String version = " v0.6.8";
	private final String windowTitle = "Quasar";
	private final int frameWidth = 450;
	private final int frameHeight = 400;
	
	private final String imagePath = "/images/";	// path in jar file
	
	private JFrame mainWindow;
	private JPanel mainPanel;
	
	// Variables
	boolean editWinVisible = false;
	String[] dataTypeList = new String[]{ "All", "Documents", "Websites", "Pictures", "Contacts" };
	
	// GUI
	private JTextField searchTF;
	private JLabel filterLbl;
	private JButton searchBtn;
//	private JList<String> resultsList;
	private JTextArea dataList;
	private JComboBox<String> filterComboBox;
	private JButton newNodeBtn;
	private JButton editBtn;
	private JButton saveBtn;

	public MainWindow()
	{
		super();
		
		this.nm = new NodeManager();
		this.editWin = new EditWindow();
		
		initializeMainWindowAndPanel();
		
		createAndAddMenuBar();
		
		createGUIElements();
		
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
		
		//String[] values = new String[10];
		dataList = new JTextArea();
		if(nm.isEmpty())
			dataList.setText("");
		else
			dataList.setText(nm.getAllData());
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
		newNodeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arrrrg) {
				// have the node manager add a node
				//new Thread().start() {
					new NewEntryFrame(nm, dataTypeList);
				//};
			}
		});
		newNodeBtn.setBounds(341, 77, 91, 23);

		editBtn = new JButton("Edit");
		editBtn.setToolTipText("Edit the selected entry.");
		editBtn.setBounds(341, 111, 91, 23);
		editBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				// launch the edit window
				if(!editWinVisible)
				{
					editWinVisible = true;
					// pass info based on selected item in list
					// TODO remove these test cases...
//					Data d = new Data("test data node", "This is a test string",
//							"9/27/2013", "a, test, nodes, blah", 'd');
//					Data d2 = new Data("test data node 2", "This is another test string",
//							"9/30/2015", "a, test, nodes, blah", 'd');
					
//					if(resultsList.getSelectedIndex() == 0)
//						editWin.displayNode(new Node(d));
//					else
//						editWin.displayNode(new Node(d2));
					editWin.showFrame();	// thread this TODO issue #9
				}
				else
				{
					editWin.remove();
					editWinVisible = false;
				}
			}
		});

		saveBtn = new JButton("Save");
		saveBtn.setToolTipText("Save current list.");
		saveBtn.setBounds(341, 144, 89, 23);
		// nm.saveToFile(); // TODO
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
                JOptionPane.showMessageDialog(null, "helpful message", "Usage",
						JOptionPane.PLAIN_MESSAGE, new ImageIcon(this.getClass().getResource(imagePath+"help64.png")));
			}
		});
		helpMenu.add(helpMenuItem);
		
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