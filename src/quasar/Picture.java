/*
 * Created: May 28, 2013
 */

package quasar;

public class Picture extends Data
{
	private String path;
	private String photographer;
	private int width;
	private int height;
	
	/**
	 * Width and height set to -1 to indicate absence.
	 * @param path - location of file
	 */
	public Picture(String path)
	{
		this(path, "", -1, -1);
	}
	
	/**
	 * Width and height set to -1 to indicate absence.
	 * @param path - location of file
	 * @param photographer
	 */
	public Picture(String path, String photographer)
	{
		this(path, photographer, -1, -1);
	}
	
	/**
	 * 
	 * @param path - location of file
	 * @param w - image width
	 * @param h - image height
	 */
	public Picture(String path, int w, int h)
	{
		this(path, "", w, h);
	}
	
	/**
	 * 
	 * @param path - location of file
	 * @param photographer
	 * @param w - image width
	 * @param h - image height
	 */
	public Picture(String path, String photographer, int w, int h)
	{
		super();
		this.path = path;
		this.photographer = photographer;
		this.width = w;
		this.height = h;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPublisher() {
		return photographer;
	}

	public void setPublisher(String publisher) {
		this.photographer = publisher;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int w) {
		if(w >= 0)
			this.width = w;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int h) {
		if(h >= 0)
			this.height = h;
	}
}