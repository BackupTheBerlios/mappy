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
 * $Id: Tile.java,v 1.3 2004/12/18 19:26:48 fkubis Exp $
 */
public class Tile {
	private int id;
	private Point from;
	private Point to;
	private Dimension dim;
	private byte[] data;
	
	private boolean hasImage = false;
	private ImageIcon image;
	
	public Tile(int id, int XFrom, int XTo, int YFrom, int YTo, byte[] gif) {		
		this.id	= id;
		from 	= new Point(XFrom, YFrom);
		to 		= new Point(XTo, YTo);
		data 	= gif;
				
		try {
			createImage();
		} catch (NoImageException e) {
			System.err.println("createImage der Tile: " + e.getMessage());
		}
		
		System.out.println("EIN NEUES TILE WURDE GEBOREN! EIN HOCH AUF DIE KÜHE!");
	}
	
	private void createImage() throws NoImageException {
		image = new ImageIcon();
		
		if (data != null) {
			image = new ImageIcon(data);
			hasImage = true;
			    		
    		/*	
			BufferedImage bI = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);    			    			
			bI = ImageIO.read( res.getUnicodeStream("Data") );
			return bI;
			*/
		} else {
			throw new NoImageException("Stream aus der DB ist null");
		}		
	}
	
	public boolean hasImage() {
		return hasImage;
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

}
