package soccerLeagueDM;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.persistence.EntityTransaction;

import soccerLeagueDAO.emDAO;
import soccerLeaguePD.League;
import soccerLeaguePD.Location;
import soccerLeaguePD.Team;

public class LocationDM {

	
	public static void loadLocations(League league) throws IOException
	{
		System.out.println("loading locations...");
		LocationDM.loadLocations(league, "locations.csv");
	}
	
	public static void loadLocations(League league, String pathName) throws IOException
	{	
		String line = null;
		String[] token;
		
		String name;
		String address;
		String homeTeamName;
		
		BufferedReader bufferedReader = null;
		EntityTransaction userTransaction = emDAO.getEM().getTransaction();
		userTransaction.begin();


	    try 
	    {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader = 
	            new FileReader(pathName);

	        // Always wrap FileReader in BufferedReader.
	        bufferedReader = new BufferedReader(fileReader);
			
	        // read and discard headings in csv
	        line = bufferedReader.readLine();
	        
	        while((line = bufferedReader.readLine()) != null) 
		    {
		        		//split data by comma
			        	token = line.split(",");
			        	if ( token.length < 2)
			        		throw new IOException("Bad file format: " + pathName);
			        	else
			        	{
				        	name = token[0];
				        	address = token[1];
				        	homeTeamName = token[3];
			        	}
			        	
			        	Team homeTeam = league.findTeam(homeTeamName);
			        	Location location = new Location(league, name, address, homeTeam);  
	        }
	    }
	    catch(FileNotFoundException ex) 
	    {
	        System.out.println(
	            "Unable to open file '" + 
	            pathName + "'" + " at cur dir: " + System.getProperty("user.dir"));    
	        throw ex;
	    }
	    catch(IOException ex) {
	        System.out.println(
	            "Error reading file '" 
	            + pathName + "'");  
	        throw ex;
		
		}
	    finally
	    {
	    	 // Always close files.
	    		if ( bufferedReader != null )
	    			bufferedReader.close();   
	    
	    }
	    userTransaction.commit();
	}
}

