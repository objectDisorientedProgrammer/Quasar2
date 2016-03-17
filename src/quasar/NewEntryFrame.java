package quasar;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewEntryFrame extends JFrame {
	
	private final int frameWidth = 350;
	private final int frameHeight = 300;
	private Container selectorPane;
	private JPanel documentPane;
	private NodeManager manager;
	private String[] possibleEntries;
	
	public NewEntryFrame(NodeManager mngr, String[] entryTypes)
	{
		this.manager = mngr;
		this.possibleEntries = entryTypes;
		setUpFrame();
		createPanes();
		
		
		createGUI();
		
		
		addPanes();
		
		this.setVisible(true);
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
		JComboBox<String> dataTypeSelector = new JComboBox<String>(possibleEntries);
		dataTypeSelector.setSelectedIndex(0);
		
		JLabel dataTitle = new JLabel("Title:");
		JTextField title = new JTextField();
		title.setToolTipText("Entry title");
		
		JLabel dataDesciption = new JLabel("Description:");
		JTextField description = new JTextField();
		
		JLabel dataDate = new JLabel("Date:");
		JTextField date = new JTextField();
		
		JLabel dataKeywords = new JLabel("Keywords:");
		JTextField keywords = new JTextField();
		keywords.setToolTipText("Any keywords or tags to identify this entry");
		
		selectorPane.add(dataTypeSelector);
		selectorPane.add(Box.createVerticalGlue());
		selectorPane.add(dataTitle);
		selectorPane.add(Box.createHorizontalStrut(10));
		selectorPane.add(title);
		selectorPane.add(Box.createVerticalGlue());
		selectorPane.add(dataDesciption);
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
	}

	private void setUpFrame() {
		this.setSize(frameWidth, frameHeight);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
}
