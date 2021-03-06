package soccerLeagueUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import soccerLeaguePD.League;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LeagueFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void startGUI(League league) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LeagueFrame frame = new LeagueFrame(league);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LeagueFrame(League league) {
		JFrame currentFrame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 450);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnMaintain = new JMenu("Maintain");
		menuBar.add(mnMaintain);
		
		JMenuItem mntmLeague = new JMenuItem("League");
		mntmLeague.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new LeagueEdit(league,currentFrame));
				getContentPane().revalidate();
			}
		});
		mnMaintain.add(mntmLeague);
		
		JMenuItem mntmLocations = new JMenuItem("Locations");
		mnMaintain.add(mntmLocations);
		
		JMenuItem mntmTeams = new JMenuItem("Teams");
		mntmTeams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new TeamSelectionList(league,currentFrame));
				getContentPane().revalidate();
	
			}
		});
		mnMaintain.add(mntmTeams);
		
		JMenu mnScheudle = new JMenu("Schedule");
		menuBar.add(mnScheudle);
		
		JMenuItem mntmGenerate = new JMenuItem("Generate");
		mnScheudle.add(mntmGenerate);
		
		JMenuItem mntmEdit = new JMenuItem("Edit");
		mnScheudle.add(mntmEdit);
		
		JMenu mnReports = new JMenu("Reports");
		menuBar.add(mnReports);
		
		JMenuItem mntmLeague_1 = new JMenuItem("League");
		mnReports.add(mntmLeague_1);
		
		JMenuItem mntmTeam = new JMenuItem("Team");
		mnReports.add(mntmTeam);
		
		JMenuItem mntmSchedule = new JMenuItem("Schedule");
		mnReports.add(mntmSchedule);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		getContentPane().removeAll();
		getContentPane().add(new MainPanel(league));
		getContentPane().revalidate();
	}

}
