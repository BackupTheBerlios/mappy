package server;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JPanel;

/**
 * Creats a Label out of the different Buffered images
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *
 */
class MapLabel extends JPanel {
	private ArrayList layerList;
	private ArrayList pinList;
	private int zoom;
	private Point start;

	/**
	 * Class constructor
	 */
	MapLabel() {
		setDoubleBuffered(true);
		setOpaque(false);
		setIgnoreRepaint(false);
	}

	/**
	 * Refreshes the Label
	 * @param layerList	Layers to be refreshed
	 * @param pins		Markers to be refreshed
	 * @param zoom		defined zoom
	 * @param start		defined Startpoint
	 */
	public void refresh(ArrayList layerList, ArrayList pins, int zoom, Point start) {
		this.zoom = zoom;
		this.start = start;
		this.pinList = pins;
		this.layerList = layerList;
		repaint();
	}

	/* (non-Javadoc)
	 * @see java.awt.Component#paint(java.awt.Graphics)
	 */
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
			double zoomFactor = 4 - (zoom/100);
			ListIterator i = pinList.listIterator(0);
			while (i.hasNext()) {
				Pin currentPin = (Pin) i.next();
				Image img = currentPin.getImage();
				String description = currentPin.getName();
				System.out.println (start.x);
				double x = ((currentPin.getPosition().x - start.x) / zoomFactor - 7);
				double y = ((currentPin.getPosition().y - start.y) / zoomFactor - 7);
				if (x > 0 && y > 0) {
					g.drawImage(img, (int)x, (int)y, null);
					g.drawString(description, (int)x + 17, (int)y + 13);
				}
			}
		}
	}

	/**
	 * Sets the markers in the Label
	 * @param pins	Markers to be set
	 * @param zoom	Zoom to be set
	 */
	public void setPins(ArrayList pins, int zoom){
		this.zoom = zoom;
		pinList = pins;
		repaint();
	}
}