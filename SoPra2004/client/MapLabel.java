/*
 * Created on 18.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JLabel;

import server.Layer;
import server.Tile;

/**
 * @author DrRSatzteil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
class MapLabel extends JLabel{
	private ArrayList layerList;

	MapLabel(ArrayList layerList){
		this.layerList = layerList;
	}
	public void paint(Graphics g){
		if (!layerList.isEmpty()){
			ListIterator i = layerList.listIterator(0);
			
			while(i.hasNext()){
				ImageObserver imageObserver = null;
				
				Layer temp = (Layer)i.next();
				int columns = temp.getColumns();
				ArrayList tiles = temp.getTiles(); 
				ListIterator j = tiles.listIterator(0);
				
				while (j.hasNext()){
					int startX = 0;
					int startY = 0;
					if (columns > 0){
						Tile currentTile = (Tile)j.next();
						if(currentTile.hasImage()){
							g.drawImage(currentTile.getImage(), startX, startY, imageObserver);
							startX = startX + 500;
							columns--;
						}
						else{
							startX = startX + 500;
							columns--;
						}						
					}
					else{
						columns = temp.getColumns();
						startY++;
						startX = 0;
					}
				}
			}
		}
	}
}
