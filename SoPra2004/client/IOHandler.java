/*
 * Created on 18.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * @author DrRSatzteil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class IOHandler {
	static Point getSavedStart(){
		File path = new File ("save" + File.separatorChar + "start.mpy");
		if(path.exists()){
			try{
				FileInputStream file = new FileInputStream (path);
				ObjectInputStream upperLeft = new ObjectInputStream (file);
				Point start = (Point) upperLeft.readObject();
				upperLeft.close();
				return start;
			}
			catch (IOException e) {
				System.err.println ("Failed to load Startpoint");
				return new Point(0,0);
			}
			catch (ClassNotFoundException e) {
				System.err.println ("Failed to load Startpoint");
				return new Point(0,0);
			}
		}
		else{
			System.err.println("Failed to load StartPoint");
			return new Point(5500,5500);
		}
	}
	static int[] getSavedLayers(){
		File path = new File ("save" + File.separatorChar + "layers.mpy");
		if(path.exists()){
			try{
				FileInputStream file = new FileInputStream (path);
				ObjectInputStream layers = new ObjectInputStream (file);
				int[] layersToShow = (int[]) layers.readObject();
				layers.close();
				return layersToShow;
			}
			catch (IOException e) {
				System.err.println ("Failed to load Layerlist");
				return new int[0];
			}
			catch (ClassNotFoundException e) {
				System.err.println ("Failed to load Layerlist");
				return new int[0];
			}
		}
		else{
			System.err.println("Failed to load Layerlist");
			return new int[0];
		}
	}
	static void saveStartPoint (Point startPoint){
		File path = new File ("save" + File.separatorChar + "start.mpy");
		try {
			if (!path.exists()) {
				path.mkdir ();
			}
			FileOutputStream file = new FileOutputStream (path);
			ObjectOutputStream point = new ObjectOutputStream (file);
			point.writeObject(startPoint);
			point.close();
		}
		catch ( IOException e ) {
			System.err.println("Failed to save StartPoint");
		}
  	}
	static void saveLayers (int[] layers){
		File path = new File ("save" + File.separatorChar + "layers.mpy");
		try {
			if (!path.exists()) {
				path.mkdir ();
			}
			FileOutputStream file = new FileOutputStream (path);
			ObjectOutputStream layer = new ObjectOutputStream (file);
			layer.writeObject(layers);
			layer.close();
		}
		catch ( IOException e ) {
			System.err.println("Failed to save Layers");
		}
  	}
}
