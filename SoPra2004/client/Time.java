/*
 * Created on 10.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

import java.awt.Font;
import java.text.DateFormat;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.swing.JLabel;
import javax.swing.plaf.metal.MetalBorders;

/**
 * @author DrRSatzteil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Time extends JLabel {
	Time(){
		setBorder(new MetalBorders.Flush3DBorder());
		setFont(new Font("Verdana", Font.PLAIN, 11));
		refresh();
		Wait w = new Wait ();
		w.start();
	}
	public void refresh(){
		GregorianCalendar now = new GregorianCalendar(TimeZone.getTimeZone("ECT"));
		DateFormat formater = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);
		now.setTime(now.getTime());
		String time = formater.format(now.getTime());
		setText(" " + time + " ");
	}
	
	class Wait extends Thread{
		public Wait(){
		}
		public void run(){
			while (true){
				try{
					Thread.sleep( 1000 );
				}
			catch (Exception e){}
			refresh();
			}
		}
	}
}