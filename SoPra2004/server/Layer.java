/*
 * Created on 18.12.2004
 *
 */
package server;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

import data.DBValues;

/**
 * @author fkubis
 * $Id: Layer.java,v 1.8 2004/12/21 23:23:13 drrsatzteil Exp $
 */
public class Layer {
	private int lines;
	private int columns;
	private int layerId;		
	private ArrayList tiles;
	
	private Dimension dim;
	private Point startP;
	private DBValues DB;
		
	Layer(Dimension d, Point p, int layerId, DBValues DB) {
		dim 			= d;
		startP 			= p;
		this.layerId 	= layerId;
		this.DB 		= DB;
		this.tiles		= new ArrayList();
		
		int x = 0;
		int y = 0;
		while (y < dim.height){
			lines++;
			while (x < dim.width){
				columns++;
				Point currentPoint = new Point (startP.x + x, startP.y + y);
				Tile temp = DB.getTile(currentPoint, layerId);
				tiles.add(temp);
				x += temp.getSize().width;
			}
			x = 0;
			y += 500;
		}
	}
	
	/**
	 * @return
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * @return
	 */
	public int getLines() {
		return lines;
	}

	/**
	 * @return
	 */
	public int getLayerId() {
		return layerId;
	}

	/**
	 * @return
	 */
	public ArrayList getTiles() {
		return tiles;
	}

}
