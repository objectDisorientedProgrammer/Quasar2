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
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
	
	private Container commonAttributePane; // holds the common data entry UI elements
	private final int commonPaneRows = 8;
	private final int commonPaneCols = 2;
	
	private JPanel documentPane;
	private NodeManager manager;
	private String[] possibleEntries;
	private MainWindow mwReference;
	
	private JComboBox<String> dataTypeSelector;
	private JTextField title;
	private JTextField description;
	private JFormattedTextField date;
	private JTextField keywords;
	
	private JButton addEntryButton;
	private JButton resetButton;
	
	public NewEntryFrame(NodeManager mngr, String[] entryTypes, MainWindow mw)
	{
		this.manager = mngr;
		this.possibleEntries = entryTypes;
		this.mwReference = mw;
		setUpFrame();
		createPanes();
		createGUI();
		addPanesToFrame();
		this.pack();
	}

	private void addPanesToFrame()
	{
		// set the layout for this frame's underlying container
		this.getContentPane().setLayout(new FlowLayout());
		// add sub-containers
		this.getContentPane().add(commonAttributePane);
	}

	private void createPanes()
	{
		// create container object and set box layout to have vertical layout
		commonAttributePane = new Container();
		commonAttributePane.setLayout(new GridLayout(commonPaneRows, commonPaneCols));
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
	}
	
	/**
	 * Textfields must be created outside of this function if you want to interact with them (i.e. set/get text).
	 * @param c
	 * @param tf
	 * @param tooltip
	 */
	private void addTextField(Container c, JTextField tf, String tooltip)
	{
		tf.setToolTipText(tooltip);
		
		c.add(tf);
	}

	private void createGUI()
	{
		// Category label and input
		addLabel(commonAttributePane, "Category", "What type of entry is this?");
		DefaultListCellRenderer dlcr = new DefaultListCellRenderer(); 
		dlcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
		dataTypeSelector = new JComboBox<String>(possibleEntries);
		dataTypeSelector.setSelectedIndex(0);
		dataTypeSelector.setRenderer(dlcr);
		commonAttributePane.add(dataTypeSelector);
		
		// Title label and input
		addLabel(commonAttributePane, "Title", "Memorable and descriptive title.");
		title = new JTextField();
		addTextField(commonAttributePane, title, "Entry title");
		
		// Description label and input
		addLabel(commonAttributePane, "Description", "");
		description = new JTextField();
		addTextField(commonAttributePane, description, "");
		
		// Date label and input
		addLabel(commonAttributePane, "Date", "yyyy-mm-dd");
		date = new JFormattedTextField(createFormatter("####-##-##"));
		date.setToolTipText("Format: YYYY-MM-DD");
		commonAttributePane.add(date);
		
		// Keyword label and input
		addLabel(commonAttributePane, "Keywords", "");
		keywords = new JTextField();
		addTextField(commonAttributePane, keywords, "Keywords and tags that help identify this entry. Separate with spaces.");
		
		// add two labels for spacing
		addLabel(commonAttributePane, "", "");
		addLabel(commonAttributePane, "", "");
		
		// confirmation button
		addEntryButton = new JButton("Add Entry");
		addEntryButton.addActionListener(new ActionListener()
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
		commonAttributePane.add(addEntryButton);
		
		// reset button
		resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// reset all members back to default
				dataTypeSelector.setSelectedIndex(0);
				clearAllTextfields();
			}
		});
		commonAttributePane.add(resetButton);
	}
	
	private void clearAllTextfields()
	{
		title.setText("");
		description.setText("");
		date.setText("");
		keywords.setText("");
	}

	private void setUpFrame() {
		this.setTitle("New Entry");
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		this.setLocationRelativeTo(null); // center window on screen
	}
	
	private void hideFrame()
	{
		this.setVisible(false);
	}
}
