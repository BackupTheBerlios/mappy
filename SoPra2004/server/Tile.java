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
 * $Id: Tile.java,v 1.9 2005/01/13 11:24:35 drrsatzteil Exp $
 */
public class Tile {
	private Dimension dim;
	private Image image = null;
	private int xFrom;
	private int xTo;
	private int yFrom;
	private int yTo;
	
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
	
	private void createImage(byte[] gif) throws NoImageException {		
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
	 * @return Returns the dim.
	 */
	public Dimension getDim() {
		return dim;
	}
	/**
	 * @return Returns the xFrom.
	 */
	public int getXFrom() {
		return xFrom;
	}
	/**
	 * @return Returns the xTo.
	 */
	public int getXTo() {
		return xTo;
	}
	/**
	 * @return Returns the yFrom.
	 */
	public int getYFrom() {
		return yFrom;
	}
	/**
	 * @return Returns the yTo.
	 */
	public int getYTo() {
		return yTo;
	}
	/**
	 * @param image The image to set.
	 */
	public void setImage(Image image) {
		this.image = image;
	}
	/**
	 * @param dim The dim to set.
	 */
	public void setDim(Dimension dim) {
		this.dim = dim;
	}
	/**
	 * @param from The xFrom to set.
	 */
	public void setXFrom(int from) {
		xFrom = from;
	}
	/**
	 * @param to The xTo to set.
	 */
	public void setXTo(int to) {
		xTo = to;
	}
	/**
	 * @param from The yFrom to set.
	 */
	public void setYFrom(int from) {
		yFrom = from;
	}
	/**
	 * @param to The yTo to set.
	 */
	public void setYTo(int to) {
		yTo = to;
	}
}
