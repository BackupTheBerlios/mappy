/*
 * Created on 21.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @author ba008268
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MapPopup extends JPopupMenu{
	private Gui owner;
	private Point p;

	MapPopup(String name, Gui owner){
		super(name);
		this.owner = owner;
		JMenuItem setPin = new JMenuItem("Pin setzen");
		setPin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				openInpuDialog();
			}
		});
		JMenuItem delPin = new JMenuItem("Pin entfernen");
		delPin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				deletePoint();
			}
		});
		add(setPin);
		add(delPin);
	}

	/**
	 * 
	 */
	protected void deletePoint() {
		if(owner.getMappy().pinExists(p) != null){
			owner.getMappy().removePin(p);
		}
	}

	void setPinPoint(Point p){
		Point mapPoint = owner.getSb().getUpperLeft();
		this.p = new Point(mapPoint.x + p.x, mapPoint.y + p.y);
	}
	/**
	 * 
	 */
	protected void openInpuDialog(){
		if(owner.getMappy().pinExists(p) == null){
			String name = JOptionPane.showInputDialog("Bitte Namen für Markierung eingeben");
			owner.getMappy().setPin(p, name);
		}
	}
}
