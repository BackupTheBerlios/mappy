package server;

import java.awt.Color;
import java.awt.Point;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JPanel;
import javax.swing.JProgressBar;



import data.*;

/**
 * The toplevel server-class handling all the tasks of the client and 
 * returns them to the GUI
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *
 */
public class Mappy{
	private DBValues DB;
	private MapLabel map;
	private Color[] layerColors;
	private Color[] layerColorsAlpha;
	private ArrayList pins;
	private Point startPoint;
	private int zoom;

	/**
	 * Class constructor which creates a new Mappy with the information of the
	 * DBValues-constructor and the MapLabel-constructor
	 */
	public Mappy(){
		DB = new DBValues();
		map = new MapLabel();
		pins = new ArrayList();
	}

	/**
	 * Returns all Layers selected by the parameters
	 * @param d		Dimension of the Layers
	 * @param p		Point of the Layers
	 * @param zoom	Zoom of the Layers
	 * @param layerIds	The LayerIds of the selected Layers
	 * @param progress	the defined progressbar
	 * @return		ArrayList with the information of the BufferedImages
	 */
	public ArrayList getLayers(Dimension d, Point p, int zoom, int[] layerIds, JProgressBar progress){
		ArrayList layers = new ArrayList();
		for (int i = 0; i < layerIds.length; i++){
			Layer layer = new Layer(d, p, zoom, layerIds[i]+1, DB);
			layer.setColor(layerColors[layerIds[i]], layerColorsAlpha[layerIds[i]]);
			layers.add(layer);
			progress.setValue(i+1);
		}
		return layers;
	}
	

	/**
	 * Refreshes the selected Layers
	 * @param upperLeft	Point of the selected Layers
	 * @param layersToShow	the Array containing the indices of the Layers to show
	 * @param zoom	the defined zoom
	 * @param progress	the defined progressbar
	 * @param layerColors	the defined layerColors
	 * @param layerColorsAlpha	the predefined layer Colors
	 */
	public void refresh(Point upperLeft, int[] layersToShow, int zoom, JProgressBar progress, Color[] layerColors, Color[] layerColorsAlpha){
		this.zoom = zoom;
		this.startPoint = upperLeft;
		this.layerColors = layerColors;
		this.layerColorsAlpha = layerColorsAlpha;
		map.refresh(this.getLayers(map.getSize(), upperLeft, zoom, layersToShow, progress), pins, zoom, upperLeft);
	}
	/**
	 * @return the current MapLabel as JPanel
	 */
	public JPanel getMapLabel(){
		return map;
	}
	/**
	 * @return the Status of the connection (true: connection is closed)
	 */
	public boolean closeDB(){
		return DB.closeConnection();
	}
	/**
	 * Sets a marker to a defined location
	 * @param p		Point of the marker
	 * @param name	name of the marker
	 * @param zoom	zoom of the marker
	 */
	public void setPin(Point p, String name, int zoom){
		this.zoom = zoom;
		boolean b = false;
		Integer test = pinExists(p);
		if(test == null){
			if(pins != null){
				ListIterator i = pins.listIterator(0);
				while (i.hasNext()){
					Pin temp = (Pin)i.next();
					if(temp.getName().equals(name)){
						System.err.println("Pin already exists");
						b = true;
					}
				}
			}
			if (b == false){
				pins.add(new Pin(p,name));
				map.setPins(pins, zoom);
			}
		}
	}
	/**
	 * 
	 * @param p
	 * @return
	 */
	public Integer pinExists(Point p){
		Integer index = null;
		if(pins != null){
			ListIterator i = pins.listIterator(0);
			while (i.hasNext()){
				Pin temp = (Pin)i.next();
				Point position = temp.getPosition();
				if(p.x >= position.x - 8 && p.x <= position.x + 8){
					if(p.y >= position.y - 8 && p.y <= position.y + 8){
						index = new Integer(i.nextIndex() - 1);
					}
				}
			}
		}
		return index;
	}
	/**
	 * Removes a defined marker
	 * @param p	Point from where the marker should be removed
	 */
	public void removePin(Point p){
		Integer test = pinExists(p);
		if(test != null){
			pins.remove(test.intValue());
		}
		map.setPins(pins, zoom);
	}
	
	/**
	 * @return String array with the names of all pins
	 */
	public String[] getPositions(){
		String[] names = null;
		if(pins != null){
			names = new String[pins.size()];
			ListIterator i = pins.listIterator(0);
			while (i.hasNext()){
				Pin temp = (Pin)i.next();
				names[i.nextIndex()-1] = temp.getName();
			}
		}
		return names;
	}

	/**
	 * Calculates the distance between a Start point and a marker
	 * @param start	the Startpoint
	 * @param name	the name of the marker
	 * @return		the distance between the two points
	 */
	public int getDistance(Point start, Point mapStart, String name){
		double realDistance = 0;
		int realD = 0;
		if(pins != null){
			ListIterator i = pins.listIterator(0);
			while (i.hasNext()){
				Pin temp = (Pin)i.next();
				if(temp.getName().equals(name)){
					Point destination = temp.getPosition();
					start.x = start.x - mapStart.x;
					start.y = start.y - mapStart.y;
					destination.x = destination.x - start.x - mapStart.x;
					destination.y = destination.y - start.y - mapStart.y;
					double aSquare = (start.x - destination.x)*(start.x - destination.x);
					double bSquare = (start.y - destination.y)*(start.y - destination.y);
					double cSquare = aSquare + bSquare;
					double distanceInPixels = Math.sqrt(cSquare);
					realDistance = (distanceInPixels * 0.5) * (4 - (zoom / 100));
					realD = (int)realDistance;
				}
			}
		}
		return realD;
	}
}
