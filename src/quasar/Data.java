/*
 * Created: May 28, 2013
 * 
 * Copyright (c) 2013 Gamma (Douglas Chidester, James Howard, Steve Corbette)
 */

package quasar;

public class Data
{
	protected String title;
	protected String description;
	protected String date;
	protected String keywords;
	protected char type;
	
	/**
	 * Creates a data object with title set to 'Default'
	 */
	public Data()
	{
		this("", "", "", "", 'd');
	}

	/**
	 * Create a data object with a title.
	 * @param title - title for the data.
	 * @param type - c: contact, d: document, p: picture, w: website
	 */
	public Data(String title, char type)
	{
		this(title, "", "", "", type);
	}

	/**
	 * @param title - title for the data
	 * @param description - description of the data
	 * @param date
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
		this.type = type;
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
	
	public void setType(char type) {
		switch(type)
		{
		case 'c':
		case 'd':
		case 'w':
		case 'p':
			this.type = type;
			break;
		}
		
	}
}