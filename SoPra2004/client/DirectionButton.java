package client;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Button without borders or decoration, just an image
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *  
 */
public class DirectionButton extends JButton {

	/**
	 * Class constructor to build a Direction Button
	 * @param file	a String giving the base location of the file
	 */
	public DirectionButton(String file) {
		super(new ImageIcon(file));
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
	}
	/**
	 * Class constructor to build a Direction Button
	 * @param file	a URL giving the base location of the file
	 */
	public DirectionButton(URL file) {
		super(new ImageIcon(file));
		setContentAreaFilled(false);
		setBorderPainted(false);
		setFocusPainted(false);
	}
}