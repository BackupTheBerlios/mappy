//javadoc ready
/*
 * Created on 18.01.2005
 *
 */
package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.sound.sampled.Clip;
import javax.swing.*;

/**
 * The class Settings builds a new Window with 3 Tabs. There is a tab for the
 * following settings: - Datenbank: Connection information of the database -
 * Farben: Colormanagement of the map layers - Sonstige: various settings
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *  
 */
public class Settings extends JFrame implements SettingsIF, LayersIF {
	/**
	 * The database settings. [0]url [1]port [2]database [3]user [4]password
	 */
	String[] dbSettings = IOHandler.getSavedDbSettings();

	/**
	 * the Url of the db
	 */
	String dbUrl;

	/**
	 * Colors of the layers
	 */
	Color[] savedColors = IOHandler.getSavedColorSettings();

	/**
	 * Tabbed Pane
	 */
	JTabbedPane jTabbedPane = new JTabbedPane();

	/**
	 * Panel for database settings
	 */
	JPanel dbPanel = new JPanel();

	/**
	 * Panel for color settings
	 */
	JPanel colorPanel = new JPanel();

	/**
	 * Panel for various settings
	 */
	JPanel sonstEinst = new JPanel();

	/**
	 * Panel for the buttons
	 */
	JPanel buttonPanel = new JPanel();

	/**
	 * enabel/disable sound
	 */
	JCheckBox soundCheckBox = new JCheckBox("Ton aus");

	/**
	 * database url input
	 */
	JTextField urlField = new JTextField();

	/**
	 * database port input
	 */
	JTextField portField = new JTextField();

	/**
	 * database input
	 */
	JTextField dbField = new JTextField();

	/**
	 * database user input
	 */
	JTextField usrField = new JTextField();

	/**
	 * database password input
	 */
	JPasswordField pwField = new JPasswordField();

	/**
	 * box with db-types
	 */
	JComboBox dbTypBox = new JComboBox(new Object[] { "MySQL" });

	/**
	 * save Button
	 */
	JButton saveBt = new JButton("Speichern");

	/**
	 * cancel Button
	 */
	JButton cancelBt = new JButton("Abrechen");

	/**
	 * reset Button
	 */
	JButton resetBt = new JButton("Zurücksetzen");

	/**
	 * Panels for color preview
	 */
	JPanel[] jp = new JPanel[IOHandler.getSavedColorSettings().length];

	/**
	 * Comment for <code>dbScrollPane</code>
	 */
	JScrollPane dbScrollPane;

	/**
	 * Comment for <code>colorScrollPane</code>
	 */
	JScrollPane colorScrollPane;

	/**
	 * Comment for <code>variousScrollPane</code>
	 */
	JScrollPane variousScrollPane;

	/**
	 * Label for input Field url
	 */
	JLabel urlLabel = new JLabel("Datenbankadresse: ");

	/**
	 * Label for input Field port
	 */
	JLabel portLabel = new JLabel("Port: ");

	/**
	 * Label for input Field database
	 */
	JLabel dbLabel = new JLabel("Datenbankname: ");

	/**
	 * Label for input Field user
	 */
	JLabel usrLabel = new JLabel("Benutzername: ");

	/**
	 * Label for input Field password
	 */
	JLabel pwLabel = new JLabel("Passwort: ");

	/**
	 * Label for input ComboBox db type
	 */
	JLabel dbTypLabel = new JLabel("Datenbank-Typ: ");

	/**
	 * just a string spacer for layout
	 */
	JLabel spacer = new JLabel("                                     ");

	/**
	 * Layout
	 */
	GridBagLayout layout = new GridBagLayout();

	private URL clickSound;

	private Clip btClick;

	private Gui owner;

	public Settings(Gui owner) {
		super("Einstellungen");
		this.owner = owner;
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		this.setIconImage(new ImageIcon(getClass().getResource(
				"/images/mappy_icon_15x15.gif")).getImage());
		this.init();
	}

