/*
 * Created on 18.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package server;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JPanel;


/**
 * @author DrRSatzteil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
class MapLabel extends JPanel{
	private ArrayList layerList;

	MapLabel(){
		setDoubleBuffered(true);
		setOpaque(false);
	}
	
	public void refresh(ArrayList layerList){
		this.layerList = layerList;
		repaint();
	}
	public void paint(Graphics g){
		if (layerList == null){
		}
		else{
			if (!layerList.isEmpty()){
				ListIterator i = layerList.listIterator(0);
				while(i.hasNext()){
					Layer temp = (Layer)i.next();
					g.drawImage(temp.getMap(), 0, 0, null);
				}
			}
		}
	}
}
