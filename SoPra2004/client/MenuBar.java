package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;

/**
 * The Menubar of the Gui with all its Menues and Items
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *	
 */
public class MenuBar extends JMenuBar{
	
	private Gui owner;

	public MenuBar(Gui owner){
		this.owner = owner;
		
		// Create Menues
		JMenu menuFile=new JMenu("Datei");
		JMenu menuView=new JMenu("Anzeige");		
		JMenu menuHelp=new JMenu("Hilfe");

		
		// Add Menues to MenuBar
		this.add(menuFile);
		this.add(menuView);
		this.add(menuHelp);
		
		// create Menu Items
		  // File
		JMenuItem menuItemExit = new JMenuItem("Beenden");
		JMenuItem menuItemSave = new JMenuItem("Speichern");
		  // View 
		JMenuItem menuItemZoom000 = new JMenuItem("Zoom   0%");
		JMenuItem menuItemZoom100 = new JMenuItem("Zoom 100%");
		JMenuItem menuItemZoom200 = new JMenuItem("Zoom 200%");
		JMenuItem menuItemZoom300 = new JMenuItem("Zoom 300%");
		  // Help
		JMenuItem menuItemAbout = new JMenuItem("Über Mappy...");
		  // Settings
		JMenuItem menuItemSettings = new JMenuItem("Einstellungen...");
		JMenuItem menuItemLanguage = new JMenuItem("Sprache");
		// add Menu Items
		  // Settings
		menuFile.add(menuItemSettings);
		  // File
		menuFile.add(menuItemSave);
		menuFile.addSeparator();
		menuFile.add(menuItemExit);
		  // Anzeige
		menuView.add(menuItemZoom000);
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
	menuItemSettings.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			new Settings();
		}
	});
	menuItemAbout.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			openAbout();
		}
	});
	menuItemZoom000.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e){
			setZoom(0);
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
	 * Sets the zoom to a defined value
	 * @param zoom	a int-Value as the new zoom-Value
	 */
	protected void setZoom(int zoom){
		owner.getSb().setZoom(zoom);
		owner.getZoomSlider().setValue(zoom);		
	}


	/**
	 * Shows the Close Dialog
	 */
	protected void closeProgram() {
		owner.showCloseDialog();
	}

	/**
	 * calls the different save methods
	 * @see IOHandler
	 */
	protected void saveSettings(){
		JProgressBar progress = owner.getProgress();
		progress.setMaximum(6);
		IOHandler.saveStartPoint(owner.getSb().getUpperLeft());
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

	/**
	 * Opens the About Dialog
	 */
	void openAbout(){
		AboutDialog about = new AboutDialog ((JFrame)this.getFocusCycleRootAncestor());
	}
}
