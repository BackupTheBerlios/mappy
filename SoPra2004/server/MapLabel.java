/*
 * Created on 18.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package server;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
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
	private ArrayList pinList;
	private Point start;

	MapLabel(){
		setDoubleBuffered(true);
		setOpaque(false);
		setIgnoreRepaint(false);
	}
	
	public void refresh(ArrayList layerList, Point start){
		this.start = start;
		this.layerList = layerList;
		repaint();
	}
	public void paint(Graphics g){
		if (layerList == null){
		}
		else{
			if(!layerList.isEmpty()){
				ListIterator i = layerList.listIterator(0);
				while(i.hasNext()){
					Layer temp = (Layer)i.next();
					g.drawImage(temp.getMap(), 0, 0, null);
				}
			}
		}
		if(pinList != null){
			ListIterator i = pinList.listIterator(0);
			while(i.hasNext()){
				Pin currentPin = (Pin)i.next();
				Image img = currentPin.getImage();
				int x = currentPin.getPosition().x - start.x;
				int y = currentPin.getPosition().y - start.y;
				if(x > 0 && y > 0){
					getGraphics().drawImage(img, x, y, null);
				}
			}
		}
		//setPins(pinList);
	}
	public void setPins(ArrayList pins){
		pinList = pins;
		/*if(pinList != null){
			ListIterator i = pins.listIterator(0);
			while(i.hasNext()){
				Pin currentPin = (Pin)i.next();
				Image img = currentPin.getImage();
				int x = currentPin.getPosition().x - start.x;
				int y = currentPin.getPosition().y - start.y;
				if(x > 0 && y > 0){
					getGraphics().drawImage(img, x, y, null);
				}
			}
		}*/
		repaint();
	}
}
