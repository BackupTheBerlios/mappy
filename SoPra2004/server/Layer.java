
package server;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.ByteLookupTable;
import java.awt.image.LookupOp;
import java.util.ArrayList;
import java.util.ListIterator;

import data.DBValues;

/**
 * Class for the ...
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *
 */
public class Layer{
	private BufferedImage map;
		
	Layer(Dimension d, Point p, int zoom, int layerId, DBValues DB){
		float zoomFactor = 4 - (zoom / 100);
		Dimension realDimension = new Dimension();
		realDimension.setSize(round(d.width * zoomFactor), round(d.height * zoomFactor));
		map = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
		
		ArrayList tiles;
		tiles = DB.getTiles(p, realDimension, layerId);
		if(tiles != null){
			ListIterator i = tiles.listIterator(0);
			while(i.hasNext()){
				Tile temp = (Tile)i.next();
				if (temp.hasImage()){
					if(zoomFactor != 1){
						float resizedX = temp.getSize().width / zoomFactor;
						float resizedY = temp.getSize().height / zoomFactor;
						temp.setImage(temp.getImage().getScaledInstance(round(resizedX), round(resizedY), Image.SCALE_DEFAULT));
						temp.setSize(new Dimension (round(resizedX), round(resizedY)));
						float xFrom = temp.getXFrom();
						xFrom = xFrom - p.x;
						xFrom = xFrom / zoomFactor;
						xFrom = xFrom + p.x;
						temp.setXFrom(xFrom);
						float yFrom = temp.getYFrom();
						yFrom = yFrom - p.y;
						yFrom = yFrom / zoomFactor;
						yFrom = yFrom + p.y;
						temp.setYFrom(yFrom);
					}
					int xPaint = round(temp.getXFrom()) - p.x;
					int yPaint = round(temp.getYFrom()) - p.y;
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
						if(xToCut > 0 && yToCut > 0){
							cutTile.getGraphics().drawImage(temp.getImage(),0,0,null);
						}
						if(xToCut > 0 && yToCut == 0){
							cutTile.getGraphics().drawImage(temp.getImage(),0,yToCut,null);
						}
						if(xToCut == 0 && yToCut > 0){
							cutTile.getGraphics().drawImage(temp.getImage(),xToCut,0,null);
						}
						int newWidth = width - xToCut;
						int newHeight = height - yToCut;
						temp.setImage(cutTile.getSubimage(round(xToCut), round(yToCut), newWidth, newHeight));
						temp.setXFrom(temp.getXFrom() - xToCut);
						temp.setYFrom(temp.getYFrom() - yToCut);
						map.getGraphics().drawImage(temp.getImage(),xPaint + xToCut,yPaint + yToCut,null);
					}
					else{
						map.getGraphics().drawImage(temp.getImage(),xPaint,yPaint,null);
					}
				}
			}
			map.flush();
		}
	}
	
	private static int round(float value){
		int rounded = Math.round(value);
		return rounded;
	}

	public Dimension getDimension(){
		return new Dimension (map.getWidth(), map.getHeight());
	}
	
	public BufferedImage getMap(){
		return map;
	}

	/**
	 * @param color
	 */
	public void setColor(Color color, Color coloralpha){
		byte cR[] = new byte[256];
	    byte cG[] = new byte[256];
	    byte cB[] = new byte[256];
	    byte cA[] = new byte[256];
		
	    cR[0]= (byte)color.getRed();
	    cG[0]= (byte)color.getGreen();
	    cB[0]= (byte)color.getBlue();
	    cA[255]= (byte)coloralpha.getAlpha();
	    ByteLookupTable blut = new ByteLookupTable(0, new byte [][] {cR, cG, cB, cA}); 
	    LookupOp lop = new LookupOp (blut, null);
	    lop.filter(map, map);
	    map.flush();
	}
}
