package client;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

/**
 * Popup for setting marks on the map
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *  
 */
public class MapPopup extends JPopupMenu {
	private Gui owner;

	private Point p;

	private JMenuItem setPin;

	private JMenuItem delPin;

	private JMenu distance;

	private String[] names;

	private JMenuItem[] itemForName;

	private int zoom;

	private JMenu jumpTo;

	/**
	 * Class Constructor builds a PopUp
	 * 
	 * @param name
	 *            the name of the Popup as a String
	 * @param owner
	 *            the GUI the PopUp belongs to
	 */
	MapPopup(String name, Gui owner) {
		super(name);
		this.owner = owner;
		setPin = new JMenuItem("Markierung setzen");
		setPin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openInputDialog();
			}
		});
		add(setPin);
		delPin = new JMenuItem("Markierung entfernen");
		delPin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deletePoint();
			}
		});
		add(delPin);
		addSeparator();
		distance = new JMenu("Entfernung zu...");
		distance.addMenuListener(new MenuListener() {
			public void menuCanceled(MenuEvent arg0) {
			}

			public void menuDeselected(MenuEvent arg0) {
			}

			public void menuSelected(MenuEvent arg0) {
				fillMenu();
			}
		});
		add(distance);
		addSeparator();
		jumpTo = new JMenu("Springe zu...");
		jumpTo.addMenuListener(new MenuListener() {

			public void menuSelected(MenuEvent arg0) {
				fillMenu2();
			}

			public void menuDeselected(MenuEvent arg0) {
			}

			public void menuCanceled(MenuEvent arg0) {
			}
		});
		add(jumpTo);
	}

	/**
	 *  
	 */
	private void fillMenu() {
		distance.removeAll();
		names = owner.getMappy().getPositions();
		itemForName = new JMenuItem[names.length];
		for (int i = 0; i < names.length; i++) {
			itemForName[i] = new JMenuItem(names[i]);
			itemForName[i].addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					getDistance(arg0);
				}

			});
			distance.add(itemForName[i]);
		}
	}

	private void fillMenu2() {
		jumpTo.removeAll();
		names = owner.getMappy().getPositions();
		itemForName = new JMenuItem[names.length];
		for (int i = 0; i < names.length; i++) {
			itemForName[i] = new JMenuItem(names[i]);
			itemForName[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					jumpTo(arg0);
				}
			});
			jumpTo.add(itemForName[i]);
		}
	}

	/**
	 *  
	 */
	private void getDistance(ActionEvent arg0) {
		String name = ((JMenuItem) arg0.getSource()).getLabel();
		int distance = owner.getMappy().getDistance(p,
				owner.getSb().getUpperLeft(), name);
		JOptionPane.showMessageDialog(null, "Die Entfernung zu " + name
				+ " beträgt ca. " + distance + " Meter.", "Distanz",
				JOptionPane.PLAIN_MESSAGE);
	}

	private void jumpTo(ActionEvent arg0) {
		String name = ((JMenuItem) arg0.getSource()).getLabel();
		Point location = owner.getMappy().getPinLocation(name);
		if (location.x - 250 > 0 && location.y - 250 > 0) {
			owner.setUpperLeft(new Point(location.x - 250, location.y - 250));
		} else {
			owner.setUpperLeft(location);
		}
		owner.refreshAction();
	}

	/**
	 *  
	 */
	private void deletePoint() {
		if (owner.getMappy().pinExists(p) != null) {
			owner.getMappy().removePin(p);
		}
	}

	void setPinPoint(Point p, int zoom) {
		p.x = p.x * (4 - zoom / 100);
		p.y = p.y * (4 - zoom / 100);
		Point mapPoint = owner.getSb().getUpperLeft();
		this.p = new Point(mapPoint.x + p.x, mapPoint.y + p.y);
		this.zoom = zoom;
	}

	/**
	 *  
	 */
	private void openInputDialog() {
		if (owner.getMappy().pinExists(p) == null) {
			String name = JOptionPane
					.showInputDialog("Bitte Namen für Markierung eingeben");
			if (name != null) {
				if (name.length() == 0) {
					name = "Markierung";
				}
				owner.getMappy().setPin(p, name, zoom);
			}
		}
	}
}