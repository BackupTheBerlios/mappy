/*
 * Created on 12.01.2005
 *
 */
package client;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author Nicolas Fritsch
 *
 */
public class DirectionButton extends JButton{

	  public DirectionButton( String file )
	  {
	    super( new ImageIcon(file) );
	    setContentAreaFilled( false );
	    setBorderPainted( false );
	    setFocusPainted( false );
	  }
	}



