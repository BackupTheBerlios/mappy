/*
 * Created on 18.12.2004
 *
 */
package server;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * @author fkubis
 * $Id: Tile.java,v 1.10 2005/01/14 17:18:26 drrsatzteil Exp $
 */
public class Tile {
	private Dimension dim;
	private Image image = null;
	private float xFrom;
	private float xTo;
	private float yFrom;
	private float yTo;
	
	public Tile(){
	}
	
	public Tile(int id, int xFrom, int xTo, int yFrom, int yTo, byte[] gif) {		
		dim = new Dimension(xTo - xFrom, yTo - yFrom);
		this.xFrom = xFrom;
		this.xTo = xTo;
		this.yFrom = yFrom;
		this.yTo = yTo;
		
		try {
			createImage(gif);
		} catch (NoImageException e) {
			System.err.println("createImage der Tile: " + e.getMessage());
		}
	}
	
	private void createImage(byte[] gif) throws NoImageException{		
		if (gif != null) {
			image = new ImageIcon(gif).getImage();
		} else {
			throw new NoImageException("Stream aus der DB ist null");
		}
	}
	
	public boolean hasImage() {
		if(image != null){
			return true;
		}
		else{
			return false;
		}
	}
	
	public Image getImage() {
		return image;
	}
	public Dimension getSize(){
		return dim;
	}
	/**
	 * @return Returns the xFrom.
	 */
	public float getXFrom() {
		return xFrom;
	}
	/**
	 * @return Returns the xTo.
	 */
	public float getXTo() {
		return xTo;
	}
	/**
	 * @return Returns the yFrom.
	 */
	public float getYFrom() {
		return yFrom;
	}
	/**
	 * @return Returns the yTo.
	 */
	public float getYTo() {
		return yTo;
	}
	/**
	 * @param image The image to set.
	 */
	public void setImage(Image image) {
		ImageIcon temp = new ImageIcon(image);
		this.image = temp.getImage();
	}
	public void setSize(Dimension d){
		dim = d;
	}
	/**
	 * @param from The xFrom to set.
	 */
	public void setXFrom(float from) {
		xFrom = from;
		setXTo(xFrom + getSize().width);
	}
	/**
	 * @param from The yFrom to set.
	 */
	public void setYFrom(float from) {
		yFrom = from;
		setYTo(yFrom + getSize().height);
	}
	private void setXTo(float to) {
		yTo = to;
	}
	/**
	 * @param to The yTo to set.
	 */
	private void setYTo(float to) {
		yTo = to;
	}
}
