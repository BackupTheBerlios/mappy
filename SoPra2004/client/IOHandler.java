/*
 * Created on 18.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JPanel;


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
			catch (IOException e){
				System.err.println ("Failed to load Startpoint");
				return new Point(0,0);
			}
			catch (ClassNotFoundException e){
				System.err.println ("Failed to load Startpoint");
				return new Point(0,0);
			}
		}
		else{
			System.err.println("Failed to load StartPoint");
			return new Point(4000,3000);
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
			catch (IOException e){
				System.err.println ("Failed to load Layerlist");
				return new int[0];
			}
			catch (ClassNotFoundException e){
				System.err.println ("Failed to load Layerlist");
				return new int[0];
			}
		}
		else{
			System.err.println("Failed to load Layerlist");
			return new int[0];
		}
	}
	static Dimension getSavedWindowSize(){
		File path = new File ("save" + File.separatorChar + "windowSize.mpy");
		if(path.exists()){
			try{
				FileInputStream file = new FileInputStream (path);
				ObjectInputStream window = new ObjectInputStream (file);
				Dimension windowSize = (Dimension) window.readObject();
				window.close();
				return windowSize;
			}
			catch (IOException e){
				System.err.println ("Failed to load WindowSize");
				return new Dimension(600,600);
			}
			catch (ClassNotFoundException e){
				System.err.println ("Failed to load WindowSize");
				return new Dimension(600,600);
			}
		}
		else{
			System.err.println("Failed to load WindowSize");
			return new Dimension(600,600);
		}
	}
	static Point getSavedWindowPosition(){
		File path = new File ("save" + File.separatorChar + "windowPos.mpy");
		if(path.exists()){
			try{
				FileInputStream file = new FileInputStream (path);
				ObjectInputStream window = new ObjectInputStream (file);
				Point windowPosition = (Point) window.readObject();
				window.close();
				return windowPosition;
			}
			catch (IOException e){
				System.err.println ("Failed to load WindowPosition");
				return new Point(0,0);
			}
			catch (ClassNotFoundException e){
				System.err.println ("Failed to load WindowPosition");
				return new Point(0,0);
			}
		}
		else{
			System.err.println("Failed to load WindowPostition");
			return new Point(0,0);
		}
	}
	static Dimension getSavedMapSize(){
		File path = new File ("save" + File.separatorChar + "mapSize.mpy");
		if(path.exists()){
			try{
				FileInputStream file = new FileInputStream (path);
				ObjectInputStream map = new ObjectInputStream (file);
				Dimension mapSize = (Dimension) map.readObject();
				map.close();
				return mapSize;
			}
			catch (IOException e){
				System.err.println ("Failed to load MapSize");
				return new Dimension(0,0);
			}
			catch (ClassNotFoundException e){
				System.err.println ("Failed to load MapSize");
				return new Dimension(0,0);
			}
		}
		else{
			System.err.println("Failed to load MapSize");
			return new Dimension(0,0);
		}
	}
	static int getSavedZoom(){
		File path = new File ("save" + File.separatorChar + "zoom.mpy");
		if(path.exists()){
			try{
				FileInputStream file = new FileInputStream (path);
				ObjectInputStream zoomFactor = new ObjectInputStream (file);
				Integer zoom = (Integer)zoomFactor.readObject();
				int zoomValue = zoom.intValue();
				zoomFactor.close();
				return zoomValue;
			}
			catch (IOException e){
				System.err.println ("Failed to load ZoomFactor");
				return 100;
			}
			catch (ClassNotFoundException e){
				System.err.println ("Failed to load ZoomFactor");
				return 100;
			}
		}
		else{
			System.err.println("Failed to load ZoomFactor");
			return 100;
		}
	}
	static void saveStartPoint (Point startPoint){
		File path = new File ("save" + File.separatorChar + "start.mpy");
		try {
			if (!path.exists()) {
				new File("save").mkdir ();
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
	static void saveLayers(int[] layers){
		File path = new File ("save" + File.separatorChar + "layers.mpy");
		try {
			if (!path.exists()) {
				new File("save").mkdir ();
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
	static void saveWindowSize(JFrame window){
		File path = new File ("save" + File.separatorChar + "windowSize.mpy");
		try {
			if (!path.exists()) {
				new File("save").mkdir ();
			}
			FileOutputStream file = new FileOutputStream (path);
			ObjectOutputStream windowProperties = new ObjectOutputStream (file);
			windowProperties.writeObject(window.getSize());
			windowProperties.close();
		}
		catch ( IOException e ) {
			System.err.println("Failed to save WindowSize");
		}
	}
	static void saveWindowPosition(JFrame window){
		File path = new File ("save" + File.separatorChar + "windowPos.mpy");
		try {
			if (!path.exists()) {
				new File("save").mkdir ();
			}
			FileOutputStream file = new FileOutputStream (path);
			ObjectOutputStream windowProperties = new ObjectOutputStream (file);
			windowProperties.writeObject(window.getLocationOnScreen());
			windowProperties.close();
		}
		catch ( IOException e ) {
			System.err.println("Failed to save WindowPosition");
		}
	}
	static void saveMapSize(JPanel map){
		File path = new File ("save" + File.separatorChar + "mapSize.mpy");
		try {
			if (!path.exists()) {
				new File("save").mkdir ();
			}
			FileOutputStream file = new FileOutputStream (path);
			ObjectOutputStream mapProperties = new ObjectOutputStream (file);
			mapProperties.writeObject(map.getSize());
			mapProperties.close();
		}
		catch ( IOException e ) {
			System.err.println("Failed to save MapSize");
		}
	}
	static void saveZoomValue (int zoom){
		File path = new File ("save" + File.separatorChar + "zoom.mpy");
		try {
			if (!path.exists()) {
				new File("save").mkdir();
			}
			FileOutputStream file = new FileOutputStream (path);
			ObjectOutputStream zoomProperties = new ObjectOutputStream (file);
			Integer zoomValue = new Integer(zoom);
			zoomProperties.writeObject(zoomValue);
			zoomProperties.close();
		}
		catch ( IOException e ) {
			System.err.println("Failed to save ZoomFactor");
		}
  	}
}
