/*
 * Created on 13.01.2005
 *
 */
package client;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author Nicolas Fritsch
 *
 */
public class SelectionButton extends JButton {
	  public SelectionButton( String file )
	  {
	    super( new ImageIcon(file) );
	    setContentAreaFilled( false );
	    setBorderPainted( false );
	    setFocusPainted( false );
	  }
}
