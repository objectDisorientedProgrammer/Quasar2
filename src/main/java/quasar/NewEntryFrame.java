/*
 * 
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

import java.awt.Component;
import java.awt.Container;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class NewEntryFrame extends JFrame {
	
	private boolean EnableDebug = true; // toggle this to print debugging console output
	
	private final int frameWidth = 350;
	private final int frameHeight = 300;
	private Container descriptionsPane; // holds the labels
	private Container inputsPane; // holds the textfields
	private JPanel documentPane;
	private NodeManager manager;
	private String[] possibleEntries;
	private MainWindow mwReference;
	
	private JComboBox<String> dataTypeSelector;
	private JTextField title;
	private JTextField description;
	private JFormattedTextField date;
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
		
		
		addPanesToFrame();
		
		//this.setVisible(true);
	}

	private void addPanesToFrame()
	{
		// set the layout for this frame's underlying container
		this.getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.LINE_AXIS));
		// add sub-containers
		this.getContentPane().add(descriptionsPane);
		//this.getContentPane().add(Box.createHorizontalGlue());
		this.getContentPane().add(inputsPane);
		//this.getContentPane().add(Box.createHorizontalGlue());
		
//		this.getContentPane().add(documentPane, BorderLayout.PAGE_END);
	}

	private void createPanes()
	{
		// create container object and set box layout to have vertical layout
		descriptionsPane = new Container();
		descriptionsPane.setLayout(new BoxLayout(descriptionsPane, BoxLayout.PAGE_AXIS));
		// create container object and set box layout to have vertical layout
		inputsPane = new Container();
		inputsPane.setLayout(new BoxLayout(inputsPane, BoxLayout.PAGE_AXIS));
		
		
//		documentPane = new JPanel(new BoxLayout(documentPane, BoxLayout.PAGE_AXIS));
	}
	
	// FIXME this method exists here and in EditWindow...need to consolidate these...
	// stolen from https://docs.oracle.com/javase/tutorial/uiswing/components/formattedtextfield.html
	protected MaskFormatter createFormatter(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	    }
	    return formatter;
	}
	
	private void addLabel(Container c, String title, String tooltip)
	{
		JLabel label = new JLabel(title);
		label.setToolTipText(tooltip);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		c.add(label);
		c.add(Box.createVerticalStrut(30));
	}
	
	private void addTextField(Container c, JTextField tf, String tooltip)
	{
		tf = new JTextField();
		tf.setToolTipText(tooltip);
		
		c.add(tf);
	}

	private void createGUI()
	{
		addLabel(descriptionsPane, "Category", "What type of entry is this?");
//		JLabel categoryTitle = new JLabel("Category");
//		//categoryTitle.setLabelFor(dataTypeSelector);
//		categoryTitle.setToolTipText("What type of entry is this?");
//		categoryTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		DefaultListCellRenderer dlcr = new DefaultListCellRenderer(); 
		dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		dataTypeSelector = new JComboBox<String>(possibleEntries);
		dataTypeSelector.setSelectedIndex(0);
		dataTypeSelector.setRenderer(dlcr);
		inputsPane.add(dataTypeSelector);
		
		addLabel(descriptionsPane, "Title", "Memorable and descriptive title.");
//		JLabel dataTitle = new JLabel("Title");
		addTextField(inputsPane, title, "Entry title");
//		title = new JTextField();
//		title.setToolTipText("Entry title");
//		inputsPane.add(title);
		
		addLabel(descriptionsPane, "Description", "");
//		JLabel dataDescription = new JLabel("Description");
		addTextField(inputsPane, description, "");
//		description = new JTextField();
		
		
		addLabel(descriptionsPane, "Date", "yyyy-mm-dd");
//		JLabel dataDate = new JLabel("Date (YYYY-MM-DD)");
		date = new JFormattedTextField(createFormatter("####-##-##"));
		date.setToolTipText("Format: YYYY-MM-DD");
		inputsPane.add(date);
		
		addLabel(descriptionsPane, "Keywords", "");
//		JLabel dataKeywords = new JLabel("Keywords");
		addTextField(inputsPane, keywords, "Keywords and tags that help identify this entry. Separate with spaces.");
//		keywords = new JTextField();
//		keywords.setToolTipText("Keywords and tags that help identify this entry. Separate with spaces.");
		
		addButton = new JButton("Add Entry");
		addButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// Object created for debugging. TODO this could be done inline if desired.
				Data d = new Data(title.getText(), description.getText(), date.getText(), keywords.getText(), possibleEntries[dataTypeSelector.getSelectedIndex()]);
				manager.createEntry(d);
				
				if(EnableDebug)
					System.out.println("in NewEntryFrame.addButton - adding: " + d.toString());
				
				mwReference.requestListDisplayUpdate();
				
				clearAllTextfields();
				hideFrame();
			}
		});
		
//		descriptionsPane.add(categoryTitle);
//		
//		descriptionsPane.add(Box.createVerticalGlue());
//		descriptionsPane.add(dataTitle);
//		//descriptionsPane.add(Box.createHorizontalStrut(10));
//		inputsPane.add(title);
//		descriptionsPane.add(Box.createVerticalGlue());
//		descriptionsPane.add(dataDescription);
//		//descriptionsPane.add(Box.createHorizontalStrut(10));
//		inputsPane.add(description);
//		descriptionsPane.add(Box.createVerticalGlue());
//		descriptionsPane.add(dataDate);
//		//descriptionsPane.add(Box.createHorizontalStrut(10));
//		inputsPane.add(date);
//		descriptionsPane.add(Box.createVerticalGlue());
//		descriptionsPane.add(dataKeywords);
//		//descriptionsPane.add(Box.createHorizontalStrut(10));
//		inputsPane.add(keywords);
//		descriptionsPane.add(Box.createVerticalGlue());
		
		descriptionsPane.add(addButton);
		inputsPane.add(new JButton("Clear"));
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
