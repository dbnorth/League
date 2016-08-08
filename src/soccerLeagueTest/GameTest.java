package soccerLeagueTest;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import soccerLeaguePD.Game;
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
}



