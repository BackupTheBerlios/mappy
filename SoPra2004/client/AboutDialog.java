//javadoc ready
/*
 * Created on 07.12.2004
 *
 */
package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Simple About Dialog
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *
 */
public class AboutDialog extends JDialog {
	
	private ImageIcon uniIcon;
	private JLabel uniLabel;
	private JLabel team;
	private JLabel mappyVersion;
	private JButton close;
	public AboutDialog(JFrame parent){
		super(parent, "Über Mappy", true);
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		int xPos = (screenDimension.width - this.getSize().width)/2-50;
		int yPos = (screenDimension.height - this.getSize().height)/2-100;
		this.setLocation(xPos, yPos);
		setResizable (false);
		initComponents();
	}
	private void initComponents(){
		uniIcon = new ImageIcon ("images" + File.separatorChar +  "aboutlogo.gif");
		uniLabel = new JLabel (uniIcon);
		getContentPane().add(uniLabel, BorderLayout.CENTER);
		mappyVersion = new JLabel("<html><p/<p/Endlich habe ich es geschafft.<p/Wir haben einen neuen about Dialog.<p/" +
				"Unsere aktuelle Version ist: 1.3<p/<p/</html>" );
		mappyVersion.setFont(new Font("Verdana", Font.PLAIN, 11));
		getContentPane().add(mappyVersion, BorderLayout.NORTH);
		close = new JButton ("Schliessen");
		getContentPane().add(close, BorderLayout.SOUTH);
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		pack();
		setVisible (true);
	}
}
