/*
 * Created: May 28, 2013
 * 
 * Copyright (c) 2013 Gamma (Douglas Chidester, James Howard, Steve Corbette)
 */

package quasar;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.ListModel;

import org.apache.commons.io.FileUtils;

public class NodeManager
{
	private Node head;
	private Node current;
	private Node tail;
	private int documentCount;
	private int websiteCount;
	private int pictureCount;
	private int contactCount;
	private int totalCount;
	private String dataFile;
	private HashMap<Integer, Data> dataContainer;

	/**
	 * Creates a NodeManager with the default load/save file of Quasar.dat.
	 */
	public NodeManager()
	{
		super();
		this.dataContainer = new HashMap<Integer, Data>();
		initializeVariables();
		// TODO
		//this("Quasar.dat");
	}

	/**
	 * Creates a NodeManager with a specified load/save file.
	 * @param dataFile - filename for the loading/saving
	 */
	public NodeManager(String dataFile) {
		super();
		this.dataContainer = new HashMap<Integer, Data>();
		this.dataFile = dataFile;
		initializeVariables();
		try {
			loadFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reset counts and set up head/tail nodes.
	 */
	private void initializeVariables() {
		this.documentCount = 0;
		this.websiteCount = 0;
		this.pictureCount = 0;
		this.contactCount = 0;
		this.totalCount = 0;
		head = null;
		tail = null;
		
		//head = new Node(new Data("Head Node"));	// create head with title
		//tail = new Node(new Data("Tail Node"));	// create tail with title
	}

	/**
	 * 
	 * @param String
	 * @throws IOException
	 */
	private void loadFile() throws IOException {
		String file = FileUtils.readFileToString(new File(dataFile));
		String[] fileLines = file.split("\n");
	}

	/**
	 * 
	 * @param String
	 */
	public void saveToFile() {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param Node
	 */
	public void addNode(Data rawData) {
		throw new UnsupportedOperationException();
	}
	
	public void createEntry(Data rawData)
	{
		dataContainer.put(rawData.hashCode(), rawData);
	}

	public void goFirst() {
		throw new UnsupportedOperationException();
	}

	public void goNext() {
		throw new UnsupportedOperationException();
	}

	public void goPrev() {
		throw new UnsupportedOperationException();
	}

	public void goLast() {
		throw new UnsupportedOperationException();
	}

	public boolean outOfBounds() {
		throw new UnsupportedOperationException();
	}

	public boolean isEmpty() {
		return dataContainer.isEmpty();
	}

	public String getAllData() {
		return dataContainer.toString();
	}

}