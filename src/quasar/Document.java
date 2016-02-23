/*
 * Created: May 28, 2013
 * 
 * Copyright (c) 2013 Gamma (Douglas Chidester, James Howard, Steve Corbette)
 */

package quasar;

public class Document extends Data {
	private String path;
	private String pageNumber;
	private String author;
	private String publishDate;

	/**
	 * Constructor with no fields.
	 */
	public Document()
	{
		this("", "", "", "");
	}

	/**
	 * Constructor with only path.
	 * @param path - location of file
	 */
	public Document(String path)
	{
		this(path, "", "", "");
	}
	
	/**
	 * Constructor with path and page number.
	 * @param path - location of file
	 * @param pageNumber
	 */
	public Document(String path, String pageNumber)
	{
		this(path, pageNumber, "", "");
	}
	
	/**
	 * Constructor with path, page number, and author.
	 * @param path - location of file
	 * @param pageNumber
	 * @param author
	 */
	public Document(String path, String pageNumber, String author)
	{
		this(path, pageNumber, author, "");
	}
	
	/**
	 * Constructor with all fields.
	 * @param path - location of file
	 * @param pageNumber
	 * @param author
	 * @param publish - publish date
	 */
	public Document(String path, String pageNumber, String author, String publish)
	{
		super();
		this.path = path;
		this.pageNumber = pageNumber;
		this.author = author;
		this.publishDate = publish;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
}