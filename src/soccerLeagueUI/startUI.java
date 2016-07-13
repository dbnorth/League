package soccerLeagueUI;

import soccerLeaguePD.League;
import soccerLeaguePD.Player;
import soccerLeaguePD.Team;

import java.util.Collection;

import soccerLeagueDAO.LeagueDAO;
import soccerLeagueDAO.emDAO;

public class startUI {

	public static void main(String[] args) {
		emDAO.initEM();
//		League league = new League();
//		league.init();
		League league = (League) LeagueDAO.listLeague().toArray()[0];	
		printLeague(league);
		LeagueFrame.startGUI(league);

	}
	
	public static void printLeague(League league)
	{
		System.out.println("League Report");
		System.out.println("League :"+league.toString());
		System.out.println("Teams");
		for (Team team : league.getTeams())
		{
			emDAO.getEM().refresh(team);
			System.out.println(team.toString());
			System.out.println("--------------------------");
			for (Player player : team.getPlayers())
			{ 
				System.out.println(player.toString());
			}
			System.out.println("");
		}
	}

}
