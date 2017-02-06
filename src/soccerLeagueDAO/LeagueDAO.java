package soccerLeagueDAO;

import java.util.List;
import javax.persistence.TypedQuery;
import soccerLeaguePD.League;
public class LeagueDAO {

	public static void addLeague(League league)
	{
		emDAO.getEM().persist(league);
	}
		
	public static void saveLeague(League league) {
		emDAO.getEM().persist(league);
		
	}

	public static List<League> listLeague()
	{
		TypedQuery<League> query = emDAO.getEM().createQuery(
				"SELECT league FROM league league", League.class);
		return query.getResultList();
	}

	public static League findLeagueById(int id)
	{
		League league = emDAO.getEM().find(League.class, new Integer(id));
		return league;
	}
	
	public static void removeLeague(League league)
	{
		emDAO.getEM().remove(league);
	}
}
