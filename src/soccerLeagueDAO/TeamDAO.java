package soccerLeagueDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import soccerLeaguePD.Team;

public class TeamDAO {	

	public static void addTeam(Team team)
	{
		emDAO.getEM().persist(team);
	}

	public static List<Team> listTeam()
	{
		TypedQuery<Team> query = emDAO.getEM().createQuery(
				"SELECT team FROM team team", Team.class);
		return query.getResultList();
	}
	
	public static Team findTeamById(int id)
	{
		Team team = emDAO.getEM().find(Team.class, new Integer(id));
		return team;
	}

	public static void removeTeam(Team team)
	{
		emDAO.getEM().remove(team);
	}
}
