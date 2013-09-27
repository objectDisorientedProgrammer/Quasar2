/*
 * Created: May 28, 2013
 */

package quasar;

import java.awt.Container;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EditWindow
{
	MainWindow mwReference;
	JFrame frame;
	private String windowTitle = "Edit";
	private int frameWidth = 400;
	private int frameHeight = 200;
	private JTextField titleTextField;
	private JLabel propertiesLabel;
	private JLabel titleLabel;
	private JLabel descriptionLabel;
	private JTextField descriptionTextField;
	private JLabel dateLabel;
	private JTextField dateTextField;
	private JLabel keywordsLabel;
	private JTextField keywordsTextField;
	private int textfieldXcoord = 95;	// (X, y, width, height)
	private int labelWidth = 80;		// (X, y, WIDTH, height)
	private int textfieldWidth;			// (x, y, WIDTH, height)
	
	public EditWindow()
	{
		super();
		//this.mwReference = main;
		frame = new JFrame();
		frame.setTitle(windowTitle);
		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		
		createAndAddGUI(frame.getContentPane());
	}
	
	/**
	 * Initialize and set up all GUI components.
	 * @param pane - the container to add components to.
	 */
	private void createAndAddGUI(Container pane)
	{
		textfieldWidth = frameWidth - (textfieldXcoord + 10);
		propertiesLabel = new JLabel("Properties");
		propertiesLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		propertiesLabel.setBounds(10, 11, 97, 18);
		pane.add(propertiesLabel);

		titleLabel = new JLabel("Title:");
		titleLabel.setLabelFor(titleTextField);
		titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		titleLabel.setBounds(10, 42, labelWidth, 14);
		pane.add(titleLabel);

		titleTextField = new JTextField();
		titleTextField.setToolTipText("Title");
		titleTextField.setBounds(textfieldXcoord, 40, textfieldWidth, 20);
		titleTextField.setColumns(10);
		pane.add(titleTextField);

		descriptionLabel = new JLabel("Description:");
		descriptionLabel.setLabelFor(descriptionTextField);
		descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		descriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		descriptionLabel.setBounds(10, 72, labelWidth, 14);
		pane.add(descriptionLabel);

		descriptionTextField = new JTextField();
		descriptionTextField.setToolTipText("Description");
		descriptionTextField.setBounds(textfieldXcoord, 70, textfieldWidth, 20);
		descriptionTextField.setColumns(10);
		pane.add(descriptionTextField);

		dateLabel = new JLabel("Date:");
		dateLabel.setLabelFor(dateTextField);
		dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dateLabel.setBounds(10, 102, labelWidth, 14);
		pane.add(dateLabel);

		dateTextField = new JTextField();
		dateTextField.setToolTipText("Date");
		dateTextField.setBounds(textfieldXcoord, 100, textfieldWidth, 20);
		dateTextField.setColumns(10);
		pane.add(dateTextField);

		keywordsLabel = new JLabel("Keywords:");
		keywordsLabel.setLabelFor(keywordsTextField);
		keywordsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		keywordsLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		keywordsLabel.setBounds(10, 132, labelWidth, 14);
		pane.add(keywordsLabel);

		keywordsTextField = new JTextField();
		keywordsTextField.setToolTipText("Keywords");
		keywordsTextField.setBounds(textfieldXcoord, 130, textfieldWidth, 20);
		keywordsTextField.setColumns(10);
		pane.add(keywordsTextField);
	}

	/**
	 * Display a node's data in the edit window.
	 * @param n - the node to display
	 */
	public void displayNode(Node n)
	{
		// take a node's data and put it in the correct fields
		titleTextField.setText(n.getData().getTitle());
		descriptionTextField.setText(n.getData().getDescription());
		dateTextField.setText(n.getData().getDate());
		keywordsTextField.setText(n.getData().getKeywords());
	}

	public void showFrame() {
		frame.setVisible(true);
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