package client;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Button without borders or decoration, just an image 
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *	
 */
public class SelectionButton extends JButton {
	/**
	 * Class constructor to build a Selection Button
	 * @param file	a String giving the base location of the Button-Image-file
	 * @param fileOver	a String giving the base location of the Mouse-Over-Button-Image-file
	 */  
	public SelectionButton( String file, String fileOver)

	  {
	    super( new ImageIcon(file) );
	    setContentAreaFilled( false );
	    setBorderPainted( false );
	    setFocusPainted( false );
	    this.setRolloverIcon(new ImageIcon(fileOver));
	  }
	/**
	 * Class constructor to build a Selection Button
	 * @param file	a URL giving the base location of the Button-Image-file
	 * @param fileOver	a URL giving the base location of the Mouse-Over-Button-Image-file
	 */ 
	  public SelectionButton( URL file, URL fileOver)
	  {
	    super( new ImageIcon(file) );
	    setContentAreaFilled( false );
	    setBorderPainted( false );
	    setFocusPainted( false );
	    this.setRolloverIcon(new ImageIcon(fileOver));
	  }
}
