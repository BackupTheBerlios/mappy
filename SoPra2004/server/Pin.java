/*
 * Created on 20.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package server;

import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 * @author DrRSatzteil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Pin{
	ImageIcon pin;
	Point position;
	String name;
	
	Pin(Point position, String name){
		pin = new ImageIcon(getClass().getResource("/images/mappy_icon_15x15.gif"));
		this.position = position;
		this.name = name;
	}
	Image getImage(){
		return pin.getImage();
	}
	Point getPosition(){
		return position;
	}
	String getName(){
		return name;
	}
}
