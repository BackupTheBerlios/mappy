package client;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * Layoutmanager for the Client
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *	
 */
class LayoutManager {
	static void addComponent (Container cont, GridBagLayout gbl, Component c,
			                  int x, int y, int width, int height,
							  double resizeX, double resizeY){
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		gbc.weightx = resizeX;
		gbc.weighty = resizeY;
		gbl.setConstraints(c, gbc);
		cont.add(c);
	}
}
