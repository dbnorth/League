package soccerLeagueDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import soccerLeaguePD.Location;

public class LocationDAO {	

	public static void addLocation(Location location)
	{
		emDAO.getEM().persist(location);
	}

	public static List<Location> listLocation()
	{
		TypedQuery<Location> query = emDAO.getEM().createQuery(
				"SELECT location FROM location location", Location.class);
		return query.getResultList();
	}

	public static Location findLocationById(int id)
	{
		Location Location = emDAO.getEM().find(Location.class, new Integer(id));
		return Location;
	}

	public static void removeLocation(Location location)
	{
		emDAO.getEM().remove(location);
	}
}
