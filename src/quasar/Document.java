/*
 * Created: May 28, 2013
 */

package quasar;

public class Document extends Data {
	private String path;
	private String pageNumber;
	private String author;

	public Document() {
		super();
	}

	public Document(String path, String pageNumber, String author) {
		super();
		this.path = path;
		this.pageNumber = pageNumber;
		this.author = author;
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

}