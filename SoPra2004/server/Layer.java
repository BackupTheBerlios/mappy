/*
 * Created on 18.12.2004
 *
 */
package server;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import data.DBValues;

/**
 * @author fkubis
 * $Id: Layer.java,v 1.11 2005/01/12 20:25:36 drrsatzteil Exp $
 */
public class Layer {
	private BufferedImage map;
	private Dimension d;
		
	Layer(Dimension d, Point p, int layerId, DBValues DB) {
		
		map = new BufferedImage(d.width, d.height, BufferedImage.TRANSLUCENT);
		this.d = d;
		int tileWidth = 0;
		int tileHeight = 0;
		int x = -500;
		int y = -500;
		final Point startPoint = p;
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
				System.out.println(currentPoint);
			}
			x = 0;
			y += tileHeight;
			currentPoint.setLocation(5500 + x, 5500 + y);
			//System.out.println(currentPoint);
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

}
