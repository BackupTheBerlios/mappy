/*
 * Created on 19.01.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package client;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 * @author DrRSatzteil
 *
 *$Id: AudioPlayer.java,v 1.1 2005/01/19 21:28:26 drrsatzteil Exp $
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AudioPlayer{
	
	static Clip getStream(File path){
		AudioInputStream ais;
		AudioFormat format;
		Clip cl = null;
		try{
			ais = AudioSystem.getAudioInputStream(path);
			format = ais.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format,
			((int) ais.getFrameLength() * format.getFrameSize()));
			
			cl = (Clip) AudioSystem.getLine(info);
			cl.open(ais);
		}
		catch(Exception e){}
		return cl;
	}
}
