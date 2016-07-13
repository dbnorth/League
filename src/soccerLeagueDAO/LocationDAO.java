package soccerLeagueDAO;

import java.util.ArrayList;
import javax.persistence.Query;
import soccerLeaguePD.Location;

	public class LocationDAO {	

			public static void addLocation(Location location) {
				emDAO.getEM().persist(location);
			}

			public static ArrayList<Location> listLocation() {
				Query query = emDAO.getEM().createQuery("SELECT location FROM location location");
				ArrayList<Location> list= (ArrayList) query.getResultList();

				return list;
			}

			public static Location findLocationById(int id) {
				Location Location = emDAO.getEM().find(Location.class, new Integer(id));
				return Location;
			}

			public static void removeLocation(Location location) {
				emDAO.getEM().remove(location);
				
			}

	}

