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
 * $Id: Tile.java,v 1.8 2005/01/12 22:57:18 drrsatzteil Exp $
 */
public class Tile {
	private Dimension dim;
	private Image image = null;
	
	public Tile(){
	}
	
	public Tile(int id, int XFrom, int XTo, int YFrom, int YTo, byte[] gif) {		
		dim 	= new Dimension(XTo - XFrom, YTo - YFrom);
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
}
