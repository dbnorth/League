package soccerLeagueDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import soccerLeaguePD.Game;

public class GameDAO {	

	public static void saveGame(Game game) {
		emDAO.getEM().persist(game);
	}
	public static void addGame(Game game) {
		emDAO.getEM().persist(game);
	}

	public static List<Game> listGame()
	{
		TypedQuery<Game> query = emDAO.getEM().createQuery("SELECT game FROM game game", Game.class);
		return query.getResultList();
	}

	public static Game findGameById(int id)
	{
		Game game = emDAO.getEM().find(Game.class, new Integer(id));
		return game;
	}

	public static void removeGame(Game game)
	{
		emDAO.getEM().remove(game);
	}
}
