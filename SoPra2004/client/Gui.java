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
import java.awt.event.*;
import javax.swing.*;

import java.awt.Color;
import javax.swing.border.*;

import server.Mappy;


public class Gui extends JFrame {
	private Mappy mappy;
	private JLabel labelMap;
	
	
	public Gui(Mappy mappy){
		super("Mappy");
		this.mappy=new Mappy();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		System.out.print("Building new GUI...");
		this.setSize(500,500);
		
		MenuBar mb=new MenuBar();
		this.setJMenuBar(mb);
		
		this.setNewLookAndFeel();

			
		
		
		
		// ContentPane setup
		this.getContentPane().setLayout(new BorderLayout());
		JPanel panelFeatures=new JPanel();
		JPanel panelMap=new JPanel();
		JPanel panelStatus=new JPanel();
		panelStatus.setBorder(new EtchedBorder());
		panelStatus.setLayout(new FlowLayout(FlowLayout.LEFT,1,0));
		this.getContentPane().add(panelFeatures, BorderLayout.WEST);
		this.getContentPane().add(panelMap, BorderLayout.CENTER);
		this.getContentPane().add(panelStatus, BorderLayout.SOUTH);
		
		// Features setup
		
		panelFeatures.setLayout(new GridLayout(0,1));
		panelFeatures.setBorder(new EtchedBorder());
		JLabel labelFeatures=new JLabel("Kartenfunktionen");
		Features features=new Features();
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
		
		// Map setup
		// Hier Weiter Machen
		ImageIcon image=new ImageIcon("uniba.gif");//mappy.getMap();
		labelMap=new JLabel(image);
		panelMap.add(labelMap);
		Image img=mappy.getMap();//
		BufferedImage bi=new BufferedImage(300,300,BufferedImage.TYPE_INT_RGB);
		Graphics2D gfx=bi.createGraphics();
		gfx.drawImage(img,0,0,this);
		System.out.println(img.getWidth(this));
		//panelMap.paint(gfx);
		
		
		// Status setup
		StatusBar sb=new StatusBar();
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
	    SwingUtilities.updateComponentTreeUI(this ); 
	  } catch( Exception e ) { e.printStackTrace(); }
	}
	


}
