/*
 * Created on 04.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

import java.awt.Color;
import java.awt.Point;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JProgressBar;
import javax.sound.sampled.*;

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
	private Color[] layerColorsAlpha;
	private int zoom;
	private Gui owner;


	Refresher(Gui owner){
		this.owner = owner;
		this.layersToShow = owner.getLayers();
		this.refToMappy = owner.getMappy();
		this.progress = owner.getProgress();
		this.sb = owner.getSb();
		this.upperLeft = sb.getUpperLeft();
		this.layerColors = owner.getLayerColors();
		this.layerColorsAlpha=owner.getLayerColorsAlpha();
		this.zoom = owner.getZoomSlider().getValue();
		sb.setInfo("Aktualisiere Karte");
	}


	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run(){
		File file = new File("mapload.wav");
		AudioInputStream ais;
		AudioFormat format;
		Clip cl = null;
		try{
			ais = AudioSystem.getAudioInputStream(file);
			format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format,
			((int) ais.getFrameLength() * format.getFrameSize()));
			
			cl = (Clip) AudioSystem.getLine(info);
			cl.open(ais);
		}
		catch(Exception e){}
		cl.start();
		cl.loop(Clip.LOOP_CONTINUOUSLY);
		
		refToMappy.refresh(upperLeft, layersToShow, zoom, progress, layerColors, layerColorsAlpha);
		sb.setInfo("Fertig!");

		owner.getWait().setVisible(false);
		cl.stop();
		cl.close();
	}
}
