package client;

/**
 * Exceptionhandler for Sound Settings
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *
 */
public class SoundDisabledException extends Exception{
	public SoundDisabledException(){
		super();
	}

	public SoundDisabledException(String s){
		super(s);
	}
}