/*
 * Created on 18.12.2004
 *
 */
package server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ByteLookupTable;
import java.awt.image.LookupOp;

import data.DBValues;

/**
 * @author fkubis
 * $Id: Layer.java,v 1.18 2005/01/12 22:57:00 drrsatzteil Exp $
 */
public class Layer {
	private BufferedImage map;
	private Dimension d;
		
	Layer(Dimension d, Point p, int layerId, DBValues DB) {
		
		map = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		this.d = d;
		int tileWidth = 0;
		int tileHeight = 0;
		int x = -500;
		int y = -500;
		final Point startPoint = new Point (5500,5500);
		Point currentPoint = p;
		while(d.height > y){
			while(d.width > x){
				Tile current = DB.getTile(currentPoint, layerId);
				tileWidth = current.getSize().width;
				tileHeight = current.getSize().height;
				if(current.hasImage() == true){
					map.getGraphics().drawImage(current.getImage(),x,y,null);
				}
				x += tileWidth;
				currentPoint.setLocation(5500 + x, 5500 + y);
			}
			x = 0;
			y += tileHeight;
			currentPoint.setLocation(5500 + x, 5500 + y);
		}
		map.flush();
	}

	public Dimension getDimension(){
		return d;
	}
	/**
	 * @return
	 */
	public BufferedImage getMap(){
		return map;
	}

	/**
	 * @param color
	 */
	public void setColor(Color color){
		byte cR[] = new byte[256];
	    byte cG[] = new byte[256];
	    byte cB[] = new byte[256];
	    byte cA[] = new byte[256];
							 
	    cR[0]= (byte)color.getRed();
	    cG[0]= (byte)color.getGreen();
	    cB[0]= (byte)color.getBlue();
	    cA[255]= (byte)255;
	    ByteLookupTable blut = new ByteLookupTable(0, new byte [][] {cR, cG, cB, cA}); 
	    LookupOp lop = new LookupOp (blut, null);
	    lop.filter(map, map);
	}

}
