/*
 * Created on 04.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

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


	Refresher(Point upperLeft, int[] layersToShow, Mappy refToMappy, JProgressBar progress){
		this.upperLeft = upperLeft;
		this.layersToShow = layersToShow;
		this.refToMappy = refToMappy;
		this.progress = progress;
	}


	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		refToMappy.refresh(upperLeft, layersToShow, progress);
	}
}
