/*
 * Created on 04.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JProgressBar;

import server.Mappy;

/**
 * @author DrRSatzteil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Refresher implements Runnable{
	
	private Point upperLeft;
	private int[] layersToShow;
	private Mappy refToMappy;
	private JProgressBar progress;
	private StatusBar sb;
	private Color[] layerColors;
	private int zoom;
	private Gui owner;


	Refresher(Gui owner){
		this.owner = owner;
		this.upperLeft = owner.getUpperLeft();
		this.layersToShow = owner.getLayers();
		this.refToMappy = owner.getMappy();
		this.progress = owner.getProgress();
		this.sb = owner.getSb();
		this.layerColors = owner.getLayerColors();
		this.zoom = owner.getZoomSlider().getValue();
		sb.setInfo("Aktualisiere Karte");
	}


	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run(){
		refToMappy.refresh(upperLeft, layersToShow, zoom, progress, layerColors);
		owner.getWait().setVisible(false);
		sb.setInfo("Fertig!");
	}
}
