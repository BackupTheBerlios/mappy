package server;

/**
 * Exceptionhandler if there is no image
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *
 */
public class NoImageException extends Exception {
	  public NoImageException()
	  {
	    super();
	  }

	  public NoImageException( String s )
	  {
	    super( s );
	  }
}
