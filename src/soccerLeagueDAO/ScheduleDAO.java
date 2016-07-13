package soccerLeagueDAO;

import java.util.ArrayList;
import javax.persistence.Query;
import soccerLeaguePD.Schedule;

public class ScheduleDAO {	

	public static void saveSchedule(Schedule schedule) {
		emDAO.getEM().persist(schedule);
	}
	
		public static void addSchedule(Schedule schedule) {
			emDAO.getEM().persist(schedule);
		}

		public static ArrayList<Schedule> listSchedule() {
			Query query = emDAO.getEM().createQuery("SELECT schedule FROM schedule schedule");
			ArrayList<Schedule> list= (ArrayList) query.getResultList();

			return list;
		}

		public static Schedule findScheduleById(int id) {
			Schedule schedule = emDAO.getEM().find(Schedule.class, new Integer(id));
			return schedule;
		}

		public static void removeSchedule(Schedule schedule) {
			emDAO.getEM().remove(schedule);
			
		}

}

