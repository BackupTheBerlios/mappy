package server;

import java.awt.image.RGBImageFilter;
import java.awt.*;
/**
 * Class to change the Colors of the diffrent Lazers
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *
 */
public class ColorChanger extends RGBImageFilter {
	  
	 private int rgb;  
	 private int rgbTo;
	  
	    /**
	     * Class constructor
	     * @param colorFrom	old color
	     * @param colorTo	new color
	     */
	    public ColorChanger(Color colorFrom, Color colorTo) {
	      // Speichern der zu switchenden Farbe als int
	      this.rgb = colorFrom.getRGB();
	      this.rgbTo = colorTo.getRGB();
	      System.out.println("Filter gestartet");
	      // Hab nicht so genau rausgefunden was das bedeutet
	      canFilterIndexColorModel = false;
	    }
	
	    public int filterRGB(int x, int y, int rgb) {
	    	if(rgb==this.rgb){
	    		rgb=rgbTo;
	    	}
	    	return rgb;
		    }
	    
	}
