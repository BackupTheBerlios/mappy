/*
 * Created on 15.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;


import java.awt.*;

import javax.swing.*;


/**
 * @author ba008959
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Features extends JPanel{

	JButton update=new JButton("aktualisieren");
	public void addCheckBox(String layer){
		this.setLayout(new GridLayout(0,1));
		this.add(new JCheckBox(layer));
		this.add(update);
	}
}
