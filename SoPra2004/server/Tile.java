/*
 * Created on 18.12.2004
 *
 */
package server;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 * @author fkubis
 * $Id: Tile.java,v 1.4 2004/12/21 13:47:59 drrsatzteil Exp $
 */
public class Tile {
	private int id;
	private Point from;
	private Point to;
	private Dimension dim;
	private byte[] data;
	private ImageIcon image;
	
	public Tile(){
	}
	
	public Tile(int id, int XFrom, int XTo, int YFrom, int YTo, byte[] gif) {		
		this.id	= id;
		from 	= new Point(XFrom, YFrom);
		to 		= new Point(XTo, YTo);
		dim = new Dimension(XFrom + XTo, YFrom + YTo);
		data 	= gif;
		
		try {
			createImage();
		} catch (NoImageException e) {
			System.err.println("createImage der Tile: " + e.getMessage());
		}
	}
	
	private void createImage() throws NoImageException {		
		if (data != null) {
			image = new ImageIcon(data);
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
		return image.getImage();
	}
	
	/**
	 * @param icon
	 */
	public void setImage(ImageIcon icon) {
		image = icon;
	}
	public Dimension getSize(){
		return dim;
	}

}
