/*
 * Created on 18.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package server;

import java.awt.Graphics;
import java.util.ArrayList;
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
		setIgnoreRepaint(false);
	}
	
	public void refresh(ArrayList layerList){
		this.layerList = layerList;
		repaint();
	}
	public void paint(Graphics g){
		if (layerList == null){
		}
		else{
			while(!layerList.isEmpty()){
				Layer temp = (Layer)layerList.get(0);
				g.drawImage(temp.getMap(), 0, 0, null);
				layerList.remove(0);
			}
		}
	}
}
