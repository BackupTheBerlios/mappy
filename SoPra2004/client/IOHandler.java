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
 * IOHandler for the different options to save
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *
 */

public class IOHandler implements SettingsIF, LayersIF{
	/**
	 * Returns the saved Startpoint
	 * @return	the saved upperLeft Point of the map
	 */
	static Point getSavedStart(){
		if(IOHandler.class.getResource("/save/start.mpy")!=null){
			File path = new File (IOHandler.class.getResource("/save/start.mpy").getFile());
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
	/**
	 * Returns the saved Layers
	 * @return	the saved int-Array of the selected Layers
	 */
	static int[] getSavedLayers(){
		if(IOHandler.class.getResource("/save/layers.mpy")!=null){
			File path = new File (IOHandler.class.getResource("/save/layers.mpy").getFile());
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
	/**
	 * Returns the saved Windowsize
	 * @return	the saved Dimension of the window
	 */
	static Dimension getSavedWindowSize(){
		if(IOHandler.class.getResource("/save/windowSize.mpy")!=null){
			File path = new File (IOHandler.class.getResource("/save/windowSize.mpy").getFile());
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
	/**
	 * Returns the saved Window Position
	 * @return	the saved Location of the window
	 */
	static Point getSavedWindowPosition(){
		
		if(IOHandler.class.getResource("/save/windowPos.mpy")!=null){
			File path = new File (IOHandler.class.getResource("/save/windowPos.mpy").getFile());
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
	/**
	 * Returns the saved Map Size
	 * @return	the saved Dimension the Map
	 */
	static Dimension getSavedMapSize(){
		
		if(IOHandler.class.getResource("/save/mapSize.mpy")!=null){
			File path = new File (IOHandler.class.getResource("/save/mapSize.mpy").getFile());
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
	/**
	 * Returns the saved Zoom
	 * @return	the saved Zoom Value as an int
	 */
	static int getSavedZoom(){
		if(IOHandler.class.getResource("/save/zoom.mpy")!=null){
			File path = new File (IOHandler.class.getResource("/save/zoom.mpy").getFile());
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
	/**
	 * Returns the saved Database settings
	 * @return	the saved Database Settings as a String-Array
	 */
	static String[] getSavedDbSettings(){
		if(IOHandler.class.getResource("/save/dbSettings.mpy")!=null){
			File path = new File (IOHandler.class.getResource("/save/dbSettings.mpy").getFile());
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
	/**
	 * Returns the saved Color Settings
	 * @return	the saved Color Settings as a Color-Array
	 */
	static Color[] getSavedColorSettings(){
		if(IOHandler.class.getResource("/save/colorSettings.mpy")!=null){
			File path = new File (IOHandler.class.getResource("/save/colorSettings.mpy").getFile());
			try{
				FileInputStream file = new FileInputStream (path);
				ObjectInputStream colorSettings = new ObjectInputStream (file);
				Color[] colorSet = (Color[])colorSettings.readObject();
				colorSettings.close();
				return colorSet;
			}
			catch (IOException e){
				System.err.println ("Failed to load colors, using default");
				return LAYERCOLORS;
			}
			catch (ClassNotFoundException e){
				System.err.println ("Failed to load colors, using default");
				return LAYERCOLORS;
			}
		}
		else{
			System.err.println("Failed to load colors, using default");
			return LAYERCOLORS;
		}
	}
	/**
	 * Returns the saved Sound Settings
	 * @return	the saved Sound Setting as a boolean
	 */
	static boolean getSavedSoundSettings(){
		if(IOHandler.class.getResource("save/soundSettings.mpy")!=null){
			File path = new File (IOHandler.class.getResource("/save/soundSettings.mpy").getFile());
			try{
				FileInputStream file = new FileInputStream (path);
				ObjectInputStream soundSettings = new ObjectInputStream (file);
				Boolean b = (Boolean)soundSettings.readObject();
				boolean bool = b.booleanValue();
				System.out.println("gesucht");
				return bool;
			}
			catch (IOException e){
				System.err.println ("Failed to load soundSettings. Sounds enabled");
				System.out.println("gesucht");
				return false;
			}
			catch (ClassNotFoundException e){
				System.err.println ("Failed to load soundSettings. Sounds enabled");
				System.out.println("gesucht");
				return false;
			}
		}
		else{
			System.err.println("Failed to load soundSettings. Sounds enabled");
			System.out.println("gesucht");
			return false;
		}
	}
	
	
	
	/**
	 * Saves the current Startpoint 
	 * @param startPoint	the current upperLeft of the map
	 */
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
	/**
	 * Saves the currently selected Layers
	 * @param layers	the currently selected Layers
	 */
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
	/**
	 * Saves the current Window Size
	 * @param window	the current Window
	 */
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
	/**
	 * Saves the current Window Position
	 * @param window	the current Window
	 */
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
	/**
	 * Saves the current Map Size
	 * @param map	the current JPanel map
	 */
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
	/**
	 * Saves the currently selected Zoom
	 * @param zoom	the current zoom Value
	 */
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
	/**
	 * Saves the current Database Settings
	 * @param dbSettings	the current Database Settings
	 */
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
	/**
	 * Saves the current Color Settings
	 * @param colorSettings	the current Color Settings
	 */
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
	/**
	 * Saves the current Sound Settings
	 * @param b	the current Sound Setting (enabled/disabled)
	 */
	static void saveSoundSettings(boolean b){
		File path = new File ("save" + File.separatorChar + "soundSettings.mpy");
		try {
			if (!path.exists()) {
				new File("save").mkdir();
			}
			FileOutputStream file = new FileOutputStream (path);
			ObjectOutputStream sound = new ObjectOutputStream (file);
			sound.writeObject(new Boolean(b));
			sound.close();
		}
		catch ( IOException e ) {
			System.err.println("Failed to save soundSettings");
		}
	}
	
	/**
	 * delets the save file
	 */
	static void deleteColorSettings(){
		File path = new File ("save" + File.separatorChar + "colorSettings.mpy");
		if (path.exists()){
			path.delete();
		}
	}
	/**
	 * delets the save file
	 */
	static void deleteDBSettings(){
		File path = new File ("save" + File.separatorChar + "dbSettings.mpy");
		if (path.exists()){
			path.delete();
		}
	}
	/**
	 * delets the save file
	 */
	static void deleteSoundSettings(){
		File path = new File ("save" + File.separatorChar + "soundSettings.mpy");
		if (path.exists()){
			path.delete();
		}
	}
	/**
	 * delets the save file
	 */
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
	/**
	 * delets the save file
	 */
	static boolean changesExist(){
		File path1 = new File ("save" + File.separatorChar + "colorSettings.mpy");
		File path2 = new File ("save" + File.separatorChar + "dbSettings.mpy");
		File path3 = new File ("save" + File.separatorChar + "soundSettings.mpy");
		if(path1.exists() || path2.exists() || path3.exists()){
			return true;
		}
		else{
			return false;
		}
	}
}
