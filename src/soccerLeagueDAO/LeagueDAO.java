package soccerLeagueDAO;

import java.util.List;

import javax.persistence.Query;

import soccerLeaguePD.League;
public class LeagueDAO {
		

		public static void addLeague(League league) {
			emDAO.getEM().persist(league);
		}

		public static List<League> listLeague() {
			Query query = emDAO.getEM().createQuery("SELECT league FROM league league");
			List<League> list= (List) query.getResultList();

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

