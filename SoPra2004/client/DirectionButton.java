//javadoc ready
/*
 * Created on 12.01.2005
 *
 */
package client;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Button without borders or decoration, just an image
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *
 */
public class DirectionButton extends JButton{

	  public DirectionButton( String file ){
	  	super( new ImageIcon(file) );
	    setContentAreaFilled( false );
	    setBorderPainted( false );
	    setFocusPainted( false );
	  }
}