/*
 * Created on 18.01.2005
 *
 */
package client;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

/**
 * @author Nicolas Fritsch
 *
 */
public class Settings extends JFrame implements SettingsIF, LayersIF {
	String[] dbSettings=IOHandler.getSavedDbSettings();
	String dbUrl;
	Color[] savedColors=IOHandler.getSavedColorSettings();
	JTabbedPane jTabbedPane= new JTabbedPane();
	JPanel dbPanel=new JPanel();
	JPanel colorPanel=new JPanel();
	JPanel buttonPanel=new JPanel();
	JTextField urlField=new JTextField();
	JTextField portField=new JTextField();
	JTextField dbField=new JTextField();
	JTextField usrField=new JTextField();
	JPasswordField pwField=new JPasswordField();
	JComboBox dbTypBox=new JComboBox(new Object[] {"MySQL"});
	JButton saveBt=new JButton("Speichern");
	JButton cancelBt=new JButton("Abrechen");
	JButton resetBt=new JButton("Zurücksetzen");
	JTextField[] tf=new JTextField[IOHandler.getSavedColorSettings().length];
	JPanel[] jp=new JPanel[IOHandler.getSavedColorSettings().length];
	
	JLabel urlLabel= new JLabel("Datenbankadresse: ");
	JLabel portLabel= new JLabel("Port: ");
	JLabel dbLabel= new JLabel("Datenbankname: ");
	JLabel usrLabel= new JLabel("Benutzername: ");
	JLabel pwLabel= new JLabel("Passwort: ");
	JLabel dbTypLabel= new JLabel("Datenbank-Typ: ");
	JLabel spacer=new JLabel("                                     ");
	GridBagLayout layout = new GridBagLayout();

	public Settings (){
		super("Einstellungen");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			public void windowClosing (WindowEvent e){
				dispose();
			}
		});
		this.init();
	}
	

	private void init() {
		this.setSize(new Dimension(350,700));
		//dbPanel init
		dbPanel.setLayout(layout);
		
		LayoutManager.addComponent(dbPanel, layout, dbTypLabel, 0, 0, 1, 1, 0d, 0d);
		LayoutManager.addComponent(dbPanel, layout, dbTypBox, 1, 0, 1, 1, 0d, 0d);
		
		urlField.setText(dbSettings[0]);
		LayoutManager.addComponent(dbPanel, layout, urlLabel, 0, 1, 1, 1, 0d, 0d);
		LayoutManager.addComponent(dbPanel, layout, urlField, 1, 1, 1, 1, 0d, 0d);
		
		portField.setText(dbSettings[1]);
		LayoutManager.addComponent(dbPanel, layout, portLabel, 0, 2, 1, 1, 0d, 0d);
		LayoutManager.addComponent(dbPanel, layout, portField, 1, 2, 1, 1, 0d, 0d);
		
		dbField.setText(dbSettings[2]);
		LayoutManager.addComponent(dbPanel, layout, dbLabel, 0, 3, 1, 1, 0d, 0d);
		LayoutManager.addComponent(dbPanel, layout, dbField, 1, 3, 1, 1, 0d, 0d);
		
		usrField.setText(dbSettings[3]);
		LayoutManager.addComponent(dbPanel, layout, usrLabel, 0, 4, 1, 1, 0d, 0d);
		LayoutManager.addComponent(dbPanel, layout, usrField, 1, 4, 1, 1, 0d, 0d);
		
		pwField.setText(dbSettings[4]);
		LayoutManager.addComponent(dbPanel, layout, pwLabel, 0, 5, 1, 1, 0d, 0d);
		LayoutManager.addComponent(dbPanel, layout, pwField, 1, 5, 1, 1, 0d, 0d);
		
		LayoutManager.addComponent(dbPanel, layout, spacer, 1, 6, 1, 1, 0d, 0d);
		LayoutManager.addComponent(dbPanel, layout, saveBt, 0, 7, 1, 1, 0d, 0d);
		LayoutManager.addComponent(dbPanel, layout, cancelBt, 1, 7, 1, 1, 0d, 0d);
		
		//ColorPanel init
		colorPanel.setLayout(layout);
		int count=0;
		
		for(int i=0; i<savedColors.length;i++){
			JLabel label=new JLabel(ALLLAYERS[i]);
			jp[i]=new JPanel();
			jp[i].setBackground(savedColors[i]);
			tf[i]=new JTextField(savedColors[i].toString());
			final IDButton bt=new IDButton("Ändern", i);
			
			
			bt.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					changeLayerColor(bt.id);
					
				}
				
			});
			LayoutManager.addComponent(colorPanel, layout, label, 0, i, 1, 1, 0d, 0d);
			//LayoutManager.addComponent(colorPanel, layout, tf[i], 1, i, 1, 1, 0d, 0d);
			
			LayoutManager.addComponent(colorPanel, layout, jp[i], 1, i, 1, 1, 0d, 0d);
			LayoutManager.addComponent(colorPanel, layout, bt, 2, i, 1, 1, 0d, 0d);
			count=i;
		}
		LayoutManager.addComponent(colorPanel, layout, saveBt, 0, 22, 1, 1, 0d, 0d);
		LayoutManager.addComponent(colorPanel, layout, cancelBt, 1, count+1, 1, 1, 0d, 0d);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(jTabbedPane, BorderLayout.CENTER);
		buttonPanel.add(saveBt);
		buttonPanel.add(cancelBt);
		resetBt.setEnabled(false);
		buttonPanel.add(resetBt);
		
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		jTabbedPane.add(dbPanel, "Datenbank");
		jTabbedPane.add(colorPanel, "Farben");
		this.setVisible(true);
		dbUrl="jdbc:"+"mysql"+"://"+urlField.getText()+":"+portField.getText()+"/"+dbField.getText();
		
		System.out.println(dbUrl);
		saveBt.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
			String[] dbSet={urlField.getText(),portField.getText() ,dbField.getText(), usrField.getText(),pwField.getText() };	
			IOHandler.saveDbSettings(dbSet);
			
			Color[] colorSet=new Color[savedColors.length];
			for(int i=0; i<colorSet.length;i++){
				
				colorSet[i]=jp[i].getBackground();
			}
			IOHandler.saveColorSettings(colorSet);
			}
		});
		cancelBt.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
			dispose();
		}
		});
		resetBt.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
			init();
		}
		});

	}
	private void changeLayerColor(int i) {
		Color newColor = JColorChooser.showDialog(
	              null, "Wähle neue Farbe für die Ebene", jp[i].getBackground() );
	
	jp[i].setBackground(newColor);
	}

}
