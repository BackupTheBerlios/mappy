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
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
class MapLabel extends JPanel {
	private ArrayList layerList;
	private ArrayList pinList;
	private int zoom;
	private Point start;

	MapLabel() {
		setDoubleBuffered(true);
		setOpaque(false);
		setIgnoreRepaint(false);
	}

	public void refresh(ArrayList layerList, ArrayList pins, int zoom, Point start) {
		this.zoom = zoom;
		this.start = start;
		this.pinList = pins;
		this.layerList = layerList;
		repaint();
	}

	public void paint(Graphics g) {
		if (layerList == null) {
		} else {
			if (!layerList.isEmpty()) {
				ListIterator i = layerList.listIterator(0);
				while (i.hasNext()) {
					Layer temp = (Layer) i.next();
					g.drawImage(temp.getMap(), 0, 0, null);
				}
			}
		}
		if (pinList != null) {
			double zoomFactor = 4 - (zoom / 100);
			ListIterator i = pinList.listIterator(0);
			while (i.hasNext()) {
				Pin currentPin = (Pin) i.next();
				Image img = currentPin.getImage();
				String description = currentPin.getName();
				double x = ((currentPin.getPosition().x - start.x) / zoomFactor) - 7;
				double y = ((currentPin.getPosition().y - start.y) / zoomFactor) - 7;
				if (x > 0 && y > 0) {
					g.drawImage(img, (int)x, (int)y, null);
					g.drawString(description, (int)x + 17, (int)y + 13);
				}
			}
		}
	}

	public void setPins(ArrayList pins) {
		
		pinList = pins;
		repaint();
	}
}