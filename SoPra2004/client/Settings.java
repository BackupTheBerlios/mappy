/*
 * Created on 18.01.2005
 *
 */
package client;

import java.awt.BorderLayout;
import java.awt.Component;
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
public class Settings extends JFrame implements SettingsIF {
	
	String dbUrl;
	
	JTabbedPane jTabbedPane= new JTabbedPane();
	JPanel dbPanel=new JPanel();
	JPanel colorPanel=new JPanel();
	JTextField urlField=new JTextField();
	JTextField portField=new JTextField();
	JTextField dbField=new JTextField();
	JTextField usrField=new JTextField();
	JPasswordField pwField=new JPasswordField();
	JComboBox dbTypBox=new JComboBox(new Object[] {"MySQL"});
	
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
		this.setSize(new Dimension(400,300));
		//dbPanel init
		dbPanel.setLayout(layout);
		
		LayoutManager.addComponent(dbPanel, layout, dbTypLabel, 0, 0, 1, 1, 0d, 0d);
		LayoutManager.addComponent(dbPanel, layout, dbTypBox, 1, 0, 1, 1, 0d, 0d);
		
		urlField.setText(DB_URL);
		LayoutManager.addComponent(dbPanel, layout, urlLabel, 0, 1, 1, 1, 0d, 0d);
		LayoutManager.addComponent(dbPanel, layout, urlField, 1, 1, 1, 1, 0d, 0d);
		
		portField.setText(DB_PORT);
		LayoutManager.addComponent(dbPanel, layout, portLabel, 0, 2, 1, 1, 0d, 0d);
		LayoutManager.addComponent(dbPanel, layout, portField, 1, 2, 1, 1, 0d, 0d);
		
		dbField.setText(DB_NAME);
		LayoutManager.addComponent(dbPanel, layout, dbLabel, 0, 3, 1, 1, 0d, 0d);
		LayoutManager.addComponent(dbPanel, layout, dbField, 1, 3, 1, 1, 0d, 0d);
		
		usrField.setText(DB_USR);
		LayoutManager.addComponent(dbPanel, layout, usrLabel, 0, 4, 1, 1, 0d, 0d);
		LayoutManager.addComponent(dbPanel, layout, usrField, 1, 4, 1, 1, 0d, 0d);
		
		pwField.setText(DB_PW);
		LayoutManager.addComponent(dbPanel, layout, pwLabel, 0, 5, 1, 1, 0d, 0d);
		LayoutManager.addComponent(dbPanel, layout, pwField, 1, 5, 1, 1, 0d, 0d);
		
		LayoutManager.addComponent(dbPanel, layout, spacer, 1, 6, 1, 1, 0d, 0d);
		this.getContentPane().add(jTabbedPane);
		jTabbedPane.add(dbPanel, "Datenbank");
		jTabbedPane.add(colorPanel, "Farben");
		this.setVisible(true);
		dbUrl="jdbc:"+"mysql"+"://"+urlField.getText()+":"+portField.getText()+"/"+dbField.getText();
		System.out.println(dbUrl);
	}
	
	

}
