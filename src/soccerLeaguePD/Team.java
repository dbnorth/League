package soccerLeaguePD;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import soccerLeagueDAO.PlayerDAO;

/**
 * A Team is an organization that competes in a league.
 */
@Entity(name = "team")
public class Team
{
	@Id //signifies the primary key
    @Column(name = "team_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long teamID;
	
	/**
	 * The League that he Team plays it games in.
	 */
	@ManyToOne(optional=false)
    @JoinColumn(name="league_id",referencedColumnName="league_id")
	private League league;
	
	/**
	 * The collection of the Players that play on a team.
	 */
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="team",targetEntity=Player.class)
	private Collection<Player> players;

	
	@OneToOne(optional=true)
    @JoinColumn(name = "home_location_id",nullable = true,referencedColumnName="location_id") 
	private Location homeLocation;
	
	/**
	 * The name of the Team
	 */
	private String name;
	
	/**
	 * The name of the coach for the team.
	 */
	private String coachName;

	public Team()
	{
//		players = new ArrayList<Player>();
	}
	public Team (String name, String coachName)
  {
    this();
    this.name = name;
    this.coachName = coachName;
  }
	
	public Team (League league, String name, String coachName)
	{
		this();
		this.name = name;
		this.coachName = coachName;
		this.league = league;
		league.addTeam(this);
	}
	
	public League getLeague()
	{
		return this.league;
	}

	public void setLeague(League league)
	{
		this.league = league;
	}

	public Collection<Player> getPlayers()
	{
		return this.players;
	}

	public void addPlayer(Player player)
	{
		PlayerDAO.addPlayer(player);
	}
	
	public void removePlayer (Player player)
	{
		PlayerDAO.removePlayer(player);
	}
	
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getCoachName()
	{
		return this.coachName;
	}

	public void setCoachName(String coachName)
	{
		this.coachName = coachName;
	}

	public Location getHomeLocation()
	{
		return homeLocation;
	}

	public void setHomeLocation(Location homeLocation)
	{
		this.homeLocation = homeLocation;
	}
	
	public Boolean isOkToRemove()
	{
		return true;
	}
	
	public String toString()
	{
		return getName()+":"+getCoachName()+":"+getHomeLocation();
	}
}
