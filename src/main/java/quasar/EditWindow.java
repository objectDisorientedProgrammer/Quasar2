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

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

public class EditWindow
{
    private JFrame frame;
    private String windowTitle = "Edit";
    private int frameWidth = 300;
    private int frameHeight = 450;
    
    private JLabel propertiesLabel;
    private String windowHeading = "Properties for";
    
    private JLabel titleLabel;
    private JTextField titleTextField;
    
    private JLabel descriptionLabel;
    private JTextField descriptionTextField;
    private JLabel dateLabel;
    private JFormattedTextField dateDisplay;
    private JLabel keywordsLabel;
    private JTextField keywordsTextField;
    
    private JButton saveButton;
    
    private JButton editButton;
    private boolean editable = false;
    
    private JButton cancelButton;
    
    private Data dataReference = null;
    
    JComboBox<String> typeSelector;
    private JPanel cards;
    private JTextField docPathTf;
    
    private JTextField urlTf;
    
    private JTextField picPathTf;
    
    private JTextField firstnameTf;
    private JTextField lastnameTf;
    private JTextField phoneNumberTf;
    private JTextField emailTf;
    
    
    public EditWindow(Data entry)
    {
        super();
        frame = new JFrame(windowTitle);
        frame.setSize(frameWidth, frameHeight);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        
        dataReference = entry;
        
        cards = new JPanel(new CardLayout());
        
        createAndAddGUI();
        addGUIElements(frame.getContentPane());
        setEditingEntry(editable);
    }
    
    /**
     * Add GUI elements to a container.
     * @param pane - the container to add components to.
     */
    private void addGUIElements(Container pane)
    {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
        // create container for "common attributes" and populate
        JPanel topPane = new JPanel();
        topPane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.0;
        c.gridwidth = 10;
        c.insets = new Insets(2, 2, 2, 2); // padding for all elements
        
        c.anchor = GridBagConstraints.PAGE_START;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        topPane.add(propertiesLabel, c);
        
        c.anchor = GridBagConstraints.LINE_START;
        c.gridwidth = 1;
        c.gridy = 1;
        c.ipady = 0; // this adjusts the size of the title. 0 is default.
        topPane.add(titleLabel, c);
        c.gridx = 1;
        c.weightx = 2.0;
        topPane.add(titleTextField, c);
        
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.5;
        topPane.add(descriptionLabel, c);
        c.gridx = 1;
        topPane.add(descriptionTextField, c);
        
        c.gridx = 0;
        c.gridy = 3;
        topPane.add(dateLabel, c);
        c.gridx = 1;
        topPane.add(dateDisplay, c);
        
        c.gridx = 0;
        c.gridy = 4;
        topPane.add(keywordsLabel, c);
        c.gridx = 1;
        topPane.add(keywordsTextField, c);
        
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2; // column width
        c.anchor = GridBagConstraints.CENTER;
        c.insets = new Insets(4, 80, 4, 80); // add left and right padding to center the element
        topPane.add(typeSelector, c);
        
        pane.add(topPane);
        
        // add changing UI panel (cards)
        pane.add(cards);
        
        // create container for buttons
        JPanel endPane = new JPanel();
        
        endPane.add(cancelButton);
        endPane.add(editButton);
        endPane.add(saveButton);
        
        pane.add(endPane);
    }

