/*
 * Created on 13.01.2005
 *
 */
package client;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author Nicolas Fritsch
 *
 */
public class SelectionButton extends JButton {
	  public SelectionButton( String file, String fileOver)

	  {
	    super( new ImageIcon(file) );
	    setContentAreaFilled( false );
	    setBorderPainted( false );
	    setFocusPainted( false );
	    this.setRolloverIcon(new ImageIcon(fileOver));
	  }
	  public SelectionButton( URL file, URL fileOver)
	  {
	    super( new ImageIcon(file) );
	    setContentAreaFilled( false );
	    setBorderPainted( false );
	    setFocusPainted( false );
	    this.setRolloverIcon(new ImageIcon(fileOver));
	  }
}
