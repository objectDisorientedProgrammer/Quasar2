/*
 * Created: May 28, 2013
 */

package quasar;

public class Data
{
	private String title;
	private String description;
	private String date;
	private String keywords;
	private int type;
	
	/**
	 * Creates a data object with title set to 'Default'
	 */
	public Data() {
		this("Default");
	}

	/**
	 * Create a data object with a title.
	 * @param title - title for the data.
	 */
	public Data(String title) {
		super();
		this.title = title;
		this.type = 0;
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
	
	public void setType(int type) {
		this.type = type;
	}

}