package quasar;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewEntryFrame extends JFrame {
	
	private boolean EnableDebug = true; // toggle this to print debugging console output
	
	private final int frameWidth = 350;
	private final int frameHeight = 300;
	private Container selectorPane;
	private JPanel documentPane;
	private NodeManager manager;
	private String[] possibleEntries;
	private MainWindow mwReference;
	
	private JComboBox<String> dataTypeSelector;
	private JTextField title;
	private JTextField description;
	private JTextField date;
	private JTextField keywords;
	
	private JButton addButton;
	
	public NewEntryFrame(NodeManager mngr, String[] entryTypes, MainWindow mw)
	{
		this.manager = mngr;
		this.possibleEntries = entryTypes;
		this.mwReference = mw;
		setUpFrame();
		createPanes();
		
		
		createGUI();
		
		
		addPanes();
		
		//this.setVisible(true);
	}

	private void addPanes() {
		this.getContentPane().add(selectorPane);//, BorderLayout.PAGE_START);
//		this.getContentPane().add(documentPane, BorderLayout.PAGE_END);
	}

	private void createPanes() {
		selectorPane = new Container();
		selectorPane.setLayout(new BoxLayout(selectorPane, BoxLayout.PAGE_AXIS));
		
//		documentPane = new JPanel(new BoxLayout(documentPane, BoxLayout.PAGE_AXIS));
	}

	private void createGUI() {
		dataTypeSelector = new JComboBox<String>(possibleEntries);
		dataTypeSelector.setSelectedIndex(0);
		
		JLabel dataTitle = new JLabel("Title:");
		title = new JTextField();
		title.setToolTipText("Entry title");
		
		JLabel dataDescription = new JLabel("Description:");
		description = new JTextField();
		
		JLabel dataDate = new JLabel("Date:");
		date = new JTextField();
		
		JLabel dataKeywords = new JLabel("Keywords:");
		keywords = new JTextField();
		keywords.setToolTipText("Any keywords or tags to identify this entry");
		
		addButton = new JButton("Add Entry");
		addButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// for debugging TODO remove this
				Data d = new Data(title.getText(), description.getText(), date.getText(), keywords.getText(), possibleEntries[dataTypeSelector.getSelectedIndex()]);
				manager.createEntry(d);
				if(EnableDebug)
					System.out.println("in NewEntryFrame.addButton - adding: " + d.toString());
				mwReference.requestListDisplayUpdate();
				
				clearAllTextfields();
				hideFrame();
			}
		});
		
		selectorPane.add(dataTypeSelector);
		selectorPane.add(Box.createVerticalGlue());
		selectorPane.add(dataTitle);
		selectorPane.add(Box.createHorizontalStrut(10));
		selectorPane.add(title);
		selectorPane.add(Box.createVerticalGlue());
		selectorPane.add(dataDescription);
		selectorPane.add(Box.createHorizontalStrut(10));
		selectorPane.add(description);
		selectorPane.add(Box.createVerticalGlue());
		selectorPane.add(dataDate);
		selectorPane.add(Box.createHorizontalStrut(10));
		selectorPane.add(date);
		selectorPane.add(Box.createVerticalGlue());
		selectorPane.add(dataKeywords);
		selectorPane.add(Box.createHorizontalStrut(10));
		selectorPane.add(keywords);
		selectorPane.add(Box.createVerticalGlue());
		selectorPane.add(addButton);
	}
	
	private void clearAllTextfields()
	{
		title.setText("");
		description.setText("");
		date.setText("");
		keywords.setText("");
	}

	private void setUpFrame() {
		this.setSize(frameWidth, frameHeight);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
	
	private void hideFrame()
	{
		this.setVisible(false);
	}
}
