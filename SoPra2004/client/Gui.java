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
		System.out.print("Building new GUI...");
		this.setSize(500,500);
		
		// Create new MenuBar
		JMenuBar menuBar=new JMenuBar();
		
		// Add MenuBar to Frame
		this.setJMenuBar(menuBar);
		
		// Create Menues
		JMenu menuFile=new JMenu("Datei");
		JMenu menuView=new JMenu("Anzeige");		
		JMenu menuHelp=new JMenu("Hilfe");
		JMenu menuSettings=new JMenu("Einstellungen");
		
		// Add Menues to MenuBar
		menuBar.add(menuFile);
		menuBar.add(menuView);
		menuBar.add(menuHelp);
		
		// create Menu Items
		  // File
		JMenuItem menuItemExit=new JMenuItem("Beenden");
		JMenuItem menuItemSave=new JMenuItem("Speichern");
		  // View 
		JMenuItem menuItemZoom100=new JMenuItem("Zoom 100%");
		JMenuItem menuItemZoom200=new JMenuItem("Zoom 200%");
		JMenuItem menuItemZoom300=new JMenuItem("Zoom 300%");
		  // Help
		JMenuItem menuItemAbout=new JMenuItem("Über Mappy...");
		  // Settings
		JMenuItem menuItemDb=new JMenuItem("Datenbank");
		JMenuItem menuItemLanguage= new JMenuItem("Sprache");
		// add Menu Items
		  // Settings
		menuSettings.add(menuItemDb);
		menuSettings.add(menuItemLanguage);
		  // File
		menuFile.add(menuSettings);
		menuFile.add(menuItemSave);
		menuFile.addSeparator();
		menuFile.add(menuItemExit);
		  // Anzeige
		menuView.add(menuItemZoom100);
		menuView.add(menuItemZoom200);
		menuView.add(menuItemZoom300);
		  // Hilfe
		menuHelp.add(menuItemAbout);
		
		this.setNewLookAndFeel();

			
		
		menuItemExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		
		// ContentPane setup
		this.getContentPane().setLayout(new BorderLayout());
		JPanel panelFeatures=new JPanel();
		JPanel panelMap=new JPanel();
		JPanel panelStatus=new JPanel();
		Border etchedBdr = BorderFactory.createEtchedBorder(Color.BLACK, Color.BLACK);
		panelStatus.setBorder(etchedBdr);
		this.getContentPane().add(panelFeatures, BorderLayout.WEST);
		this.getContentPane().add(panelMap, BorderLayout.CENTER);
		this.getContentPane().add(panelStatus, BorderLayout.SOUTH);
		
		// Features setup
		
		panelFeatures.setLayout(new GridLayout(0,1));
		panelFeatures.setBackground(Color.BLUE);
		JLabel labelFeatures=new JLabel("Kartenfunktionen");
		panelFeatures.add(labelFeatures);
		
		
		// Map setup
		// Hier Weiter Machen
		ImageIcon image=mappy.getMap();
		labelMap=new JLabel(image);
		panelMap.add(labelMap);
		
		
		// Status setup
		
		JLabel labelDbStatus= new JLabel("Sie sind mit keiner Datenbank verbunden!");
		panelStatus.setBackground(Color.RED);
		panelStatus.add(labelDbStatus);
		
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
