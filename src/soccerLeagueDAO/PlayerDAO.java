package soccerLeagueDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import soccerLeaguePD.Player;

public class PlayerDAO {	

	public static void addPlayer(Player player)
	{
		emDAO.getEM().persist(player);
	}
	public static List<Player> listPlayer()
	{
		TypedQuery<Player> query = emDAO.getEM().createQuery(
				"SELECT player FROM player player", Player.class);
		return query.getResultList();
	}

	public static Player findPlayerById(int id)
	{
		Player player = emDAO.getEM().find(Player.class, new Integer(id));
		return player;
	}

	public static void removePlayer(Player player)
	{
		emDAO.getEM().remove(player);
	}
}
