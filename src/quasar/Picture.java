/*
 * Created: May 28, 2013
 * 
 * Copyright (c) 2013 Gamma (Douglas Chidester, James Howard, Steve Corbette)
 */

package quasar;

public class Picture extends Data
{
	private String path;
	private String photographer;
	private int imageWidth;
	private int imageHeight;
	
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
		this.imageWidth = w;
		this.imageHeight = h;
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
		return imageWidth;
	}

	public void setWidth(int w) {
		if(w >= 0)
			this.imageWidth = w;
	}

	public int getHeight() {
		return imageHeight;
	}

	public void setHeight(int h) {
		if(h >= 0)
			this.imageHeight = h;
	}
}