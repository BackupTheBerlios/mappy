/*
 * Created on 18.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package server;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JLabel;


/**
 * @author DrRSatzteil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
class MapLabel extends JLabel{
	private ArrayList layerList;

	MapLabel(){
		setDoubleBuffered(true);
	}
	
	public void refresh(ArrayList layerList){
		this.layerList = layerList;
		repaint();
	}
	public void paint(Graphics g){
		if (layerList == null){
			setText("Keine Ebenen ausgewählt");
		}
		else{
			if (!layerList.isEmpty()){
				ListIterator i = layerList.listIterator(0);
				
				while(i.hasNext()){
					ImageObserver imageObserver = null;
					
					Layer temp = (Layer)i.next();
					int columns = temp.getColumns();
					ArrayList tiles = temp.getTiles();
					ListIterator j = tiles.listIterator(0);
					int startX = 0;
					int startY = 0;
					
					while (j.hasNext()){
						Tile currentTile = (Tile)j.next();
						if (columns > 0){
							if(currentTile.hasImage()){
								g.drawImage(currentTile.getImage(), startX, startY, imageObserver);
								startX = startX + currentTile.getSize().width;
								columns--;
							}
							else{
								startX = startX + currentTile.getSize().width;
								columns--;
							}
						}
						else{
							columns = temp.getColumns();
							startY = startY + currentTile.getSize().height;
							startX = 0;
						}
					}
				}
			}
		}
	}
}
