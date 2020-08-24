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
import java.awt.TextField;

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
    
    private JLabel[] labels;
    private JTextField[] textfields;
    
    public GenericDisplay(String[] labelText)
    {
        int rows = labelText.length;
        this.setLayout(new GridLayout(rows, columns, 2, 2));
        
        labels = new JLabel[rows];
        textfields = new JTextField[rows];
        
        for(int i = 0; i < rows; ++i)
        {
            labels[i] = new JLabel(labelText[i]);
            labels[i].setFont(defaultLabelFont);
            textfields[i] = new JTextField(10);
            
            this.add(labels[i]);
            this.add(textfields[i]);
        }
    }
    
    public void setTextByRow(int row, String text)
    {
        if(row < textfields.length)
            textfields[row].setText(text);
        else
            throw new IndexOutOfBoundsException("Row " + row + " is out of bounds!");
    }
    
    public void setTextByName(String labelName, String text)
    {
        // find label and set text
        for(int i = 0; i < labels.length; ++i)
            if(labelName == labels[i].getText())
                textfields[i].setText(text);
    }

    public String getTextByRow(int row)
    {
        if(row < textfields.length)
            return textfields[row].getText();
        else
            throw new IndexOutOfBoundsException("Row " + row + " is out of bounds!");
    }
    
    public String getTextByName(String labelName)
    {
        // find label and set text
        for(int i = 0; i < labels.length; ++i)
            if(labelName == labels[i].getText())
                return textfields[i].getText();
        
        return null;
    }

    public void setEditable(boolean editable)
    {
        for(JTextField t : textfields)
        {
            t.setEditable(editable);
        }
    }
}
