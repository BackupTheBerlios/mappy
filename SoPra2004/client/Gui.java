/*
 * Created on 30.11.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

/**
 * @author ba008959
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

import java.awt.Color;
import javax.swing.border.*;

import server.Mappy;


public class Gui extends JFrame {
	private Mappy mappy;
	private JLabel labelMap;
	
	
	public Gui(Mappy mappy){
		super("Mappy");
		this.mappy = new Mappy();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.print("Building new GUI...");
		this.setSize(500,500);
		setNewLookAndFeel();
		initComponents();
		
		// Map setup
		// Hier Weiter Machen
		//ImageIcon image=new ImageIcon("uniba.gif");//mappy.getMap();
		//labelMap=new JLabel(image);
		//panelMap.add(labelMap);
	}
	
	void initComponents(){
		JMenuBar mb = new JMenuBar();
		setJMenuBar(mb);
		
		getContentPane().setLayout(new BorderLayout());
		
		JPanel panelFeatures = new JPanel();
		getContentPane().add(panelFeatures, BorderLayout.WEST);
		
		JPanel panelMap = new JPanel();
		getContentPane().add(panelMap, BorderLayout.CENTER);
		
		JPanel panelStatus = new JPanel();
		panelStatus.setBorder(new EtchedBorder());
		panelStatus.setLayout(new FlowLayout(FlowLayout.LEFT,1,0));
		getContentPane().add(panelStatus, BorderLayout.SOUTH);
		
		panelFeatures.setLayout(new GridLayout(0,1));
		panelFeatures.setBorder(new EtchedBorder());
		JLabel labelFeatures = new JLabel("Kartenfunktionen");
		Features features = new Features();
		features.addCheckBox("Wald");
		features.addCheckBox("Wald");
		features.addCheckBox("Wald");
		features.addCheckBox("Wald");
		features.addCheckBox("Wald");
		features.addCheckBox("Wald");
		features.addCheckBox("Wald");
		features.addCheckBox("Wald");
		features.addCheckBox("Wald");
		features.addCheckBox("Wald");
		features.addCheckBox("Wald");
		features.addCheckBox("Wald");
		features.addCheckBox("Wald");
		features.addCheckBox("Wald");
		features.addCheckBox("Wald");
		panelFeatures.add(features , BorderLayout.WEST);
		
		BufferedImage img = mappy.getMap(new Point (0,0), 1);
		System.out.println (img);
		Graphics2D gfx = img.createGraphics();
		gfx.setColor(Color.BLACK); 
        gfx.drawLine(0,0,50,50); 
		gfx.drawImage(img,0,0,this);
		System.out.println(img.getWidth(this));
		JLabel imageLabel = new JLabel();
		panelMap.setDoubleBuffered(true);
		panelMap.paint(gfx);
		
		StatusBar sb = new StatusBar();
		sb.setInfo("Los geht's!");
		sb.setZoom(100);
		sb.setPosition(143,256);
		panelStatus.add(sb);
		
		pack();
		setVisible(true);
	}
	
	void setNewLookAndFeel()
	{
	  try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    SwingUtilities.updateComponentTreeUI(this); 
	  } catch( Exception e ) { e.printStackTrace(); }
	}
}