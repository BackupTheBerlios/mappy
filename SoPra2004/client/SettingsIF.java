package client;

/**
 * Interface for the Database Settings
 * 
 * @author Softwarepraktikum 2004/05 Gruppe 2
 *
 */
public interface SettingsIF {
	String DB_URL="lnxsvc.cip.uni-bamberg.de";
	String DB_PORT="3306";
	String DB_NAME="SoPraDB";
	String DB_USR="projekt";
	String DB_PW="StPlan";
	String[] DB_SETTINGS={DB_URL,DB_PORT,DB_NAME,DB_USR,DB_PW };
	

}
