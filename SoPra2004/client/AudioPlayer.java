package client;


import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 * AudioPlayer Class for the different soundfiles 
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
*/
public class AudioPlayer{
	
	static Clip getStream(URL path) throws SoundDisabledException{
		AudioInputStream ais;
		AudioFormat format;
		Clip cl = null;
		if(!IOHandler.getSavedSoundSettings()){
			try{
				ais = AudioSystem.getAudioInputStream(path);
				format = ais.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, format,
				((int) ais.getFrameLength() * format.getFrameSize()));
				
				cl = (Clip) AudioSystem.getLine(info);
				cl.open(ais);
			}
			catch(Exception e){}
		}
		else{
			throw new SoundDisabledException("Sounds sind deaktiviert!");
		}
		return cl;
	}
}
