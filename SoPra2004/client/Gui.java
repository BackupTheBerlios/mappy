/*
 * Created on 30.11.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

/**
 * @author ba008959
 * $Id: Gui.java,v 1.31 2005/01/12 20:18:21 drrsatzteil Exp $
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalBorders;

import server.Mappy;


public class Gui extends JFrame implements LayersIF {
	private Mappy mappy;
	private String[] layer = ALLLAYERS;
	private JList layers;
	private JScrollPane layersScrollPane;
	private JPanel status;
	private Image image;
	private JToolBar layerButtonBar;
	private JButton refresh;
	private JButton chooseAll;
	private JButton deselect;
	private AbstractButton moveEast;
	private AbstractButton moveWest;
	private AbstractButton moveNorth;
	private JButton moveSouth;
	private Point upperLeft;
	private int[] layersToShow;
	private JPanel map;
	private JPanel mapPanel = new JPanel();
	private StatusBar sb;
	private JProgressBar progress = new JProgressBar(0,1);
	private Time date;
	
	
	
	public Gui(Mappy mappy){
		super("Mappy");
		this.mappy = mappy;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			public void windowClosing (WindowEvent e){
				showCloseDialog();
			}
		});
		// Build GUI
		System.out.print("Building new GUI...");
		setNewLookAndFeel();
		initComponents();

	}

	/**
	 * 
	 */
	private void initComponents() {
		
		GridBagLayout layout = new GridBagLayout();
		getContentPane().setLayout(layout);
		
		MenuBar mb = new MenuBar(this);
		
		layers = new JList(layer);
		layers.setFixedCellHeight(25);
		layers.setFixedCellWidth(150);
		layers.setBorder(new MetalBorders.Flush3DBorder());
		layers.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent evt){
				listValueChanged(evt);
			}
		});
		layersScrollPane = new JScrollPane(layers);
		layersScrollPane.setWheelScrollingEnabled(true);
		layersScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		LayoutManager.addComponent(getContentPane(), layout, (Component)layersScrollPane, 0, 0, 1, 1, 0d, 1d);
		
		Legend legend = new Legend();
		LayoutManager.addComponent(getContentPane(), layout, (Component)legend, 1, 0, 1, 1, 0d, 1d);
		
		layerButtonBar = new JToolBar(JToolBar.VERTICAL);
		layerButtonBar.setFloatable(false);
		refresh = new JButton ("Aktualisieren");
		refresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				refreshAction();
			}			
		});
		chooseAll = new JButton ("Alles wählen");
		chooseAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				chooseAllAction();
			}			
		});
		deselect = new JButton ("Auswahl aufheben");
		deselect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				deselectAction();
			}
		});
		LayoutManager.addComponent(getContentPane(), layout, (Component)refresh, 0, 1, 1, 1, 0d, 0d);
		LayoutManager.addComponent(getContentPane(), layout, (Component)chooseAll, 0, 2, 1, 1, 0d, 0d);
		LayoutManager.addComponent(getContentPane(), layout, (Component)deselect, 0, 3, 1, 1, 0d, 0d);
		
		
		upperLeft = IOHandler.getSavedStart();
		layersToShow = IOHandler.getSavedLayers();
		
		setSize(IOHandler.getSavedWindowSize());
		setLocation(IOHandler.getSavedWindowPosition());
		
		layers.setSelectedIndices(layersToShow);
		
		map = mappy.getMapLabel();
		map.setSize(IOHandler.getSavedMapSize());
		mapPanel.setLayout(new BorderLayout());
		moveEast=new DirectionButton("east.gif");
		moveWest=new DirectionButton("west.gif");
		moveNorth=new DirectionButton("north.gif");
		moveSouth=new DirectionButton("south.gif");
		moveEast.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				mappy.move("east");
			}
		});
		
		moveWest.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				mappy.move("west");
			}
		});
		
		moveNorth.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				mappy.move("north");
			}
		});
		
		moveSouth.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				mappy.move("south");
			}
		});
		mapPanel.add(moveEast, BorderLayout.EAST);
		mapPanel.add(moveWest, BorderLayout.WEST);
		mapPanel.add(moveNorth, BorderLayout.NORTH);
		mapPanel.add(moveSouth, BorderLayout.SOUTH);
		mapPanel.add(map, BorderLayout.CENTER);
		LayoutManager.addComponent(getContentPane(), layout, mapPanel, 2, 0, 2, 3, 1d, 1d);			
		status = new JPanel();
		if(layersToShow.length != 0){
			progress = new JProgressBar(0, layersToShow.length);
		}
		else{
			progress = new JProgressBar(0,1);
		}
		
		progress.setStringPainted(true);
		sb = new StatusBar(progress);
		sb.setInfo("Los geht's!");
		sb.setZoom(100);
		sb.setPosition(upperLeft.x,upperLeft.y);
		
		date = new Time();
		LayoutManager.addComponent(getContentPane(), layout, (Component)sb, 2, 3, 1, 1, 1d, 0d);
		LayoutManager.addComponent(getContentPane(), layout, (Component)date, 3, 3, 1, 1, 0d, 0d);
		
		if(map.getSize().height != 0 && map.getSize().width != 0){
			refreshAction();
		}
		setVisible(true);
	}
	
	/**
	 * 
	 */
	protected void showCloseDialog() {
		try{
			int decision = JOptionPane.showConfirmDialog(getGlassPane(),
			"Aktuelle Anzeige vor Beenden speichern?", "Programm Beenden", JOptionPane.YES_NO_OPTION);
			if(decision == JOptionPane.YES_OPTION){
				IOHandler.saveStartPoint(upperLeft);
				IOHandler.saveLayers(layersToShow);
				IOHandler.saveWindowSize(this);
				IOHandler.saveWindowPosition(this);
				IOHandler.saveMapSize(map);
				System.exit(0);
			}
			if(decision == JOptionPane.NO_OPTION){
				System.exit(0);
			}
		}
		catch (HeadlessException e) {}
	}

	/**
	 * 
	 */
	protected void deselectAction() {
		layers.clearSelection();		
	}

	/**
	 * 
	 */
	protected void chooseAllAction() {
		int select = ALLLAYERS.length - 1;
		layers.addSelectionInterval(0,select);
	}

	/**
	 * 
	 */
	protected void refreshAction(){
		refresh.setEnabled(false);
		Thread getData = new Thread(new Refresher(upperLeft, layersToShow, mappy, progress, sb));
		getData.start();
	}

	/**
	 * @param evt
	 * 
	 */
	protected void listValueChanged(ListSelectionEvent evt) {
		refresh.setEnabled(true);
		layersToShow = layers.getSelectedIndices();
		progress.setValue(0);
		progress.setMaximum(layersToShow.length);
	}
	
	
	void setNewLookAndFeel()
	{
	  try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    SwingUtilities.updateComponentTreeUI(this ); 
	  } catch( Exception e ) { e.printStackTrace(); }
	}
	
	Point getUpperLeft(){
		return upperLeft;
	}
	int[] getLayers(){
		return layersToShow;
	}

	/**
	 * @return
	 */
	public JPanel getMap() {
		return map;
	}
}
