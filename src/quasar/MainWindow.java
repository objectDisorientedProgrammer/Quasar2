/*
 * Created: May 28, 2013
 */

package quasar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MainWindow extends JFrame
{
	private NodeManager nm;
	private EditWindow editWin;
	private static final String windowTitle = "Quasar";
	private int frameWidth = 450;
	private int frameHeight = 400;
	
	// Variables
	boolean editWinVisible = false;
	
	// Auto-generated
	private JTextField searchTF;
	private JLabel filterLbl;

	public MainWindow()
	{
		super();
		//nm = new NodeManager();
		this.editWin = new EditWindow();
		getContentPane().setLayout(null);

		searchTF = new JTextField();
		searchTF.setToolTipText("Search here");
		searchTF.setBounds(10, 11, 315, 20);
		getContentPane().add(searchTF);
		searchTF.setColumns(10);

		JButton searchBtn = new JButton("Search");
		searchBtn.setBounds(341, 10, 91, 23);
		getContentPane().add(searchBtn);

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
					Data d = new Data("test data node");
					d.setDate("9/27/2013");
					d.setDescription("This is a test string");
					d.setKeywords("a, test, nodes, blah");
					editWin.displayNode(new Node(d));
					editWin.setVisible(editWinVisible);	// thread this?
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
					editWin.dispose();
				dispose();
			}
		});
		getContentPane().add(quitBtn);

		setTitle(windowTitle);
		setSize(frameWidth, frameHeight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		setVisible(true);
	}
}