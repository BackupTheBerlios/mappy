/*
 * Created on 18.01.2005
 *
 */
package client;

import javax.swing.JButton;

/**
 * @author Nicolas Fritsch
 *
 */
public class IDButton extends JButton {
	public  int id;
	public IDButton(String s, int id){
		super(s);
		this.id=id;
	}
}
