package server;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 * Class for marking different points on the map
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *  
 */
public class Pin {
	ImageIcon pin;

	Point position;

	String name;

	/**
	 * Class constructor creates a mark on the map
	 * 
	 * @param position
	 *            Point of the marker
	 * @param name
	 *            name of the marker as a String
	 */
	Pin(Point position, String name) {
		pin = new ImageIcon(getClass().getResource(
				"/images/mappy_icon_15x15.gif"));
		this.position = position;
		this.name = name;
	}

	/**
	 * @return the image of the marker
	 */
	Image getImage() {
		return pin.getImage();
	}

	/**
	 * @return the current position of the marker
	 */
	Point getPosition() {
		return position;
	}

	/**
	 * @return the name of the current marker
	 */
	String getName() {
		return name;
	}
}