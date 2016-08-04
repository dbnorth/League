package soccerLeaguePD;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import utility.SimpleDate;

@Entity(name = "game")
public class Game implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id //signifies the primary key
    @Column(name = "game_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long gameId;
	
	/**
	 * The Schedule that the game is a part of
	 */	
	@ManyToOne(optional=false)
    @JoinColumn(name="schedule_id",referencedColumnName="schedule_id")
    private Schedule schedule;
	
	/**
	 * The Team that is the home Team for the Game,
	 */
	@OneToOne(optional=false)
    @JoinColumn(name = "hometeam_id",referencedColumnName="team_id") 
	private Team homeTeam;
	
	/**
	 * The Team that is the visiting team for the Game.
	 */
	@OneToOne(optional=false)
    @JoinColumn(name = "visitingteam_id",referencedColumnName="team_id") 
	private Team visitingTeam;
	
	/**
	 * The Location where the game is played.
	 */
	@OneToOne(optional=false)
    @JoinColumn(name="location_id",referencedColumnName="location_id")
	private Location location;
	
	/**
	 * The date the Game is played.
	 */

	@Column(name = "date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	/**
	 * The number of goals Scored by the home Team in regulation.
	 */
	@Column(name = "home_team_score") 
	private int homeTeamScore;
	
	/**
	 * The number of goals scored by the visiting Team in regulation.
	 */
	@Column(name = "visting_team_score")
	private int visitingTeamScore;
	
	/**
	 * The number of PK's scored by the home Team after a tie.
	 */
	@Column(name = "home_team_pk") 
	private int homeTeamPK;
	
	/**
	 * The number of PK's scored by the visiting Team in a tie.
	 */
	@Column(name = "visiting_team_pk")
	private int visitingTeamPK;
	
	public Game()
	{
		
	}
	
	public Game(Schedule schedule,Team homeTeam, Team visitingTeam, Date date)
	{
		this.homeTeam = homeTeam;
		this.visitingTeam = visitingTeam;
		this.date = date;
		this.location = homeTeam.getHomeLocation();
		schedule.addGame(this);
	}
	
	public Schedule getSchedule()
	{
		return this.schedule;
	}
	
	public void setSchedule(Schedule schedule)
	{
		this.schedule = schedule;
	}
	
	public Team getHomeTeam()
	{
		return this.homeTeam;
	}

	public void setHomeTeam(Team homeTeam)
	{
		this.homeTeam = homeTeam;
	}

	public Team getVisitingTeam()
	{
		return this.visitingTeam;
	}

	public void setVisitingTeam(Team visitingTeam)
	{
		this.visitingTeam = visitingTeam;
	}

	public Location getLocation()
	{
		return this.location;
	}

	public void setLocation(Location location)
	{
		this.location = location;
	}

	public Date getDate()
	{
		return this.date;
	}
	
	public String getDateString()
	{
		if (date !=null)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
			String dateString = sdf.format(date);
			return dateString;
		}
		else
			return "";
		
	}

	public void setDate(Date date)
	{
		this.date = date;
	}
	
	public void setDate (String dateString)  
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
		try
		{
			date = sdf.parse(dateString);
		}
		catch (ParseException e)
		{
		
		}

	}
		
	public int getHomeTeamScore()
	{
		return this.homeTeamScore;
	}

	public void setHomeTeamScore(int homeTeamScore)
	{
		this.homeTeamScore = homeTeamScore;
	}

	public int getVisitingTeamScore()
	{
		return this.visitingTeamScore;
	}

	public void setVisitingTeamScore(int visitingTeamScore)
	{
		this.visitingTeamScore = visitingTeamScore;
	}

	public int getHomeTeamPK()
	{
		return this.homeTeamPK;
	}

	public void setHomeTeamPK(int homeTeamPK)
	{
		this.homeTeamPK = homeTeamPK;
	}

	public int getVisitingTeamPK()
	{
		return this.visitingTeamPK;
	}

	public void setVisitingTeamPK(int visitingTeamPK)
	{
		this.visitingTeamPK = visitingTeamPK;
	}
	
	public Boolean isOkToRemove()
	{
		if (getDate().before(new Date()))return true;
		else return false;
	}
	
	public String toString()
	{
		return getDateString()+":"+getHomeTeam().getName()+" vs. "+ getVisitingTeam().getName();
	}
}
