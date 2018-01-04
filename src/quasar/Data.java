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

import javax.swing.JOptionPane;

public class Data
{
	protected String title = null;
	protected String description = null;
	protected String date = null;
	protected String keywords = null;
	protected String type = "all"; // c: contact, d: document, p: picture, w: website, u: unassigned
	private final String[] dataTypeList = new String[]{ "All", "Documents", "Websites", "Pictures", "Contacts" };
	
	/**
	 * Creates a data object with title set empty
	 */
	public Data()
	{
		this("", "", "", "", "all");
	}

	/**
	 * Create a data object with a type.
	 * @param type - c: contact, d: document, p: picture, w: website, u: unassigned
	 */
	public Data(String type)
	{
		this("", "", "", "", type);
	}

	/**
	 * @param title - title for the data
	 * @param description - description of the data
	 * @param date - relevant date
	 * @param keywords - search keywords
	 * @param type - c: contact, d: document, p: picture, w: website
	 */
	public Data(String title, String description, String date, String keywords,
			String type) {
		super();
		this.title = title;
		this.description = description;
		this.date = date;
		this.keywords = keywords;
		setType(type);
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getKeywords() {
		return keywords;
	}
	
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	public String getType() {
		return type;
	}
	
	/**
	 * Case insensitive type setter.
	 * @param type - a valid type of data
	 */
	public void setType(String type) {
		if(!isValidType(type))
			JOptionPane.showMessageDialog(null, "Error undefined type.", "Data Type Error", JOptionPane.ERROR_MESSAGE);
		else
			this.type = type;
	}
	
	private boolean isValidType(String str) {
		for(String s : dataTypeList)
			if(str.equalsIgnoreCase(s))
				return true;
		return false;
	}

	/**
	 * Convert a Data object to a String object.
	 * @return a string with all populated fields
	 */
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		// Only add populated strings to return string
		if(title != null && title.length() > 0)
		{
			sb.append('<');
			sb.append(title);
			sb.append('>');
		}
		
		if(description != null && description.length() > 0)
		{
			
			if(sb.length() > 0)
				sb.append(" ");
			sb.append('<');
			sb.append(description);
			sb.append('>');
		}
		
		if(date != null && date.length() > 0)
		{
			if(sb.length() > 0)
				sb.append(" ");
			sb.append(date);
		}
		
		if(keywords != null && keywords.length() > 0)
		{
			if(sb.length() > 0)
				sb.append(" ");
			sb.append('<');
			sb.append(keywords);
			sb.append('>');
		}
		
		if(type != null)
		{
			if(sb.length() > 0)
				sb.append(" ");
			sb.append(type);
		}
		
		return sb.toString();
	}
}
