/*
 * Created on 18.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package server;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JPanel;


/**
 * @author DrRSatzteil
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
class MapLabel extends JPanel{
	private ArrayList layerList;

	MapLabel(){
		setDoubleBuffered(true);
		setOpaque(false);
	}
	
	public void refresh(ArrayList layerList){
		this.layerList = layerList;
		repaint();
	}
	
	/*public Image filter(Image src){
		ImageFilter colorfilter =  new ColorChanger(Color.BLACK, Color.RED);//Einfach mal so zum Test
		ImageProducer imageprod = new FilteredImageSource(src.getSource() ,colorfilter );
		Image img =Toolkit.getDefaultToolkit().createImage( imageprod );
		System.out.println("Image gefiltert und erstellt");
		
		return src;//img; da es nicht funktioniert gebe ich einfach erstmal das alte zurück
	}*/
	public void paint(Graphics g){
		if (layerList == null){
		}
		else{
			if (!layerList.isEmpty()){
				ListIterator i = layerList.listIterator(0);
				while(i.hasNext()){
					Layer temp = (Layer)i.next();
					System.out.println(temp.getMap());
					g.drawImage(temp.getMap(), 0, 0, null);
				}
			}
		}
	}
}
