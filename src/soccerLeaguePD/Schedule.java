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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import soccerLeagueDAO.GameDAO;

/**
 * A Schedule is a list of the Games played in a Session for a League.
 */
@Entity(name = "schedule")
public class Schedule
{
	@Id //signifies the primary key
    @Column(name = "schedule_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long scheduleID;
	
	/**
	 * The League that organizes the Games for the Schedule
	 */
	@ManyToOne(optional=false)
    @JoinColumn(name="league_id",referencedColumnName="league_id") 
	private League league;
	
	/**
	 * The a collection of Games to be played
	 */
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="schedule",targetEntity=Game.class)
	private Collection<Game> games;
	
	/**
	 * A season is a set period of time in which the games are played.
	 */
	@Column(name = "season", nullable = false,length = 10)
	private String season;

	public Schedule()
	{
//		games = new ArrayList<Game>();
	}
	
	public League getLeague()
	{
		return this.league;
	}

	public void setLeague(League league)
	{
		this.league = league;
	}

	public String getSeason()
	{
		return this.season;
	}

	public void setSeason(String season)
	{
		this.season = season;
	}

	public Collection<Game> getGames()
	{
		return games;
	}

	public void addGame(Game game)
	{
		GameDAO.addGame(game);
	}
	
	public void removeGame(Game game)
	{
		GameDAO.removeGame(game);
	}
}
