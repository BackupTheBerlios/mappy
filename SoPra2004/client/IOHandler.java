/*
 * Created on 18.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

import java.awt.Color;
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
public class IOHandler implements SettingsIF, LayersIF{
	static Point getSavedStart(){
		File path = new File ("save" + File.separatorChar + "start.mpy");
		if(path.exists()){
			try{
				FileInputStream file = new FileInputStream (path);
				ObjectInputStream upperLeft = new ObjectInputStream (file);
				Point start = (Point) upperLeft.readObject();
				upperLeft.close();
				if(start != null){
					return start;
				}
				else{
					return new Point(0,0);
				}
			}
			catch (IOException e){
				System.err.println ("Failed to load Startpoint");
				return new Point(0,0);
			}
			catch (ClassNotFoundException e){
				System.err.println ("start.mpy not found");
				return new Point(0,0);
			}
		}
		else{
			System.err.println("start.mpy not found");
			return new Point(0,0);
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
				if(layersToShow != null){
					return layersToShow;
				}
				else{
					return new int[0];
				}
				
			}
			catch (IOException e){
				System.err.println ("Failed to load Layerlist");
				return new int[0];
			}
			catch (ClassNotFoundException e){
				System.err.println ("layers.mpy not found");
				return new int[0];
			}
		}
		else{
			System.err.println("layers.mpy not found");
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
				if(windowSize != null){
					return windowSize;
				}
				else{
					return new Dimension(850,600);
				}
			}
			catch (IOException e){
				System.err.println ("Failed to load WindowSize");
				return new Dimension(850,600);
			}
			catch (ClassNotFoundException e){
				System.err.println ("windowSize.mpy not found");
				return new Dimension(850,600);
			}
		}
		else{
			System.err.println("windowSize.mpy not found");
			return new Dimension(850,600);
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
				if(windowPosition != null){
					return windowPosition;
				}
				else{
					return new Point(0,0);
				}
			}
			catch (IOException e){
				System.err.println ("Failed to load WindowPosition");
				return new Point(0,0);
			}
			catch (ClassNotFoundException e){
				System.err.println ("windowPos.mpy not found");
				return new Point(0,0);
			}
		}
		else{
			System.err.println("windowPos.mpy not found");
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
				if(mapSize != null){
					return mapSize;
				}
				else{
					return new Dimension(0,0);
				}
			}
			catch (IOException e){
				System.err.println ("Failed to load MapSize");
				return new Dimension(0,0);
			}
			catch (ClassNotFoundException e){
				System.err.println ("mapSize.mpy not found");
				return new Dimension(0,0);
			}
		}
		else{
			System.err.println("mapSize.mpy not found");
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
				zoomFactor.close();
				if(zoom != null){
					int zoomValue = zoom.intValue();
					return zoomValue;
				}
				else{
					return 0;
				}
			}
			catch (IOException e){
				System.err.println ("Failed to load ZoomFactor");
				return 0;
			}
			catch (ClassNotFoundException e){
				System.err.println ("zoom.mpy not found");
				return 0;
			}
		}
		else{
			System.err.println("zoom.mpy not found");
			return 0;
		}
	}
	static String[] getSavedDbSettings(){
		File path = new File ("save" + File.separatorChar + "dbSettings.mpy");
		if(path.exists()){
			try{
				FileInputStream file = new FileInputStream (path);
				ObjectInputStream dbSettings = new ObjectInputStream (file);
				String[] dbSet = (String[])dbSettings.readObject();
				dbSettings.close();
				return dbSet;
			}
			catch (IOException e){
				System.err.println ("Failed to load database settings, using default settings");
				return DB_SETTINGS;
			}
			catch (ClassNotFoundException e){
				System.err.println ("Failed to load database settings, using default settings");
				return DB_SETTINGS;
			}
		}
		else{
			System.err.println("Failed to load database settings, using default settings");
			return DB_SETTINGS;
		}
	}
	static Color[] getSavedColorSettings(){
		File path = new File ("save" + File.separatorChar + "colorSettings.mpy");
		if(path.exists()){
			try{
				FileInputStream file = new FileInputStream (path);
				ObjectInputStream colorSettings = new ObjectInputStream (file);
				Color[] colorSet = (Color[])colorSettings.readObject();
				colorSettings.close();
				return colorSet;
			}
			catch (IOException e){
				System.err.println ("Failed to load Colors, using default1");
				return LAYERCOLORS;
			}
			catch (ClassNotFoundException e){
				System.err.println ("Failed to load Colors, using default2");
				return LAYERCOLORS;
			}
		}
		else{
			System.err.println("Failed to load colors, using default3");
			return LAYERCOLORS;
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
	static void saveDbSettings (String[] dbSettings){
		File path = new File ("save" + File.separatorChar + "dbSettings.mpy");
		try {
			if (!path.exists()) {
				new File("save").mkdir();
			}
			FileOutputStream file = new FileOutputStream (path);
			ObjectOutputStream zoomProperties = new ObjectOutputStream (file);
			zoomProperties.writeObject(dbSettings);
			zoomProperties.close();
		}
		catch ( IOException e ) {
			System.err.println("Failed to save database settings");
		}
  	}
	static void saveColorSettings (Color[] colorSettings){
		File path = new File ("save" + File.separatorChar + "colorSettings.mpy");
		try {
			if (!path.exists()) {
				new File("save").mkdir();
			}
			FileOutputStream file = new FileOutputStream (path);
			ObjectOutputStream colors = new ObjectOutputStream (file);
			colors.writeObject(colorSettings);
			colors.close();
		}
		catch ( IOException e ) {
			System.err.println("Failed to save Colors");
		}
  	}
	
	static void deleteColorSettings(){
		File path = new File ("save" + File.separatorChar + "colorSettings.mpy");
		if (path.exists()){
			path.delete();
		}
	}
	static void deleteDBSettings(){
		File path = new File ("save" + File.separatorChar + "dbSettings.mpy");
		if (path.exists()){
			path.delete();
		}
	}
	static void deleteViewSettings(){
		File path = new File ("save" + File.separatorChar + "start.mpy");
		if (path.exists()){
			path.delete();
		}
		path = new File ("save" + File.separatorChar + "layers.mpy");
		if (path.exists()){
			path.delete();
		}
		path = new File ("save" + File.separatorChar + "windowSize.mpy");
		if (path.exists()){
			path.delete();
		}
		path = new File ("save" + File.separatorChar + "windowPos.mpy");
		if (path.exists()){
			path.delete();
		}
		path = new File ("save" + File.separatorChar + "mapSize.mpy");
		if (path.exists()){
			path.delete();
		}
		path = new File ("save" + File.separatorChar + "zoom.mpy");
		if (path.exists()){
			path.delete();
		}
	}
}
