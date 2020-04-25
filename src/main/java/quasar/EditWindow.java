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

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class EditWindow
{
	MainWindow mwReference;
	JFrame frame;
	private String windowTitle = "Edit";
	private int frameWidth = 430;
	private int frameHeight = 250;
	private JTextField titleTextField;
	private JLabel propertiesLabel;
	private JLabel titleLabel;
	private JLabel descriptionLabel;
	private JTextField descriptionTextField;
	private JLabel dateLabel;
	private JFormattedTextField dateDisplay;
	private JLabel keywordsLabel;
	private JTextField keywordsTextField;
	private int textfieldXcoord = 95;  // (X, y, width, height)
	private int labelWidth = 80;       // (X, y, WIDTH, height)
	private int textfieldWidth = frameWidth - (textfieldXcoord + 18); // text fields extend to end of frame on creation
	
	private JButton saveButton;
	
	private JButton editButton;
	private boolean editable = false;
	
	private JButton cancelButton;
	private int buttonWidth = 90;
	
	private Data localDataCopy = null;
	
	
	public EditWindow(MainWindow main)
	{
		super();
		this.mwReference = main;
		frame = new JFrame();
		frame.setTitle(windowTitle);
		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		
		createAndAddGUI();
		addGUIElements(frame.getContentPane());
		setEditingEntry(editable);
	}
	
	/**
	 * Add GUI elements to a container.
	 * @param pane - the container to add components to.
	 */
	private void addGUIElements(Container pane) {
		pane.add(propertiesLabel);
		
		pane.add(titleLabel);
		pane.add(titleTextField);
		
		pane.add(descriptionLabel);
		pane.add(descriptionTextField);
		
		pane.add(dateLabel);
		pane.add(dateDisplay);
		
		pane.add(keywordsLabel);
		pane.add(keywordsTextField);
		
		pane.add(editButton);
		pane.add(cancelButton);
		pane.add(saveButton);
	}

	/**
	 * Initialize all GUI components.
	 */
	private void createAndAddGUI()
	{
		Font defaultLabelFont = new Font("Tahoma", Font.PLAIN, 12);
		
		propertiesLabel = new JLabel("Properties");
		propertiesLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		propertiesLabel.setBounds(10, 11, 97, 18);
		
		titleLabel = new JLabel("Title:");
		titleLabel.setLabelFor(titleTextField);
		titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		titleLabel.setFont(defaultLabelFont);
		titleLabel.setBounds(10, 42, labelWidth, 14);

		titleTextField = new JTextField();
		titleTextField.setToolTipText("Title");
		titleTextField.setBounds(textfieldXcoord, 40, textfieldWidth, 20);
		titleTextField.setColumns(10);

		descriptionLabel = new JLabel("Description:");
		descriptionLabel.setLabelFor(descriptionTextField);
		descriptionLabel.setFont(defaultLabelFont);
		descriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		descriptionLabel.setBounds(10, 72, labelWidth, 14);

		descriptionTextField = new JTextField();
		descriptionTextField.setToolTipText("Description");
		descriptionTextField.setBounds(textfieldXcoord, 70, textfieldWidth, 20);
		descriptionTextField.setColumns(10);
		
		dateLabel = new JLabel("Date:");
		dateLabel.setLabelFor(dateDisplay);
		dateLabel.setFont(defaultLabelFont);
		dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dateLabel.setBounds(10, 102, labelWidth, 14);

		dateDisplay = new JFormattedTextField(createFormatter("####-##-##"));
		dateDisplay.setToolTipText("YYYY-MM-DD");
		dateDisplay.setBounds(textfieldXcoord, 100, textfieldWidth, 20);

		keywordsLabel = new JLabel("Keywords:");
		keywordsLabel.setLabelFor(keywordsTextField);
		keywordsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		keywordsLabel.setFont(defaultLabelFont);
		keywordsLabel.setBounds(10, 132, labelWidth, 14);

		keywordsTextField = new JTextField();
		keywordsTextField.setToolTipText("Keywords");
		keywordsTextField.setBounds(textfieldXcoord, 130, textfieldWidth, 20);
		keywordsTextField.setColumns(10);
		
		cancelButton = new JButton("Close");
		cancelButton.setBounds(textfieldXcoord, 160, buttonWidth, 34);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if(editable == true)
				{
					editable = !editable; // toggle edit status
					setEditingEntry(editable); // disable editing
					editButton.setEnabled(true); // enable clicking 'edit' again
					saveButton.setEnabled(false); // disable saving
				}
				hideFrame();
			}
		});
		
		saveButton = new JButton("Save");
		saveButton.setBounds(textfieldXcoord + buttonWidth + 6, 160, buttonWidth, 34);
		saveButton.setEnabled(false); // disable by default until user wants to edit
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(editable == true)
				{
					editable = !editable; // toggle edit status
					setEditingEntry(editable); // disable editing
					editButton.setEnabled(true); // enable clicking 'edit' again
					saveButton.setEnabled(false); // disable saving
					
					updateDataValues(); // save changes
					
					frame.getContentPane().repaint(); // redraw the frame
				}
			}
		});
		
		editButton = new JButton("Edit");
		editButton.setBounds(textfieldXcoord + ((buttonWidth + 6) * 2), 160, buttonWidth, 34);
		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(editable == false)
				{
					editable = !editable; // toggle edit status
					setEditingEntry(editable); // enable editing
					editButton.setEnabled(false); // disable clicking 'edit' again
					saveButton.setEnabled(true); // allow saving
					frame.getContentPane().repaint(); // redraw the frame
				}
			}
		});
	}
	
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
	
	/**
	 * Enable or disable editing a node.
	 * @param enable - set true to enable editing, set false to disable editing
	 */
	private void setEditingEntry(boolean enable)
	{
		titleTextField.setEditable(enable);
		descriptionTextField.setEditable(enable);
		dateDisplay.setEditable(enable);
		keywordsTextField.setEditable(enable);
		
		// TODO node specific data
	}
	
	/**
	 * Save all changes made to data.
	 */
	private void updateDataValues()
	{
		// could change this to see if text has changed before trying to set new,
		// but it might be more efficient to just overwrite.
		String newValue = titleTextField.getText();
		
		// TODO check for valid 'newValue' in all cases
		if(newValue.compareTo(this.localDataCopy.title) != 0) // if title changed
		{
			titleTextField.setText(newValue);
			frame.setTitle(newValue); // update window title
			this.localDataCopy.title = newValue;
			this.mwReference.requestListDisplayUpdate(); // update the display list
		}
		
		newValue = descriptionTextField.getText();
		descriptionTextField.setText(newValue);
		this.localDataCopy.description = newValue;
		
		newValue = dateDisplay.getText();
		//if(verifyDate(newValue))
		dateDisplay.setText(newValue);
		this.localDataCopy.date = newValue;
		//else -> create error message window
		
		newValue = keywordsTextField.getText();
		keywordsTextField.setText(newValue);
		this.localDataCopy.keywords = newValue;
		
		// TODO node specific data
	}
	
	/**
	 * Display an entry's data in the edit window.
	 * @param d - the entry to display
	 */
	public void displayEntry(Data d)
	{
		this.localDataCopy = d;
		frame.setTitle(this.localDataCopy.getTitle()); // update window title
		// fill in fields
		titleTextField.setText(this.localDataCopy.getTitle());
		descriptionTextField.setText(this.localDataCopy.getDescription());
		dateDisplay.setText(this.localDataCopy.getDate());
		keywordsTextField.setText(this.localDataCopy.getKeywords());
		
		// TODO switch on type
		// display type specific info
		//if(localDataCopy instanceof Picture)
		//else if(localDataCopy instanceof Document)
		// ...
	}

	/**
	 * Show the window.
	 */
	public void showFrame() {
		frame.setVisible(true);
	}
	
	/**
	 * Hide the window.
	 */
	public void hideFrame() {
		frame.setVisible(false);
	}

	/**
	 * Delete the instance of the frame.
	 */
	public void remove() {
		frame.dispose();
	}
	
	/**
	 * Determine if the frame on the screen.
	 * @return <b>True</b> if the frame is showing on the screen, otherwise <b>false</b>.
	 */
	public boolean isAlive() {
		return frame.isShowing();
	}
}