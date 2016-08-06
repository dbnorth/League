package soccerLeagueTest;

import org.junit.Assert;
import org.junit.Test;

import soccerLeaguePD.Game;
import utility.SimpleDate;

public class GameTest {
@Test
public void setDateTest()
{
	Game game = new Game();
	SimpleDate date = new SimpleDate("08/01/2016");
	game.setDate(date);
	SimpleDate date1 = game.getDate();
	Assert.assertTrue("Date is not the same", date1.equals(date));
}
}



