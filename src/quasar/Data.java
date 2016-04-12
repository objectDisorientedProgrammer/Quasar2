/*
 * Created: May 28, 2013
 * 
 * Copyright (c) 2013 Gamma (Douglas Chidester, James Howard, Steve Corbette)
 */

package quasar;

import javax.swing.JOptionPane;

public class Data
{
	protected String title = null;
	protected String description = null;
	protected String date = null;
	protected String keywords = null;
	protected char type = 'u'; // c: contact, d: document, p: picture, w: website, u: unassigned
	
	/**
	 * Creates a data object with title set empty
	 */
	public Data()
	{
		this("", "", "", "", 'u');
	}

	/**
	 * Create a data object with a type.
	 * @param type - c: contact, d: document, p: picture, w: website, u: unassigned
	 */
	public Data(char type)
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
			char type) {
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
	
	public int getType() {
		return type;
	}
	
	/**
	 * Case insensitive type setter.
	 * @param type
	 */
	public void setType(char type) {
		switch(type)
		{
			case 'c':	// contact
			case 'C':
				this.type = 'c';
				break;
			case 'd':	// document
			case 'D':
				this.type = 'd';
				break;
			case 'w':	// website
			case 'W':
				this.type = 'w';
				break;
			case 'p':	// photo
			case 'P':
				this.type = 'p';
				break;
			case 'u':	// unassigned
			case 'U':
			case 'a':
			case 'A':
				this.type = 'u';
				break;
			default:
				JOptionPane.showMessageDialog(null, "Error undefined type.", "Type Error",
						JOptionPane.ERROR_MESSAGE);
		}
		
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
			sb.append(title);
		
		if(description != null && description.length() > 0)
		{
			if(sb.length() > 0)
				sb.append(" " + description);
			else
				sb.append(description);
		}
		
		if(date != null && date.length() > 0)
		{
			if(sb.length() > 0)
				sb.append(" " + date);
			else
				sb.append(date);
		}
		
		if(keywords != null && keywords.length() > 0)
		{
			if(sb.length() > 0)
				sb.append(" " + keywords);
			else
				sb.append(keywords);
		}
		
		if(sb.length() > 0)
			sb.append(" " + type);
		else
			sb.append(type);
		
		return sb.toString();
	}
}
