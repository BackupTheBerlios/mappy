/*
 * Created on 07.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class AboutDialog extends JDialog {
	
	public AboutDialog(JFrame parent){
		super(parent, "Über Mappy", true);
		pack();
	}
}
