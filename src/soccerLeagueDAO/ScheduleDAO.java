package soccerLeagueDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import soccerLeaguePD.Schedule;

public class ScheduleDAO {	

	public static void saveSchedule(Schedule schedule) {
		emDAO.getEM().persist(schedule);
	}
	
		public static void addSchedule(Schedule schedule) {
			emDAO.getEM().persist(schedule);
		}

	public static List<Schedule> listSchedule()
	{
		TypedQuery<Schedule> query = emDAO.getEM().createQuery(
				"SELECT schedule FROM schedule schedule", Schedule.class);
		return query.getResultList();
	}

	public static Schedule findScheduleById(int id)
	{
		Schedule schedule = emDAO.getEM().find(Schedule.class, new Integer(id));
		return schedule;
	}

	public static void removeSchedule(Schedule schedule)
	{
		emDAO.getEM().remove(schedule);
	}
}
