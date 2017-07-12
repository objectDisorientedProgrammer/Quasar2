/*
 * Created: May 28, 2013
 * 
 * Copyright (c) 2013 Gamma (Douglas Chidester, James Howard, Steve Corbette)
 */

package quasar;

public class Node {
	private Data data;
	public Node next;
	public Node prev;
	public Node nextDocument;
	public Node prevDocument;
	public Node nextWebsite;
	public Node prevWebsite;
	public Node nextPicture;
	public Node prevPicture;
	public Node nextContact;
	public Node prevContact;

	public Node() {
		this(null);
	}

	public Node(Data data) {
		super();
		this.data = data;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

}