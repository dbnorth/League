package soccerLeagueDAO;


import java.util.ArrayList;
import javax.persistence.Query;
import soccerLeaguePD.Team;

	public class TeamDAO {	

		public static void addTeam(Team team) {
			emDAO.getEM().persist(team);
		}

		public static ArrayList<Team> listTeam() {
			Query query = emDAO.getEM().createQuery("SELECT team FROM team team");
		ArrayList<Team> list= (ArrayList) query.getResultList();

		return list;
		}

		public static Team findTeamById(int id) {
			Team team = emDAO.getEM().find(Team.class, new Integer(id));
			return team;
		}

		public static void removeTeam(Team team) {
			emDAO.getEM().remove(team);
			
		}

	}
