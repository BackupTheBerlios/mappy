/*
 * Created on 30.11.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

/**
 * @author ba008959
 * $Id: Gui.java,v 1.22 2005/01/10 19:19:35 drrsatzteil Exp $
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private Point upperLeft;
	private int[] layersToShow;
	private JLabel map;
	private StatusBar sb;
	private JProgressBar progress;
	
	public Gui(Mappy mappy){
		super("Mappy");
		this.mappy = mappy;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Build GUI
		System.out.print("Building new GUI...");
		setSize(500,500);
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
		LayoutManager.addComponent(getContentPane(), layout, (Component)refresh, 0, 1, 2, 1, 0d, 0d);
		LayoutManager.addComponent(getContentPane(), layout, (Component)chooseAll, 0, 2, 2, 1, 0d, 0d);
		LayoutManager.addComponent(getContentPane(), layout, (Component)deselect, 0, 3, 2, 1, 0d, 0d);
		
		
		upperLeft = IOHandler.getSavedStart();
		layersToShow = IOHandler.getSavedLayers();
		
		layers.setSelectedIndices(layersToShow);
		
		map = mappy.getMapLabel();
		refreshAction();
		LayoutManager.addComponent(getContentPane(), layout, (Component)map, 2, 0, 1, 3, 1d, 1d);
		
		
		status = new JPanel();
		progress = new JProgressBar(0, layersToShow.length);
		progress.setStringPainted(true);
		sb = new StatusBar(progress);
		sb.setInfo("Los geht's!");
		sb.setZoom(100);
		sb.setPosition(upperLeft.x,upperLeft.y);
		LayoutManager.addComponent(getContentPane(), layout, (Component)sb, 2, 3, 1, 1, 1d, 0d);
		
		setVisible(true);
		
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
		Thread getData = new Thread(new Refresher(upperLeft, layersToShow, mappy, progress));
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
}
