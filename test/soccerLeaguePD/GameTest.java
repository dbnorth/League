package soccerLeaguePD;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import soccerLeaguePD.Game;
import soccerLeaguePD.Team;
import utility.SimpleDate;

public class GameTest {
@Test
public void setDateTest()
{
	Game game = new Game();
	Date date = new Date();
	game.setDate(date);
	Date date1 = game.getDate();
	Assert.assertTrue("Date is not the same", date1.equals(date));
}
@Test
public void setScoreTest()
{
  Game game = new Game();
  int homeTeamScore = 1;
  game.setHomeTeamScore(homeTeamScore);
  int visitingTeamScore = 2;
  game.setVisitingTeamScore(visitingTeamScore);
  Assert.assertTrue("Home Score is not the same", homeTeamScore==game.getHomeTeamScore());
  Assert.assertTrue("Visiting Score is not the same", visitingTeamScore==game.getVisitingTeamScore());
}
@Test
public void winnerTest()
{
  Game game = new Game();
  Team homeTeam = new Team("Rockets","Jim");
  game.setHomeTeam(homeTeam);
  Team visitingTeam = new Team("Stars","Sam");
  game.setVisitingTeam(visitingTeam);
  int homeTeamScore = 1;
  game.setHomeTeamScore(homeTeamScore);
  int visitingTeamScore = 2;
  game.setVisitingTeamScore(visitingTeamScore);
  Assert.assertTrue("Winner incorrect", game.getWinner()==visitingTeam);

}
}



