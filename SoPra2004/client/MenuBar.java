/*
 * Created on 15.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;

/**
 * @author ba008959
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MenuBar extends JMenuBar{
	
	private Gui owner;

	public MenuBar(Gui owner){
		this.owner = owner;
		
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
		JMenuItem menuItemAbout=new JMenuItem("�ber Mappy...");
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
			closeProgram();
		}
	});
	menuItemSave.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			saveSettings();
		}
	});
	menuItemAbout.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			openAbout();
		}
	});
	menuItemZoom100.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			setZoom(100);
		}
	});
	menuItemZoom200.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			setZoom(200);
		}
	});
	menuItemZoom300.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			setZoom(300);
		}
	});
	owner.setJMenuBar(this);
	}
	
	/**
	 * 
	 */
	protected void setZoom(int zoom) {
		owner.getZoomSlider().setValue(zoom);		
	}

	/**
	 * 
	 */
	protected void closeProgram() {
		owner.showCloseDialog();
	}

	/**
	 * 
	 */
	protected void saveSettings(){
		JProgressBar progress = owner.getProgress();
		progress.setMaximum(6);
		IOHandler.saveStartPoint(owner.getUpperLeft());
		progress.setValue(1);
		IOHandler.saveLayers(owner.getLayers());
		progress.setValue(2);
		IOHandler.saveWindowSize(owner);
		progress.setValue(3);
		IOHandler.saveWindowPosition(owner);
		progress.setValue(4);
		IOHandler.saveMapSize(owner.getMap());
		progress.setValue(5);
		IOHandler.saveZoomValue(owner.getZoomSlider().getValue());
		progress.setValue(6);
		owner.getStatusPanel().setText("Gespeichert!");
	}

	void openAbout(){
		AboutDialog about = new AboutDialog ((JFrame)this.getFocusCycleRootAncestor());
	}
}
