/*
 * Created on 07.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AboutDialog extends JDialog {
	
	private ImageIcon uniIcon;
	private JLabel uniLabel;
	private JLabel team;
	private JButton close;
	public AboutDialog(JFrame parent){
		super(parent, "Über Mappy", true);
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (screenDimension.width - this.getSize().width)/2;
		int yPos = (screenDimension.height - this.getSize().height)/2;
		this.setLocation(xPos, yPos);
		setResizable (false);
		initComponents();
	}
	private void initComponents(){
		uniIcon = new ImageIcon ("uniba.gif");
		uniLabel = new JLabel (uniIcon);
		getContentPane().add(uniLabel, BorderLayout.NORTH);
		close = new JButton ("Schliessen");
		getContentPane().add(close);
		pack();
		setVisible (true);
	}
}
