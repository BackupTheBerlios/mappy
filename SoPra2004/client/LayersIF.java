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
	public final String[] ALLLAYERS = {"Acker", 			//1
						   "Bachl‰ufe mit Schrift",			//2
						   "Baumgr¸n mit Schrift",			//3
						   "Brachland",						//4
						   "Industrie",						//5
						   "Grundriﬂ mit Schrift", 			//6
						   "H‰user",						//7
						   "Wohnen",						//8
						   "Park ",							//9
						   "Reliefbraun", 					//10
						   "Symbole ",						//11
						   "schwarzer Grundriﬂ freigst.",	//12
						   "Gew‰sser",						//13
						   "Staats-/Landstr.",				//14
						   "Autobahn-/Bundesstr.", 			//15
						   "Straﬂennamen",					//16
						   "schwarze Schrift", 				//17
						   "UTM-Gitter", 					//18
						   "Verwaltungsgrenzen", 			//19
						   "Wald", 							//20
						   "Weiﬂe Schrift", 				//21
						   "Wiese"} ;						//22
	public final Color[] LAYERCOLORS = {new Color(0xff8B4513),
										//new Color(0xff1C86EE),
										new Color(0xff0000ff),
										new Color(0xff008B45),
										new Color(0xffF0E68C),
										new Color(0xaaC1CDC1, true),
										new Color(0xffE0EEE0),

										new Color(0xaaCD5B45, true),
										new Color(0x66FF7256, true),

										new Color(0xff00CD66),
										new Color(0xff8B4500),
										new Color(0xff333333),
										new Color(0xff171717),
										new Color(0xff1C86EE),
										new Color(0xffFFD700),
										new Color(0xff104E8B),
										//new Color(0xff838B8B),
										new Color(0xff535B5B),
										new Color(0xff000000),
										new Color(0xffA2B5CD),
										new Color(0xffBC8F8F),
										new Color(0x90458B00, true),
										new Color(0x22ffffff, true),
										new Color(0x3590EE90, true)};
}
