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
import java.util.ArrayList;
import java.util.ListIterator;

import data.DBValues;

/**
 * @author fkubis
 * $Id: Layer.java,v 1.24 2005/01/13 20:11:37 drrsatzteil Exp $
 */
public class Layer{
	private BufferedImage map;
		
	Layer(Dimension d, Point p, int zoom, int layerId, DBValues DB) {
		map = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		ArrayList tiles;
		tiles = DB.getTiles(p, d, layerId);
		ListIterator i = tiles.listIterator(0);
		//for (int i = 0; i < tiles.length; i++){
		while(i.hasNext()){
			Tile temp = (Tile)i.next();
			if (temp.hasImage()){
				/*if(!(zoom == 100)){
					int resizedX = (int)(tiles[i].getDim().width / (100/zoom));
					int resizedY = (int)(tiles[i].getDim().height / (zoom / 50));
					tiles[i].setImage(tiles[i].getImage().getScaledInstance(resizedX, resizedY, Image.SCALE_DEFAULT));
					
				}*/
				int xPaint = temp.getXFrom() - p.x;
				int yPaint = temp.getYFrom() - p.y;
				if(xPaint < 0 || yPaint < 0){
					int xToCut = 0;
					int yToCut = 0;
					if(xPaint < 0){
						xToCut = -xPaint;
					}
					if(yPaint < 0){
						yToCut = -yPaint;
					}
					int width = temp.getSize().width;
					int height = temp.getSize().height;
					BufferedImage cutTile = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
					if(xToCut > 0 & yToCut > 0){
						cutTile.getGraphics().drawImage(temp.getImage(),0,0,null);
					}
					if(xToCut > 0 & yToCut == 0){
						cutTile.getGraphics().drawImage(temp.getImage(),0,yPaint,null);
					}
					if(xToCut == 0 & yToCut > 0){
						cutTile.getGraphics().drawImage(temp.getImage(),xPaint,0,null);
					}
					int newWidth = width - xToCut;
					int newHeight = height - yToCut;
					temp.setImage(cutTile.getSubimage(xToCut, yToCut, newWidth, newHeight));
					temp.setDim(new Dimension(newWidth, newHeight));
					temp.setXFrom(temp.getXFrom() - xToCut);
					temp.setYFrom(temp.getYFrom() - yToCut);
					map.getGraphics().drawImage(temp.getImage(),0,0,null);
				}
				else{
					map.getGraphics().drawImage(temp.getImage(),xPaint,yPaint,null);
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
	    cA[255]= (byte)color.getAlpha();
	    ByteLookupTable blut = new ByteLookupTable(0, new byte [][] {cR, cG, cB, cA}); 
	    LookupOp lop = new LookupOp (blut, null);
	    lop.filter(map, map);
	}
}
