/*
 * Created on 15.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * @author ba008959
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MenuBar extends JMenuBar{
	public MenuBar(){
		
		// Create Menues
		JMenu menuFile=new JMenu("Datei");
		JMenu menuView=new JMenu("Anzeige");		
		JMenu menuHelp=new JMenu("Hilfe");
		JMenu menuSettings=new JMenu("Einstellungen");
		
		// Add Menues to MenuBar
		this.add(menuFile);
		this.add(menuView);
		this.add(menuHelp);
		
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
	
	
	//ActionListener
	menuItemExit.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	});
	
	}
}
