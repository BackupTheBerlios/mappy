package client;

import javax.swing.JButton;

/**
 * Button with an id
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *  
 */
public class IDButton extends JButton {
	/**
	 * ID of the Button
	 */
	public int id;

	public IDButton(String s, int id) {
		super(s);
		this.id = id;
	}
}