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
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * @author ba008268
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MapPopup extends JPopupMenu{
	private Gui owner;
	private Point p;
	private JMenuItem setPin;
	private JMenuItem delPin;
	private JMenu distance;
	private String[] names;
	private JMenuItem[] itemForName;
	private Point mousePoint;

	MapPopup(String name, Gui owner){
		super(name);
		this.owner = owner;
		setPin = new JMenuItem("Markierung setzen");
		setPin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				openInpuDialog();
			}
		});
		add(setPin);
		delPin = new JMenuItem("Markierung entfernen");
		delPin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				deletePoint();
			}
		});
		add(delPin);
		addSeparator();
		distance = new JMenu("Entfernung zu...");
		distance.addMenuListener(new MenuListener(){
			public void menuCanceled(MenuEvent arg0){
			}
			public void menuDeselected(MenuEvent arg0){
			}
			public void menuSelected(MenuEvent arg0){
				fillMenu();
			}
		});
		add(distance);
	}

	/**
	 * 
	 */
	protected void fillMenu(){
		distance.removeAll();
		names = owner.getMappy().getPositions();
		itemForName = new JMenuItem[names.length];
		for(int i = 0; i < names.length; i++){
			System.out.println (names[i]);
			itemForName[i] = new JMenuItem(names[i]);
			itemForName[i].addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent arg0) {
					getDistance(arg0);
				}
				
			});
			distance.add(itemForName[i]);
		}
	}

	/**
	 * 
	 */
	protected void getDistance(ActionEvent arg0) {
		String name = ((JMenuItem)arg0.getSource()).getLabel();
		int distance = owner.getMappy().getDistance(p, name);
		JOptionPane.showMessageDialog(null, "Die Entfernung zu " + name + " beträgt ca. " + distance + " Meter.", "Distanz", JOptionPane.PLAIN_MESSAGE);
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
			if(name.length() == 0){
				name = "Markierung";
			}
			owner.getMappy().setPin(p, name);
		}
	}
}
