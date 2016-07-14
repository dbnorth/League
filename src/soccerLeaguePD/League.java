package soccerLeaguePD;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import soccerLeagueDAO.LocationDAO;
import soccerLeagueDAO.ScheduleDAO;
import soccerLeagueDAO.TeamDAO;
import soccerLeagueDAO.emDAO;
import soccerLeagueDM.LeagueDM;

/**
 * A League is an organization the a group of teams competes in.
 */
@Entity(name = "league")
public class League
{
	@Id //signifies the primary key
    @Column(name = "league_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long leagueID;
	/**
	 * A collection of the Teams that complete in the League
	 */
	 @OneToMany(mappedBy="league",targetEntity=Team.class,
     fetch=FetchType.EAGER)
	private Collection<Team> teams;
	 
	/**
	 * A collection of Schedule of Games that the League manages
	 */
	 @LazyCollection(LazyCollectionOption.FALSE)
	 @OneToMany(mappedBy="league",targetEntity=Schedule.class)
	 private Collection<Schedule> schedules;
	 
	/**
	 * The collection of Locations where Games are played.
	 */
	 @LazyCollection(LazyCollectionOption.FALSE)
	 @OneToMany(mappedBy="league",targetEntity=Location.class)
	 private Collection<Location> locations;
	 
	/**
	 * The name of the League.
	 */
	 @Column(name = "name", nullable = false,length = 50)
	private String name;
	/**
	 * The name of the commissioner who manages the league.
	 */
	 @Column(name = "commissioner", nullable = false,length = 50)
	 private String commissioner;
	
	public League()
	{
//		teams = new ArrayList<Team>();
//		schedules = new ArrayList<Schedule>();
//		locations = new ArrayList<Location>();
	}

	public Collection<Team> getTeams()
	{
		return this.teams;
	}
	
	public void addTeam(Team team)
	{
		TeamDAO.addTeam(team);
		
	}
	
	public void removeTeam(Team team)
	{
		TeamDAO.removeTeam(team);
		
	}

	public Collection<Schedule> getSchedules()
	{
		return this.schedules;
	}
	
	public void addSchedule(Schedule schedule)
	{
		ScheduleDAO.addSchedule(schedule);
		
	}
	
	public void removeSchedule(Schedule schedule)
	{
		ScheduleDAO.removeSchedule(schedule);
		
	}

	public Collection<Location> getLocations()
	{
		return this.locations;
	}

	public void addLocation(Location location)
	{
		LocationDAO.addLocation(location);
		
	}
	
	public void removeLocation(Location location)
	{
		LocationDAO.removeLocation(location); 
	
	}
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCommissioner() {
		return commissioner;
	}

	public void setCommissioner(String commissioner) {
		this.commissioner = commissioner;
	}
	
	public Team findTeam(String teamName)
	{
		for (Team team : getTeams())
		{
			if (team.getName().equals(teamName)) return team;
		}
		return null;
	}
	
	public void init()
	{
		LeagueDM.loadLeague(this);
	}
	
	public String toString()
	{
		return getName()+":"+getCommissioner();
	}
}