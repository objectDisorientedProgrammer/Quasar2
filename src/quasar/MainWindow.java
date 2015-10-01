/*
 * Created: May 28, 2013
 */

package quasar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

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
	private JPanel mainWindowPanel;
	
	// Variables
	boolean editWinVisible = false;
	
	// GUI
	private JTextField searchTF;
	private JLabel filterLbl;
	private JButton searchBtn;

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
		mainWindowPanel.add(searchTF);
		mainWindowPanel.add(searchBtn);
	}

	private void createGUIElements()
	{
		searchTF = new JTextField();
		searchTF.setToolTipText("Search here");
		searchTF.setBounds(10, 11, 315, 20);
		searchTF.setColumns(10);
		
		searchBtn = new JButton("Search");
		searchBtn.setBounds(341, 10, 91, 23);

		JList<String> resultsList = new JList<String>();
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
		getContentPane().add(resultsList);

		filterLbl = new JLabel("Search in:");
		filterLbl.setBounds(10, 42, 80, 14);
		getContentPane().add(filterLbl);

		JComboBox<String> filterComboBox = new JComboBox<String>();
		filterComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {
				"All", "Documents", "Websites", "Pictures", "Contacts" }));
		filterComboBox.setSelectedIndex(0);
		filterComboBox.setMaximumRowCount(5);
		filterComboBox.setBounds(95, 38, 120, 22);
		getContentPane().add(filterComboBox);

		JButton newNodeBtn = new JButton("New");
		newNodeBtn.setToolTipText("Create a new entry.");
		newNodeBtn.setBounds(341, 77, 91, 23);
		// have the node manager add a node
		// nm.addNode();
		getContentPane().add(newNodeBtn);

		JButton editBtn = new JButton("Edit");
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
					editWin.displayNode(new Node(d));
					editWin.showFrame();	// thread this?
				}
				else
				{
					editWin.remove();
					editWinVisible = false;
				}
			}
		});
		getContentPane().add(editBtn);

		JButton saveBtn = new JButton("Save");
		saveBtn.setToolTipText("Save current list.");
		saveBtn.setBounds(341, 144, 89, 23);
		// nm.saveToFile();
		getContentPane().add(saveBtn);

		JButton quitBtn = new JButton("Quit");
		quitBtn.setBounds(341, 178, 89, 23);
		quitBtn.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				// save nodes
				//nm.saveToFile();
				
				if(editWinVisible)
					editWin.remove();
				mainWindow.dispose();
			}
		});
		getContentPane().add(quitBtn);
	}

	private void initializeMainWindowAndPanel()
	{
		mainWindow = new JFrame(windowTitle);
		mainWindow.setSize(frameWidth, frameHeight);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setLocationRelativeTo(null);
		
		mainWindowPanel = new JPanel(null);
		
		mainWindow.add(mainWindowPanel);
	}
}