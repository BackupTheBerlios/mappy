/*
 * Created on 30.11.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

/**
 * @author ba008959
 * $Id: Gui.java,v 1.14 2004/12/19 19:49:29 drrsatzteil Exp $
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.metal.MetalBorders;

import server.Mappy;


public class Gui extends JFrame {
	private Mappy mappy;
	private String[] layer = {"Wald", "Strasse", "Gewerbegebiet",
			                  "Industriegebiet", "Wohnhäuser", "Schnaps"};
	private JList layers;
	private JScrollPane layersScrollPane;
	private JPanel status;
	private Image image;
	private JToolBar layerButtonBar;
	private JButton refresh;
	private JButton chooseAll;
	private JButton deselect;
	private ArrayList layerList;
	private Point upperLeft;
	private int[] layersToShow = {0, 0, 0, 0, 0, 0};
	private MapLabel map;
	
	public Gui(Mappy mappy){
		super("Mappy");
		this.mappy = mappy;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		MenuBar mb = new MenuBar();
		setJMenuBar(mb);
		
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
		
		
		
		layerButtonBar = new JToolBar(JToolBar.VERTICAL);
		layerButtonBar.setFloatable(false);
		refresh = new JButton ("Aktualisieren");
		refresh.setEnabled(false);
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
		layerList = mappy.getLayers(getSize(), upperLeft, layersToShow);
		
		map = new MapLabel(layerList);
		LayoutManager.addComponent(getContentPane(), layout, (Component)map, 1, 0, 1, 3, 1d, 1d);
		
		
		status = new JPanel();
		StatusBar sb = new StatusBar();
		sb.setInfo("Los geht's!");
		sb.setZoom(100);
		sb.setPosition(143,256);
		LayoutManager.addComponent(getContentPane(), layout, (Component)sb, 1, 3, 1, 1, 0d, 0d);
		
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
		layers.addSelectionInterval(0,5);
	}

	/**
	 * 
	 */
	protected void refreshAction() {
		refresh.setEnabled(false);
		
	}

	/**
	 * @param evt
	 * 
	 */
	protected void listValueChanged(ListSelectionEvent evt) {
		refresh.setEnabled(true);
		for (int i = 0; i < layersToShow.length; i++){
			if (layers.isSelectedIndex(i)){
				layersToShow[i] = 1;
			}
			else{
				layersToShow[i] = 0;
			}
		}
	}

	void setNewLookAndFeel()
	{
	  try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    SwingUtilities.updateComponentTreeUI(this ); 
	  } catch( Exception e ) { e.printStackTrace(); }
	}
	


}
