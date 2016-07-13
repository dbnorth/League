package soccerLeagueDM;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.persistence.EntityTransaction;

import soccerLeagueDAO.emDAO;

import soccerLeaguePD.League;

public class LeagueDM {
	
	public static boolean loadLeague(League league)
	{
		boolean success = true;
		try {
			
			System.out.println("loading league...");
			loadLeague(league, "leagues.csv");
			TeamDM.loadTeams(league);
			emDAO.getEM().refresh(league);
			LocationDM.loadLocations(league);

			
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			success = false;
		}
		return success;
		
	}
	
	public static void loadLeague(League league, String pathName) throws IOException
	{	
		String line = null;
		String[] token;
		
		String commissioner;	// Commissioner name
		String name;	// full name
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
	        // read one data line (we only support one league)
	        line = bufferedReader.readLine();
	        
    		//split data by comma
        	token = line.split(",");
        	if ( token.length < 2)
        		throw new IOException("Bad " + pathName + " file format; expected 2 fields");
        	else
        	{
	        	name = token[0];
	        	commissioner = token[1];
	        	league.setName(name);
	        	league.setCommissioner(commissioner);
	        	emDAO.getEM().persist(league);
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


