package soccerLeagueDM;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.persistence.EntityTransaction;

import soccerLeagueDAO.emDAO;
import soccerLeaguePD.League;
import soccerLeaguePD.Player;
import soccerLeaguePD.Team;

public class PlayerDM {
	
	public static void loadPlayers(League league) throws IOException
	{
		System.out.println("loading players...");
		PlayerDM.loadPlayers(league, "players.csv");
		
	}
	

	public static void loadPlayers(League league, String pathName) throws IOException
	{	
		String line = null;
		String[] token;
		
		String teamName;
		String name;
		String position;
		String number;
		
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
			        	if ( token.length < 4)
			        		throw new IOException("Bad file format: " + pathName);
			        	else
			        	{
				        	teamName = token[0];
				        	name = token[1];	
				        	position = token[2];	
				        	number = token[3];	
			        	}
			        	Team team = league.findTeam(teamName);
			        Player player = new Player(team, name, position,number);
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

