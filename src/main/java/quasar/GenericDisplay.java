/**
   Created: August 23, 2020

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

import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author doug
 *
 */
public class GenericDisplay extends JPanel
{
    private final int columns = 2;
    private Font defaultLabelFont = Quasar.defaultLabelFont;
    private HashMap<JLabel, JTextField> rows;
    
    public GenericDisplay(HashMap<String, String> data)
    {
        this.setLayout(new GridLayout(data.size(), columns, 2, 2));
        
        rows = new HashMap<JLabel, JTextField>(data.size());
        
        JLabel label;
        JTextField tf;
        
        // create a {label : textfield} pair for each {key : value} in data
        for(Map.Entry<String, String> e : data.entrySet())
        {
            label = new JLabel(e.getKey());
            label.setFont(defaultLabelFont);
            tf = new JTextField(e.getValue());
            rows.put(label, tf);
            this.add(label);
            this.add(tf);
        }
    }
    
    public void setEditable(boolean editable)
    {
        for(JTextField tf : rows.values())
            tf.setEditable(editable);
    }

    /**
     * Populate data in the text fields.
     * @param data
     */
    public void writeTo(HashMap<String, String> data)
    {
        for(Map.Entry<JLabel, JTextField> e : rows.entrySet())
        {
            // set the textfield value based on the new data's value which is looked up using the label's string
            // textfield.setText( data[label.getText()] )
            e.getValue().setText(data.get(e.getKey().getText()));
        }
    }
    
    /**
     * Read data from the text fields.
     * @param data
     */
    public void readFrom(HashMap<String, String> data)
    {
        for(Map.Entry<JLabel, JTextField> e : rows.entrySet())
        {
            // set the value based on the textfield's value which is looked up using the label's string
            // data[label.getText()] = textfield.getText()
            data.put(e.getKey().getText(), e.getValue().getText());
        }
    }
}
