/*
 * Created on 20.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package server;

import java.awt.Image;
import java.awt.Point;
import java.io.File;

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
		pin = new ImageIcon("images" + File.separatorChar + "mappy_icon_20x20.gif");
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
	boolean checkAtLocation(Point x){
		if(x.x >= position.x && x.y >= position.y){
			if((x.x + 20 <= position.x + 20) && (x.y + 20 <= position.y + 20)){
				return true;
			}
		}
		return false;
	}
}
