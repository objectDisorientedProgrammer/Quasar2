/*
 * Created: May 28, 2013
 */

package quasar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

import com.localarea.network.doug.WebsiteTimerGUIelement;

public class MainWindow
{
	private NodeManager nm;
	private EditWindow editWin;
	
	private static final String author = "Doug Chidester";
	private static final String version = " v0.96.6";
	private static final String windowTitle = "Quasar";
	private static final int frameWidth = 450;
	private static final int frameHeight = 400;
	
	private JFrame mainWindow;
	private JPanel mainPanel;
	
	// Variables
	boolean editWinVisible = false;
	
	// GUI
	private JTextField searchTF;
	private JLabel filterLbl;
	private JButton searchBtn;
	private JList<String> resultsList;
	private JComboBox<String> filterComboBox;
	private JButton newNodeBtn;
	private JButton editBtn;
	private JButton saveBtn;
	private JButton quitBtn;

	public MainWindow()
	{
		super();
		
		this.nm = new NodeManager();
		this.editWin = new EditWindow();
		
		initializeMainWindowAndPanel();
		
		createGUIElements();
		
		addGUIElements();

		mainWindow.setVisible(true);
	}

	private void addGUIElements()
	{
		mainPanel.add(searchTF);
		mainPanel.add(searchBtn);
		mainPanel.add(resultsList);
		mainPanel.add(filterLbl);
		mainPanel.add(filterComboBox);
		mainPanel.add(newNodeBtn);
		mainPanel.add(editBtn);
		mainPanel.add(saveBtn);
		mainPanel.add(quitBtn);
	}

	private void createGUIElements()
	{
		searchTF = new JTextField();
		searchTF.setToolTipText("Search here");
		searchTF.setBounds(10, 11, 315, 20);
		searchTF.setColumns(10);
		
		searchBtn = new JButton("Search");
		searchBtn.setBounds(341, 10, 91, 23);

		resultsList = new JList<String>();
		resultsList.setModel(new AbstractListModel<String>()
		{
			String[] values = new String[] { "test1", "test2" };

			public int getSize()
			{
				return values.length;
			}

			public String getElementAt(int index)
			{
				return values[index];
			}
		});
		resultsList.setBounds(10, 80, 315, 188);

		filterLbl = new JLabel("Search in:");
		filterLbl.setBounds(10, 42, 80, 14);

		filterComboBox = new JComboBox<String>();
		filterComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"All", "Documents", "Websites", "Pictures", "Contacts" }));
		filterComboBox.setSelectedIndex(0);
		filterComboBox.setMaximumRowCount(5);
		filterComboBox.setBounds(95, 38, 120, 22);

		newNodeBtn = new JButton("New");
		newNodeBtn.setToolTipText("Create a new entry.");
		newNodeBtn.setBounds(341, 77, 91, 23);
		// have the node manager add a node
		// nm.addNode(); // TODO

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
					Data d = new Data("test data node", "This is a test string",
							"9/27/2013", "a, test, nodes, blah", 'd');
					Data d2 = new Data("test data node 2", "This is another test string",
							"9/30/2015", "a, test, nodes, blah", 'd');
					
					if(resultsList.getSelectedIndex() == 0)
						editWin.displayNode(new Node(d));
					else
						editWin.displayNode(new Node(d2));
					editWin.showFrame();	// thread this? TODO
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

		quitBtn = new JButton("Quit");
		quitBtn.setBounds(341, 178, 89, 23);
		quitBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				// save nodes TODO
				//nm.saveToFile();
				
				if(editWinVisible)
					editWin.remove();
				mainWindow.dispose();
			}
		});
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
                // save data and close program if user clicks: File -> Quit
            	//writeToFile(filenameTextfield.getText());
                mainWindow.dispose();
            }
		});
		fileMenu.add(quitMenuItem);
		
		JMenu optionsMenu = new JMenu("Options");
		optionsMenu.setMnemonic(KeyEvent.VK_O);
		menuBar.add(optionsMenu);
		
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
                JOptionPane.showMessageDialog(null, helpMessage, "Usage",
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
				JOptionPane.showMessageDialog(null, "Created by " + author + "\nVersion " + version + source + repoWebsite, "About",
						JOptionPane.INFORMATION_MESSAGE, new ImageIcon(this.getClass().getResource(imagePath+"person.png")));
			}
		});
		helpMenu.add(aboutMenuItem);
	}
}