    /**
     * Initialize all GUI components.
     */
    private void createAndAddGUI()
    {
        initializeCommonUi();
        
        JPanel allCard = new JPanel();
        cards.add(allCard, Quasar.entryTypeStrings[Quasar.ALL]);
        // TODO add content for entries of type "all"?
        
        JPanel documentCard = new JPanel();
        docPathTf = new JTextField();
        JTextField pageNumTf = new JTextField();
        JTextField authorTf = new JTextField();
        JTextField publishDateTf = new JTextField();
        documentCard.add(docPathTf);
        documentCard.add(pageNumTf);
        documentCard.add(authorTf);
        documentCard.add(publishDateTf);
        cards.add(documentCard, Quasar.entryTypeStrings[Quasar.DOCUMENT]);
        
        JPanel websiteCard = new JPanel();
        urlTf = new JTextField();
        websiteCard.add(urlTf);
        cards.add(websiteCard, Quasar.entryTypeStrings[Quasar.WEBSITE]);
        
        JPanel pictureCard = new JPanel();
        picPathTf = new JTextField();
        JTextField photographerTf = new JTextField();
        JTextField imageWidthTf = new JTextField();
        JTextField imageHeightTf = new JTextField();
        pictureCard.add(picPathTf);
        pictureCard.add(photographerTf);
        pictureCard.add(imageWidthTf);
        pictureCard.add(imageHeightTf);
        cards.add(pictureCard, Quasar.entryTypeStrings[Quasar.PICTURE]);
        
        JPanel contactCard = new JPanel();
        firstnameTf = new JTextField();
        lastnameTf = new JTextField();
        phoneNumberTf = new JTextField();
        emailTf = new JTextField();
        contactCard.add(firstnameTf);
        contactCard.add(lastnameTf);
        contactCard.add(phoneNumberTf);
        contactCard.add(emailTf);
        cards.add(contactCard, Quasar.entryTypeStrings[Quasar.CONTACT]);
        
        typeSelector = new JComboBox<String>(Quasar.entryTypeStrings);
        typeSelector.setEnabled(false);
        typeSelector.addItemListener(new ItemListener() {
            
            @Override
            public void itemStateChanged(ItemEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, (String)e.getItem());
            }
        });
        
