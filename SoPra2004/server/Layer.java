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
 * $Id: Layer.java,v 1.21 2005/01/13 11:51:42 drrsatzteil Exp $
 */
public class Layer{
	private BufferedImage map;
		
	Layer(Dimension d, Point p, int layerId, DBValues DB) {
		
		map = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		Tile[] tiles;
		tiles = DB.getTiles(p, d, layerId);
		
		for (int i = 0; i < tiles.length; i++){
			if (tiles[i].hasImage()){
				int xPaint = tiles[i].getXFrom() - p.x;
				int yPaint = tiles[i].getYFrom() - p.y;
				if(xPaint < 0 || yPaint < 0){
					int xToCut = 0;
					int yToCut = 0;
					if(xPaint < 0){
						xToCut = -xPaint;
					}
					if(yPaint < 0){
						yToCut = -yPaint;
					}
					int width = tiles[i].getSize().width;
					int height = tiles[i].getSize().height;
					BufferedImage cutTile = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
					if(xToCut > 0 & yToCut > 0){
						cutTile.getGraphics().drawImage(tiles[i].getImage(),0,0,null);
					}
					if(xToCut > 0 & yToCut == 0){
						cutTile.getGraphics().drawImage(tiles[i].getImage(),0,yPaint,null);
					}
					if(xToCut == 0 & yToCut > 0){
						cutTile.getGraphics().drawImage(tiles[i].getImage(),xPaint,0,null);
					}
					int newWidth = width - xToCut;
					int newHeight = height - yToCut;
					tiles[i].setImage(cutTile.getSubimage(xToCut, yToCut, newWidth, newHeight));
					tiles[i].setDim(new Dimension(newWidth, newHeight));
					tiles[i].setXFrom(tiles[i].getXFrom() - xToCut);
					tiles[i].setYFrom(tiles[i].getYFrom() - yToCut);
					map.getGraphics().drawImage(tiles[i].getImage(),0,0,null);
				}
				else{
					map.getGraphics().drawImage(tiles[i].getImage(),xPaint,yPaint,null);
				}
			}
		}
		map.flush();
	}

	public Dimension getDimension(){
		return new Dimension (map.getWidth(), map.getHeight());
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
