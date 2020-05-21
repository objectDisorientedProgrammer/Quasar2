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
	
	@Override
	public String toString()
	{
		return super.toString() + "; " + this.path + "; " + this.pageNumber + "; " + this.author + "; " + this.publishDate;
	}
}