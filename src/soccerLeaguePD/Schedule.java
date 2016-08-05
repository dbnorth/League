package soccerLeaguePD;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import soccerLeagueDAO.GameDAO;
import utility.SimpleDate;

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
	
	/**
	 * The date the Season starts.
	 */

	@Column(name = "start_date", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

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
	public Date getStartDate()
	{
		return this.startDate;
	}
	public String getStartDateString()
	{
		if (startDate !=null)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			String dateString = sdf.format(startDate);
			return dateString;
		}
		else
			return "";
		
	}
	public void setStartDate(Date date)
	{
		this.startDate= date;
	}
	public void setStartDate (String startDateString)  
	{
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try
		{
			startDate = sdf.parse(startDateString);
		}
		catch (ParseException e)
		{
			startDate = new Date();
		}

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
	public Boolean isOkToRemove()
	{
		if (getGames().size() == 0)return true;
		else return false;
	}
	
	public void generateSchedule()
	{
		Team[] teamArray = getLeague().getTeams().toArray(new Team[getLeague().getTeams().size()]);
		
		Date[] gameDate = new Date[teamArray.length];
		for (int d =0;d<teamArray.length;d++)
		{
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			cal.add(Calendar.DATE, d*7);  
			gameDate[d] = cal.getTime();
		}
		for (int i=0;i<teamArray.length;i++)
			for (int j = i;j<teamArray.length;j++ )
			{
				Game game = new Game(this,teamArray[i],teamArray[j],gameDate[j]);
			}
			
	}
	public String toString()
	{
		return getSeason();
	}
}
