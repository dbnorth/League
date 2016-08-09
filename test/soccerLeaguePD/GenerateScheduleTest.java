package soccerLeaguePD;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;

import org.easymock.EasyMock;
import org.easymock.Mock;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import soccerLeagueDAO.emDAO;
import sun.misc.Unsafe;

@RunWith(PowerMockRunner.class)
@PrepareForTest(emDAO.class)
public class GenerateScheduleTest {

	@Mock League l = new League();
	
	@Test
	public void testSimpleGenerate() {
		Schedule schedule = new Schedule();
		//Schedule expects emDAO to refresh the Schedule's games field when
		//adding a new Game through the DAO (and calling refresh() on the
		//schedule), so we have to fake that behavior, here, with a DummyEntityManager
		EntityManager em = new DummyEntityManager() {
			public void persist( final Object o ) {
				try {
					Field f = Schedule.class.getDeclaredField("games");
					f.setAccessible(true);
					
					if( o instanceof Game ) {
						final Object games = f.get(schedule);
						if( games == null ) {
							f.set(schedule, new ArrayList<Game>());
						}
						
						((Collection<Game>)f.get(schedule)).add((Game)o);
					}
				}
				catch( final RuntimeException re ) {
					throw re;
				}
				catch( final Exception e ) {
					throw new RuntimeException( e );
				}
			}
		};
		//use PowerMock to mock the static methods of emDAO
		PowerMock.mockStatic(emDAO.class);
		//EasyMock doesn't seem to want to allow expecting a non-void method
		//to be called multiple times without just calling expect that many
		//times (i.e. the times(6) method won't work in this case)
		EasyMock.expect(emDAO.getEM()).andReturn(em);
		EasyMock.expect(emDAO.getEM()).andReturn(em);
		EasyMock.expect(emDAO.getEM()).andReturn(em);
		EasyMock.expect(emDAO.getEM()).andReturn(em);
		EasyMock.expect(emDAO.getEM()).andReturn(em);
		EasyMock.expect(emDAO.getEM()).andReturn(em);
		
		//set up fake Team data for testing
		Collection<Team> teams = new ArrayList<Team>();
		Team t = new Team();
		t.setName("White");
		t.setCoachName("Coach A");
		t.setLeague(l);
		teams.add(t);
		t = new Team();
		t.setName("Orange");
		t.setCoachName("Coach B");
		t.setLeague(l);
		teams.add(t);
		//make the Mock'ed League return these teams
		EasyMock.expect(l.getTeams()).andReturn(teams);
		
		//Mocks are ready
		EasyMock.replay(l);
		PowerMock.replayAll();
		
		//this is the real test code, ask the schedule to generate
		schedule.setLeague(l);
		schedule.setStartDate("01/01/2016");
		schedule.generateSchedule();
		
		//verification that the schedule is what we expect, and Mocks
		//were called as expected
		Collection<Game> games = schedule.getGames();
		Assert.assertEquals( "Two team league should have 2 games",
				2, games.size() );
		Game[] array = games.toArray( new Game[ games.size() ] );
		Assert.assertNotEquals( "Two games should be on separate dates",
				array[0].getDate(), array[1].getDate() );
		//could also verify first game is on the schedule start date, etc.
		
		PowerMock.verifyAll();
		EasyMock.verify();
	}
}
