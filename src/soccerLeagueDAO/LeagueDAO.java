package soccerLeagueDAO;

import java.util.ArrayList;

import javax.persistence.Query;

import soccerLeaguePD.League;
public class LeagueDAO {
		
	public static void saveLeague(League league) {
		emDAO.getEM().persist(league);
		
	}
		public static void addLeague(League league) {
			emDAO.getEM().persist(league);
		}

		public static ArrayList<League> listLeague() {
			Query query = emDAO.getEM().createQuery("SELECT league FROM league league");
			ArrayList<League> list= (ArrayList) query.getResultList();

			return list;
		}

		public static League findLeagueById(int id) {
			League league = emDAO.getEM().find(League.class, new Integer(id));
			return league;
		}

		public static void removeLeague(League league) {
			emDAO.getEM().remove(league);
			
		}

}

