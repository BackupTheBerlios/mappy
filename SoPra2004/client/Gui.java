/*
 * Created on 30.11.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

/**
 * @author Softwarepraktikum 2004/05 Gruppe 2
 */
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;

import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.plaf.metal.MetalBorders;

import server.Mappy;


public class Gui extends JFrame implements LayersIF{
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
	private JButton moveEast;
	private JButton moveWest;
	private JButton moveNorth;
	private JButton moveSouth;
	private Point upperLeft;
	private int[] layersToShow;
	private JPanel map;
	private JPanel mapPanel;
	private StatusBar sb;
	private JProgressBar progress = new JProgressBar(0,1);
	private Time date;
	private JSlider zoomSlider;
	private JToolBar zoomToolBar;
	private JLabel zoomLabel;
	private GridBagLayout layout;
	private Dialog wait;
	private URL clickSound;
	private Clip btClick;
	protected MapPopup menu;	
	
	public Gui(Mappy mappy){
		super("Mappy");
		ImageIcon icon=new ImageIcon(getClass().getResource("/images/mappy_icon_15x15.gif"));
		this.setIconImage(icon.getImage());
		this.mappy = mappy;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			public void windowClosing (WindowEvent e){
				showCloseDialog();
			}
		});
		//Build GUI
		System.out.println("Building new GUI...");
		setNewLookAndFeel();
		initComponents();
	}

	/**
	 * 
	 */
	private void initComponents(){
		
		layout = new GridBagLayout();
		getContentPane().setLayout(layout);
		
		MenuBar mb = new MenuBar(this);
		clickSound=getClass().getResource("/sounds/Blip.wav");
		try{
			btClick = AudioPlayer.getStream(clickSound);
		}
		catch(SoundDisabledException e){
			btClick = null;
			System.err.println(e.getMessage());
		}
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
		refresh = new SelectionButton (getClass().getResource("/images/refresh.gif"), getClass().getResource("/images/refreshOver.gif"));
		refresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				click();
				refreshAction();
			}			
		});
		chooseAll = new SelectionButton (getClass().getResource("/images/chooseAll.gif"), getClass().getResource("/images/chooseAllOver.gif"));
		chooseAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				click();
				chooseAllAction();
			}			
		});
		deselect = new SelectionButton (getClass().getResource("/images/reset.gif"), getClass().getResource("/images/resetOver.gif"));
		deselect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				click();
				deselectAction();
			}
		});
		zoomToolBar = new JToolBar();
		zoomToolBar.setFloatable(false);
		zoomLabel = new JLabel("Zoom: ");
		zoomLabel.setFont(new Font("Verdana", Font.PLAIN, 11));
		zoomSlider = new JSlider();
		zoomSlider.setMinimum(0);
		zoomSlider.setMaximum(300);
		zoomSlider.setValue(IOHandler.getSavedZoom());
		zoomSlider.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent ce){
				zoomSliderChanged();
			}
		});
		zoomToolBar.add(zoomLabel);
		zoomToolBar.add(zoomSlider);
		zoomToolBar.setBorder(new MetalBorders.Flush3DBorder());
		
		LayoutManager.addComponent(getContentPane(), layout, (Component)refresh, 0, 1, 1, 1, 0d, 0d);
		LayoutManager.addComponent(getContentPane(), layout, (Component)chooseAll, 0, 2, 1, 1, 0d, 0d);
		LayoutManager.addComponent(getContentPane(), layout, (Component)deselect, 0, 3, 1, 1, 0d, 0d);
		LayoutManager.addComponent(getContentPane(), layout, (Component)zoomToolBar, 2, 2, 1, 1, 0d, 0d);
		
		upperLeft = IOHandler.getSavedStart();
		layersToShow = IOHandler.getSavedLayers();
		
		setSize(IOHandler.getSavedWindowSize());
		setLocation(IOHandler.getSavedWindowPosition());
		
		layers.setSelectedIndices(layersToShow);
		
		menu = new MapPopup("Punktverwaltung", this);
		map = mappy.getMapLabel();
		map.setDoubleBuffered(true);
		map.setSize(IOHandler.getSavedMapSize());
		map.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent arg0){
				menu.setPinPoint(new Point(arg0.getX(), arg0.getY()));
				menu.show(arg0.getComponent(), arg0.getX(), arg0.getY());
			}
			public void mousePressed(MouseEvent arg0) {
			}
			public void mouseReleased(MouseEvent arg0) {
			}
			public void mouseEntered(MouseEvent arg0) {
			}
			public void mouseExited(MouseEvent arg0) {
			}
		});
		
		moveEast = new DirectionButton(getClass().getResource("/images/east.gif"));
		moveWest = new DirectionButton(getClass().getResource("/images/west.gif"));
		moveNorth = new DirectionButton(getClass().getResource("/images/north.gif"));
		moveSouth = new DirectionButton(getClass().getResource("/images/south.gif"));
		moveEast.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				click();
				upperLeft = sb.getUpperLeft();
				upperLeft.x = upperLeft.x + 300;
				setXY(upperLeft.x, upperLeft.y);
				progress.setValue(0);
				refreshAction();
			}
		});
		
		moveWest.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				click();
				upperLeft = sb.getUpperLeft();
				upperLeft.x = upperLeft.x - 300;
				setXY(upperLeft.x, upperLeft.y);
				progress.setValue(0);
				refreshAction();
			}
		});
		
		moveNorth.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				click();
				upperLeft = sb.getUpperLeft();
				upperLeft.y = upperLeft.y - 300;
				setXY(upperLeft.x, upperLeft.y);
				progress.setValue(0);
				refreshAction();
			}
		});
		moveSouth.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				click();
				upperLeft = sb.getUpperLeft();
				upperLeft.y = upperLeft.y + 300;
				setXY(upperLeft.x, upperLeft.y);
				progress.setValue(0);
				
				refreshAction();
			}
		});
		mapPanel = new JPanel();
		mapPanel.setOpaque(false);
		mapPanel.setBorder(new MetalBorders.Flush3DBorder());
		mapPanel.setLayout(new BorderLayout());
		mapPanel.setBackground(new Color(0,0,0));
		mapPanel.add(moveEast, BorderLayout.EAST);
		mapPanel.add(moveWest, BorderLayout.WEST);
		mapPanel.add(moveNorth, BorderLayout.NORTH);
		mapPanel.add(moveSouth, BorderLayout.SOUTH);
		mapPanel.add(map, BorderLayout.CENTER);
		LayoutManager.addComponent(getContentPane(), layout, mapPanel, 1, 0, 2, 2, 1d, 1d);
		status = new JPanel();
		if(layersToShow.length != 0){
			progress = new JProgressBar(0, layersToShow.length);
		}
		else{
			progress = new JProgressBar(0,1);
		}
		
		progress.setStringPainted(true);
		sb = new StatusBar(progress, this);
		sb.setInfo("Los geht's!");
		sb.setZoom(zoomSlider.getValue());
		sb.setXY(upperLeft.x, upperLeft.y);
		
		date = new Time();
		LayoutManager.addComponent(getContentPane(), layout, (Component)sb, 1, 2, 1, 2, 1d, 0d);
		LayoutManager.addComponent(getContentPane(), layout, (Component)date, 2, 3, 1, 1, 0d, 0d);
		
		addComponentListener(new ComponentListener(){
			public void componentHidden(ComponentEvent arg0){
			}
			public void componentMoved(ComponentEvent arg0){
			}
			public void componentResized(ComponentEvent arg0){
				if((getSize().width < 850) || (getSize().height < 600)){
					setSize(850,600);
				}
				else{
					int decision = JOptionPane.showConfirmDialog
					(null, "Die Fenstergröße wurde geändert. Wollen sie die Karte neu laden?",
					"Größe geändert", JOptionPane.YES_NO_OPTION);
					if(decision == JOptionPane.YES_OPTION){
						refreshAction();
					}					
				}
			}
			public void componentShown(ComponentEvent arg0){
			}
		});
		
		setVisible(true);
		
		
		wait = new Dialog(this, "Bitte Warten", true);
		wait.setVisible(false);
		Label waitLabel = new Label("Bitte haben sie einen Augenblick Geduld", Label.CENTER);
		waitLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
		wait.add(waitLabel);
		wait.setUndecorated(true);
		wait.pack();
		
		if(map.getSize().height != 0 && map.getSize().width != 0){
			refreshAction();
		}
	}
	

	/**
	 * @param x
	 * @param y
	 */
	protected void setXY(int x, int y){
		sb.setXY(x,y);
	}

	/**
	 * 
	 */
	protected void zoomSliderChanged(){
		if(!zoomSlider.getValueIsAdjusting()){
			System.out.println("Zoomwert: " + zoomSlider.getValue());
			progress.setValue(0);
			refresh.setEnabled(true);
		}
		else{
			sb.setZoom(zoomSlider.getValue());
		}
	}

	/**
	 * 
	 */
	protected void showCloseDialog() {
		try{
			int decision = JOptionPane.showConfirmDialog(getGlassPane(),
			"Aktuelle Anzeige vor Beenden speichern?", "Programm Beenden", JOptionPane.YES_NO_OPTION);
			if(decision == JOptionPane.YES_OPTION){
				upperLeft = sb.getUpperLeft();
				IOHandler.saveStartPoint(upperLeft);
				IOHandler.saveLayers(layersToShow);
				IOHandler.saveWindowSize(this);
				IOHandler.saveWindowPosition(this);
				IOHandler.saveMapSize(map);
				IOHandler.saveZoomValue(zoomSlider.getValue());
				if(mappy.closeDB()){
					System.exit(0);
				}
				else{
					System.err.println("Not able to close DB-Connection. Exiting anyway");
					System.exit(0);
				}
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
	protected void deselectAction(){
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
		Point location = getLocation();
		Dimension size = getSize();
		int x = location.x + size.width/2 - wait.getSize().width/2 + 65;
		int y = location.y + size.height/2 - wait.getSize().height/2 - 20;
		wait.setLocation(x, y);
		progress.setValue(0);
		refresh.setEnabled(false);
		Thread getData = new Thread(new Refresher(this));
		getData.start();
		wait.setVisible(true);
	}

	/**
	 * @param evt
	 * 
	 */
	protected void listValueChanged(ListSelectionEvent evt) {
		refresh.setEnabled(true);
		layersToShow = layers.getSelectedIndices();
		progress.setValue(0);
		if(layersToShow.length > 0){
			progress.setMaximum(layersToShow.length);
		}
		else{
			progress.setMaximum(1);
		}
	}
	
	void setNewLookAndFeel()
	{
	  try {
	    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    SwingUtilities.updateComponentTreeUI(this ); 
	  } catch(Exception e){e.printStackTrace();}
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
	public JLabel getStatusPanel(){
		return sb.getInfo();
	}
	public JProgressBar getProgress(){
		return progress;
	}
	/**
	 * @return Returns the sb.
	 */
	public StatusBar getSb() {
		return sb;
	}
	/**
	 * @return Returns the zoomSlider.
	 */
	public JSlider getZoomSlider() {
		return zoomSlider;
	}
	/**
	 * @param upperLeft The upperLeft to set.
	 */
	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
	}
	public Color[] getLayerColors(){
		try{
			return IOHandler.getSavedColorSettings();
		}
		catch (Exception e){
			return LAYERCOLORS;
		}
	}
	public Color[] getLayerColorsAlpha(){
		return LAYERCOLORS;
	}
	/**
	 * @return Returns the mappy.
	 */
	public Mappy getMappy() {
		return mappy;
	}

	/**
	 * @return
	 */
	public Dialog getWait() {
		return wait;
	}

	/**
	 * 
	 */
	public void setRefreshEnabled(boolean b) {
		refresh.setEnabled(b);
	}
	
	/**
	 * Plays the clicksound
	 * 
	 */
	private void click(){
		if(btClick!=null){
		btClick.setFramePosition(0);
		btClick.start();
		}
	}
}
