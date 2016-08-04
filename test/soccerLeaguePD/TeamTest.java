package soccerLeaguePD;

import org.junit.Assert;
import org.junit.Test;

public class TeamTest {
	@Test
	public void testBasicCreate() {
		Team t = new Team();
		
		final String name = "team name";
		t.setName(name);
		Assert.assertEquals( "Team name should match value from setter",
				name, t.getName() );
		
		final String coach = "Coach X";
		t.setCoachName(coach);
		Assert.assertEquals( "Coach name should match value from setter",
				coach, t.getCoachName() );
	}
}
