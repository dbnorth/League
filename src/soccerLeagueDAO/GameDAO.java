package soccerLeagueDAO;

import java.util.ArrayList;
import javax.persistence.Query;
import soccerLeaguePD.Game;

public class GameDAO {	

	public static void addGame(Game game) {
		emDAO.getEM().persist(game);
	}

	public static ArrayList<Game> listGame() {
		Query query = emDAO.getEM().createQuery("SELECT game FROM game game");
		ArrayList<Game> list= (ArrayList) query.getResultList();

		return list;
	}

	public static Game findGameById(int id) {
		Game game = emDAO.getEM().find(Game.class, new Integer(id));
		return game;
	}

	public static void removeGame(Game game) {
		emDAO.getEM().remove(game);
		
	}

}

