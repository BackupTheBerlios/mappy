/*
 * Created on 18.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
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

	MapLabel(ArrayList layerList, Point start){
		this.layerList = layerList;
	}
	public void paint(Graphics g){
		ListIterator i = layerList.listIterator(0);
		ImageObserver imageObserver = null;
		while(i.hasNext()){
			g.drawImage((Image)i.next(), (i.nextIndex()-1)*500, 0, imageObserver);
		}
	}
}
