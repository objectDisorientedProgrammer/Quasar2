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
import java.awt.Dimension;
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
    
    private JButton editButton;
    private boolean editable = false;
    
    private Data dataReference = null;
    
    JComboBox<String> typeSelector;
    private JPanel cards;
    private DocumentView docView;
    private ContactView contView;
    private PictureView picView;
    private WebsiteView webView;
    
    public EditWindow(Data entry)
    {
        super();
        frame = new JFrame(windowTitle);
        frame.setSize(frameWidth, frameHeight);
        frame.setMinimumSize(new Dimension(frameWidth >> 1, frameHeight >> 1));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(Quasar.getMainWindowReference().getComponent());
        frame.setLayout(null);
        
        cards = new JPanel(new CardLayout());
        
        createAndAddGUI();
        addGUIElements(frame.getContentPane());
        setEditingEntry(editable);
        
        if(entry == null)
            triggerNewEntry();
        else
            displayEntry(entry);
        
        frame.setVisible(true);
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
        
        // create bottom container
        JPanel endPane = new JPanel();
        
        endPane.add(editButton);
        
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
        
        docView = new DocumentView();
        cards.add(docView.getComponent(), Quasar.entryTypeStrings[Quasar.DOCUMENT]);
        
        webView = new WebsiteView();
        cards.add(webView.getComponent(), Quasar.entryTypeStrings[Quasar.WEBSITE]);
        
        picView = new PictureView(); // TODO possibly add to Jpanel, then to card
        cards.add(picView.getComponent(), Quasar.entryTypeStrings[Quasar.PICTURE]);
        
        contView = new ContactView();
        cards.add(contView.getComponent(), Quasar.entryTypeStrings[Quasar.CONTACT]);
        
        typeSelector = new JComboBox<String>(Quasar.entryTypeStrings);
        typeSelector.setEnabled(false);
        typeSelector.addItemListener(new ItemListener() {
            
            @Override
            public void itemStateChanged(ItemEvent e) {
                CardLayout cl = (CardLayout)(cards.getLayout());
                cl.show(cards, (String)e.getItem());
            }
        });
        
        editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleEditButton(!editable);
                
                if(false == editable)
                    updateDataValues(); // save changes
            }
        });
    }

    /**
     * Create and add the "common attributes" fields
     */
    private void initializeCommonUi()
    {
        Font defaultLabelFont = Quasar.defaultLabelFont;
        
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

        // TODO replace this code during issue #52 https://github.com/objectDisorientedProgrammer/Quasar2/issues/52
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
    
    // TODO replace this code during issue #52 https://github.com/objectDisorientedProgrammer/Quasar2/issues/52
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
        
        //  node specific data
        docView.setEditable(enable);
        contView.setEditable(enable);
        picView.setEditable(enable);
        webView.setEditable(enable);
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
                    docView.setDocument((Document) this.dataReference);
                    break;
                case Quasar.WEBSITE:
                    this.dataReference = new Website();
                    webView.setWebsite((Website) this.dataReference);
                    break;
                case Quasar.PICTURE:
                    this.dataReference = new Picture();
                    picView.setPicture((Picture) this.dataReference);
                    break;
                case Quasar.CONTACT:
                    this.dataReference = new Contact();
                    contView.setContact((Contact) this.dataReference);
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
        
        if(this.dataReference instanceof Document)
        {
            docView.update();
        }
        else if(this.dataReference instanceof Contact)
        {
            contView.update();
        }
        else if(this.dataReference instanceof Picture)
        {
            picView.update();
        }
        else if(this.dataReference instanceof Website)
        {
            webView.update();
        }
    }
    
    /**
     * Display an entry's data in the edit window.
     * @param d - the entry to display
     */
    public void displayEntry(Data d)
    {
        this.dataReference = d;
        toggleEditButton(false);
        
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
                    docView.display((Document) this.dataReference);
                }
                break;
            case Quasar.WEBSITE:
                if(this.dataReference instanceof Website)
                {
                    webView.display((Website) this.dataReference);
                }
                break;
            case Quasar.PICTURE:
                if(this.dataReference instanceof Picture)
                {
                    picView.display((Picture) this.dataReference);
                }
                break;
            case Quasar.CONTACT:
                if(this.dataReference instanceof Contact)
                {
                    contView.display((Contact) this.dataReference);
                }
                break;
        }
    }

    public void triggerNewEntry()
    {
        toggleEditButton(true);
        typeSelector.setEnabled(true);
        frame.setTitle("New");
    }

    /**
     * Destroy the window.
     */
    public void quit()
    {
        if(frame.isActive())
            frame.dispose();
    }
    
    /**
     * Toggle the edit/save button.
     * @param b - {@code true} to enable editing, {@code false} to disable editing.
     */
    private void toggleEditButton(boolean b)
    {
        editable = b;
        if(editable == true)
        {
            setEditingEntry(editable);
            editButton.setText("Save");
        }
        else
        {
            setEditingEntry(editable);
            editButton.setText("Edit");
        }
    }
}