	/**
	 * initializes the components of the JFrame Settings
	 */
	private void init() {

		this.setSize(new Dimension(350, 700));
		//dbPanel init
		dbPanel.setLayout(layout);

		LayoutManager.addComponent(dbPanel, layout, dbTypLabel, 0, 0, 1, 1, 0d,
				0d);
		LayoutManager.addComponent(dbPanel, layout, dbTypBox, 1, 0, 1, 1, 0d,
				0d);

		urlField.setText(dbSettings[0]);
		LayoutManager.addComponent(dbPanel, layout, urlLabel, 0, 1, 1, 1, 0d,
				0d);
		LayoutManager.addComponent(dbPanel, layout, urlField, 1, 1, 1, 1, 0d,
				0d);

		portField.setText(dbSettings[1]);
		LayoutManager.addComponent(dbPanel, layout, portLabel, 0, 2, 1, 1, 0d,
				0d);
		LayoutManager.addComponent(dbPanel, layout, portField, 1, 2, 1, 1, 0d,
				0d);

		dbField.setText(dbSettings[2]);
		LayoutManager
				.addComponent(dbPanel, layout, dbLabel, 0, 3, 1, 1, 0d, 0d);
		LayoutManager
				.addComponent(dbPanel, layout, dbField, 1, 3, 1, 1, 0d, 0d);

		usrField.setText(dbSettings[3]);
		LayoutManager.addComponent(dbPanel, layout, usrLabel, 0, 4, 1, 1, 0d,
				0d);
		LayoutManager.addComponent(dbPanel, layout, usrField, 1, 4, 1, 1, 0d,
				0d);

		pwField.setText(dbSettings[4]);
		pwField.addFocusListener(new FocusListener() {
			boolean messageNotShown = true;

			public void focusGained(FocusEvent arg0) {
				if (messageNotShown) {
					Toolkit.getDefaultToolkit().beep();
					JOptionPane
							.showMessageDialog(
									null,
									"Passwörter auf der Festplatte"
											+ " zu speichern ist unsicher und geschieht ausdrücklich"
											+ " auf eigene Gefahr!", "Warnung",
									JOptionPane.WARNING_MESSAGE);
					messageNotShown = false;
				}

			}

			public void focusLost(FocusEvent arg0) {
			}
		});
		LayoutManager
				.addComponent(dbPanel, layout, pwLabel, 0, 5, 1, 1, 0d, 0d);
		LayoutManager
				.addComponent(dbPanel, layout, pwField, 1, 5, 1, 1, 0d, 0d);

		LayoutManager.addComponent(dbPanel, layout, spacer, 1, 6, 1, 1, 0d, 0d);
		LayoutManager.addComponent(dbPanel, layout, saveBt, 0, 7, 1, 1, 0d, 0d);
		LayoutManager.addComponent(dbPanel, layout, cancelBt, 1, 7, 1, 1, 0d,
				0d);

		//ColorPanel init
		colorPanel.setLayout(layout);
		int count = 0;

		clickSound = getClass().getResource("/Blip.wav");
		try {
			btClick = AudioPlayer.getStream(clickSound);
		} catch (SoundDisabledException e1) {
			System.err.println(e1.getMessage());
		}

		for (int i = 0; i < savedColors.length; i++) {
			JLabel label = new JLabel(ALLLAYERS[i]);
			jp[i] = new JPanel();
			jp[i].setBackground(savedColors[i]);

			final IDButton bt = new IDButton("Ändern", i);

			bt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (btClick != null) {
						btClick.setFramePosition(0);
						btClick.start();
					}
					changeLayerColor(bt.id);
				}

			});
			LayoutManager.addComponent(colorPanel, layout, label, 0, i, 1, 1,
					0d, 0d);
			LayoutManager.addComponent(colorPanel, layout, jp[i], 1, i, 1, 1,
					0d, 0d);
			LayoutManager.addComponent(colorPanel, layout, bt, 2, i, 1, 1, 0d,
					0d);
			count = i;
		}
		LayoutManager.addComponent(colorPanel, layout, saveBt, 0, 22, 1, 1, 0d,
				0d);
		LayoutManager.addComponent(colorPanel, layout, cancelBt, 1, count + 1,
				1, 1, 0d, 0d);

		//sonstEinst init
		sonstEinst.setLayout(layout);
		soundCheckBox.setSelected(IOHandler.getSavedSoundSettings());
		LayoutManager.addComponent(sonstEinst, layout, soundCheckBox, 1, 0, 1,
				1, 0d, 0d);

		//general init
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(jTabbedPane, BorderLayout.CENTER);
		buttonPanel.add(saveBt);
		buttonPanel.add(cancelBt);
		resetBt.setEnabled(IOHandler.changesExist());
		buttonPanel.add(resetBt);

		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		dbScrollPane = new JScrollPane(dbPanel);
		jTabbedPane.add(dbScrollPane, "Datenbank");
		colorScrollPane = new JScrollPane(colorPanel);
		jTabbedPane.add(colorScrollPane, "Farben");
		variousScrollPane = new JScrollPane(sonstEinst);
		jTabbedPane.add(variousScrollPane, "Sonstiges");

		dbUrl = "jdbc:" + "mysql" + "://" + urlField.getText() + ":"
				+ portField.getText() + "/" + dbField.getText();

		//Window Properties
		this.pack();
		this.setVisible(true);
		//Handler
		saveBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click();
				String[] dbSet = { urlField.getText(), portField.getText(),
						dbField.getText(), usrField.getText(),
						new String(pwField.getPassword()) };
				IOHandler.saveDbSettings(dbSet);

				owner.openNewDB(dbSet);

				Color[] colorSet = new Color[savedColors.length];
				for (int i = 0; i < colorSet.length; i++) {
					colorSet[i] = jp[i].getBackground();
				}
				IOHandler.saveColorSettings(colorSet);
				//Checks for the soundSettings and instantly disables sounds
				// for this frame
				boolean dontPlaySounds = soundCheckBox.isSelected();
				IOHandler.saveSoundSettings(dontPlaySounds);
				if (dontPlaySounds) {
					btClick = null;
				} else {
					try {
						btClick = AudioPlayer.getStream(clickSound);
					} catch (SoundDisabledException e1) {
						btClick = null;
						System.err.println(e1.getMessage());
					}
				}
				resetBt.setEnabled(true);
			}
		});
		cancelBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click();
				dispose();
			}
		});
		resetBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IOHandler.deleteColorSettings();
				savedColors = IOHandler.getSavedColorSettings();
				IOHandler.deleteDBSettings();
				dbSettings = IOHandler.getSavedDbSettings();
				owner.openNewDB(dbSettings);
				for (int i = 0; i < jp.length; i++) {
					jp[i].setBackground(savedColors[i]);
				}
				urlField.setText(dbSettings[0]);
				portField.setText(dbSettings[1]);
				dbField.setText(dbSettings[2]);
				usrField.setText(dbSettings[3]);
				pwField.setText(dbSettings[4]);
				IOHandler.deleteSoundSettings();
				soundCheckBox.setSelected(IOHandler.getSavedSoundSettings());
				try {
					btClick = AudioPlayer.getStream(clickSound);
				} catch (SoundDisabledException e1) {
					System.err.println(e1.getMessage());
				}
				resetBt.setEnabled(false);
			}
		});
		this.addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				this_componentResized();
			}
		});

	}

	/**
	 * changes the Color of the JPanels next to the layer name
	 * 
	 * @param i
	 *            i is the layer level
	 */
	private void changeLayerColor(int i) {
		Color newColor = JColorChooser.showDialog(null,
				"Wähle neue Farbe für die Ebene", jp[i].getBackground());

		if (newColor != null)
			jp[i].setBackground(newColor);
	}

	/**
	 * Gives the JFrame a minimum size
	 *  
	 */
	private void this_componentResized() {
		Dimension size = this.getSize();
		if (size.width < 350)
			size.width = 350;
		if (size.height < 550)
			size.height = 550;
		this.setSize(size);

	}

	private void click() {
		if (btClick != null) {
			btClick.setFramePosition(0);
			btClick.start();
		}
	}

}