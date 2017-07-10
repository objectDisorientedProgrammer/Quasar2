/*
 * Created: May 28, 2013
 * 
 * Copyright (c) 2013 Gamma (Douglas Chidester, James Howard, Steve Corbette)
 */

package quasar;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
	private JTextField dateTextField;
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
	
	private Data localDataCopy;
	
	
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
		pane.add(dateTextField);
		
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
		propertiesLabel = new JLabel("Properties");
		propertiesLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		propertiesLabel.setBounds(10, 11, 97, 18);
		
		titleLabel = new JLabel("Title:");
		titleLabel.setLabelFor(titleTextField);
		titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		titleLabel.setBounds(10, 42, labelWidth, 14);

		titleTextField = new JTextField();
		titleTextField.setToolTipText("Title");
		titleTextField.setBounds(textfieldXcoord, 40, textfieldWidth, 20);
		titleTextField.setColumns(10);

		descriptionLabel = new JLabel("Description:");
		descriptionLabel.setLabelFor(descriptionTextField);
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		descriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		descriptionLabel.setBounds(10, 72, labelWidth, 14);

		descriptionTextField = new JTextField();
		descriptionTextField.setToolTipText("Description");
		descriptionTextField.setBounds(textfieldXcoord, 70, textfieldWidth, 20);
		descriptionTextField.setColumns(10);
		
		dateLabel = new JLabel("Date:");
		dateLabel.setLabelFor(dateTextField);
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dateLabel.setBounds(10, 102, labelWidth, 14);

		dateTextField = new JTextField();
		dateTextField.setToolTipText("Date");
		dateTextField.setBounds(textfieldXcoord, 100, textfieldWidth, 20);
		dateTextField.setColumns(10);

		keywordsLabel = new JLabel("Keywords:");
		keywordsLabel.setLabelFor(keywordsTextField);
		keywordsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		keywordsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		keywordsLabel.setBounds(10, 132, labelWidth, 14);

		keywordsTextField = new JTextField();
		keywordsTextField.setToolTipText("Keywords");
		keywordsTextField.setBounds(textfieldXcoord, 130, textfieldWidth, 20);
		keywordsTextField.setColumns(10);
		
		cancelButton = new JButton("Cancel");
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
	
	/**
	 * Enable or disable editing a node.
	 * @param enable - set true to enable editing, set false to disable editing
	 */
	private void setEditingEntry(boolean enable)
	{
		titleTextField.setEditable(enable);
		descriptionTextField.setEditable(enable);
		dateTextField.setEditable(enable);
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
		titleTextField.setText(newValue);
		frame.setTitle(newValue); // update window title
		this.localDataCopy.title = newValue;
		
		newValue = descriptionTextField.getText();
		descriptionTextField.setText(newValue);
		this.localDataCopy.description = newValue;
		
		newValue = dateTextField.getText();
		//if(verifyDate(newValue))
		dateTextField.setText(newValue);
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
		dateTextField.setText(this.localDataCopy.getDate());
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