        cancelButton = new JButton("Close");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(editable == true)
                {
                    editable = !editable; // toggle edit status
                    setEditingEntry(editable); // disable editing
                }
                clearFields();
                hideFrame();
            }
        });
        
        // TODO consider combining the edit and save buttons as they are mutually exclusive.
        // Combining would reduce the clutter within the edit window.
        saveButton = new JButton("Save");
        saveButton.setEnabled(false); // disable by default until user wants to edit
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(editable == true)
                {
                    editable = !editable; // toggle edit status
                    setEditingEntry(editable); // disable editing
                    
                    updateDataValues(); // save changes
                    
                    frame.getContentPane().repaint(); // redraw the frame
                }
            }
        });
        // TODO consider combining the edit and save buttons as they are mutually exclusive.
        // Combining would reduce the clutter within the edit window.
        editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(editable == false)
                {
                    editable = !editable; // toggle edit status
                    setEditingEntry(editable); // enable editing
                    frame.getContentPane().repaint(); // redraw the frame
                }
            }
        });
    }

    private void clearFields()
    {
        frame.setTitle(windowTitle);
        propertiesLabel.setText(windowHeading);
        titleTextField.setText("");
        descriptionTextField.setText("");
        dateDisplay.setText("");
        keywordsTextField.setText("");
        
        
        // TODO clear entry specific data based on current type in typeSelector
        
        typeSelector.setSelectedIndex(Quasar.ALL);
    }

    /**
     * Create and add the "common attributes" fields
     */
    private void initializeCommonUi()
    {
        Font defaultLabelFont = new Font("Tahoma", Font.PLAIN, 12);
        
        propertiesLabel = new JLabel(windowHeading);
        propertiesLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        
        titleLabel = new JLabel("Title:", JLabel.TRAILING);
        titleLabel.setLabelFor(titleTextField);
        titleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        titleLabel.setFont(defaultLabelFont);

        titleTextField = new JTextField();
        titleTextField.setToolTipText("Title");
        titleTextField.setColumns(10);

        descriptionLabel = new JLabel("Description:", JLabel.TRAILING);
        descriptionLabel.setLabelFor(descriptionTextField);
        descriptionLabel.setFont(defaultLabelFont);
        descriptionLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        descriptionTextField = new JTextField();
        descriptionTextField.setToolTipText("Description");
        descriptionTextField.setColumns(10);
        
        dateLabel = new JLabel("Date:");
        dateLabel.setLabelFor(dateDisplay);
        dateLabel.setFont(defaultLabelFont);
        dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // FIXME this method exists here and in NewEntryFrame...need to consolidate these...
        dateDisplay = new JFormattedTextField(createFormatter("####-##-##"));
        dateDisplay.setToolTipText("YYYY-MM-DD");

        keywordsLabel = new JLabel("Keywords:");
        keywordsLabel.setLabelFor(keywordsTextField);
        keywordsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        keywordsLabel.setFont(defaultLabelFont);

        keywordsTextField = new JTextField();
        keywordsTextField.setToolTipText("Keywords");
        keywordsTextField.setColumns(10);
    }
    
    // FIXME this method exists here and in NewEntryFrame...need to consolidate these...
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
        editButton.setEnabled(!editable); // update edit button
        saveButton.setEnabled(editable); // update save button
        
        titleTextField.setEditable(enable);
        descriptionTextField.setEditable(enable);
        dateDisplay.setEditable(enable);
        keywordsTextField.setEditable(enable);
        
        typeSelector.setEnabled(enable); // show the correct card depending on the data type
        
        // TODO node specific data
    }
    
    /**
     * Save all changes made to data.
     */
    private void updateDataValues()
    {
        if(frame.getTitle() == "New" && this.dataReference == null)
        {
            switch(typeSelector.getSelectedIndex())
            {
                default:
                case Quasar.ALL:
                    this.dataReference = new Data();
                    break;
                case Quasar.DOCUMENT:
                    this.dataReference = new Document();
                    break;
                case Quasar.WEBSITE:
                    this.dataReference = new Website(urlTf.getText());
                    break;
                case Quasar.PICTURE:
                    this.dataReference = new Picture(picPathTf.getText());
                    break;
                case Quasar.CONTACT:
                    this.dataReference = new Contact();
                    break;
            }
            Quasar.addNewEntry(dataReference);
        }
        
        // could change this to see if text has changed before trying to set new,
        // but it might be more efficient to just overwrite.
        String newValue = titleTextField.getText();
        
        // TODO check for valid 'newValue' in all cases
        if(newValue.compareTo(this.dataReference.title) != 0) // if title changed
        {
            propertiesLabel.setText(windowHeading + " " + newValue);
            titleTextField.setText(newValue);
            frame.setTitle(newValue); // update window title
            this.dataReference.title = newValue;
            Quasar.refreshEntryList();
        }
        
        newValue = descriptionTextField.getText();
        descriptionTextField.setText(newValue);
        this.dataReference.description = newValue;
        
        newValue = dateDisplay.getText();
        //if(verifyDate(newValue))
        dateDisplay.setText(newValue);
        this.dataReference.date = newValue;
        //else -> create error message window
        
        newValue = keywordsTextField.getText();
        keywordsTextField.setText(newValue);
        this.dataReference.keywords = newValue;
        
        this.dataReference.setType(typeSelector.getSelectedIndex());
    }
    
    /**
     * Display an entry's data in the edit window.
     * @param d - the entry to display
     */
    public void displayEntry(Data d)
    {
        this.dataReference = d;
        frame.setTitle(this.dataReference.getTitle()); // update window title
        propertiesLabel.setText(windowHeading + " " + this.dataReference.getTitle()); // update header label
        // fill in fields
        titleTextField.setText(this.dataReference.getTitle());
        descriptionTextField.setText(this.dataReference.getDescription());
        dateDisplay.setText(this.dataReference.getDate());
        keywordsTextField.setText(this.dataReference.getKeywords());
        
        // display type specific info
        typeSelector.setSelectedIndex(this.dataReference.getType());
        switch(this.dataReference.getType())
        {
            default:
            case Quasar.ALL:
                // type selector already updated
                break;
            case Quasar.DOCUMENT:
                if(this.dataReference instanceof Document)
                {
                    Document doc = (Document) this.dataReference;
                    docPathTf.setText(doc.getPath());
                }
                break;
            case Quasar.WEBSITE:
                if(this.dataReference instanceof Website)
                {
                    Website web = (Website) this.dataReference;
                    urlTf.setText(web.getUrl());
                }
                break;
            case Quasar.PICTURE:
                // TODO populate UI components
                if(this.dataReference instanceof Picture)
                {
                    Picture pic = (Picture) this.dataReference;
                    picPathTf.setText(pic.getPath());
                }
                break;
            case Quasar.CONTACT:
                if(this.dataReference instanceof Contact)
                {
                    Contact con = (Contact) this.dataReference;
                    firstnameTf.setText(con.getFirstName());
                    lastnameTf.setText(con.getLastName());
                    phoneNumberTf.setText(con.getPhoneNumber());
                    emailTf.setText(con.getEmail());
                }
                break;
        }
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
     * @return {@code true} if the frame is showing on the screen, otherwise {@code false}.
     */
    public boolean isAlive() {
        return frame.isShowing();
    }

    public void triggerNewEntry()
    {
        editable = true;
        setEditingEntry(editable);
        clearFields();
        frame.setTitle("New");
        this.dataReference = null;
        showFrame();
    }
}
