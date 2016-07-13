package soccerLeagueDM;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.persistence.EntityTransaction;

import soccerLeagueDAO.emDAO;
import soccerLeaguePD.League;
import soccerLeaguePD.Team;

public class TeamDM {

	
	public static void loadTeams(League league) throws IOException
	{
		System.out.println("loading teams...");
		TeamDM.loadTeams(league,"teams.csv");
		emDAO.getEM().refresh(league);
		PlayerDM.loadPlayers(league);
		
	}
	
	public static void loadTeams(League league, String pathName) throws IOException
	{	
		String line = null;
		String[] token;
		
		String coach;
		String name;
		
		
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
				        	coach = token[1];			        	
			        	}
			        	
			        	Team team = new Team(league, name, coach);
			        	
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

