/*
 * Created on 03.01.2005
 *
 */
package client;

import java.awt.Color;

/**
 * @author Nicolas Fritsch
 *	
 *	contains the Layers as Constants
 */
public interface LayersIF {
	public final String[] ALLLAYERS = {"Acker", 	//1
						   "Bachl‰ufe mit Schrift",	//2
						   "Baumgr¸n mit Schrift",	//3
						   "Brachland",				//4
						   "Industrie",				//5
						   "Grundriﬂ mit Schrift", 	//6
						   "H‰user",				//7
						   "Wohnen",				//8
						   "Park ",					//9
						   "Reliefbraun", 			//10
						   "Symbole ",						//11
						   "schwarzer Grundriﬂ freigst.",	//12
						   "Gew‰sser",				//13
						   "Staats-/Landstr.",		//14
						   "Autobahn-/Bundesstr.", 	//15
						   "Straﬂennamen",			//16
						   "schwarze Schrift", 		//17
						   "UTM-Gitter", 			//18
						   "Verwaltungsgrenzen", 	//19
						   "Wald", 					//20
						   "Weiﬂe Schrift", 		//21
						   "Wiese"} ;				//22
	public final Color[] LAYERCOLORS = {new Color(178,126,0),
										new Color(1,255,1),
										new Color(100,170,50),
										new Color(228,241,0),
										new Color(200,200,200),
										new Color(1,1,255),
										new Color(100,100,100),
										new Color(100,100,100),
										new Color(100,170,100),
										new Color(100,100,100),
										new Color(100,100,100),
										new Color(100,100,100),
										new Color(100,100,100),
										new Color(100,100,100),
										new Color(100,100,100),
										new Color(100,100,100),
										new Color(100,100,100),
										new Color(100,100,100),
										new Color(100,100,100),
										new Color(100,170,125),
										new Color(100,100,100),
										new Color(70,170,0)};
}
