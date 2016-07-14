package soccerLeagueUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import soccerLeaguePD.League;
import soccerLeaguePD.Team;

public class TeamSelectionList extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the panel.
	 */
	public TeamSelectionList(League league, JFrame currentFrame) {
		setLayout(null);
	
		JLabel lblTeamSelectionList = new JLabel("Team Selection List");
		lblTeamSelectionList.setBounds(148, 26, 138, 16);
		add(lblTeamSelectionList);
		
		DefaultListModel<Team> listModel = new DefaultListModel<Team>();
		for (Team team : league.getTeams())
		listModel.addElement(team);
		
		JList<Team> list = new JList<Team>(listModel);
		list.setBounds(148, 66, 138, 102);
		add(list);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TeamEdit(new Team(),league, currentFrame,true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnAdd.setBounds(66, 193, 117, 29);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TeamEdit((Team) list.getSelectedValue(),league, currentFrame,false));
				currentFrame.getContentPane().revalidate();
	
			}
		});
		btnUpdate.setBounds(179, 193, 117, 29);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Team team = (Team) list.getSelectedValue();
				if (team.isOkToRemove())
					league.removeTeam(team);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TeamSelectionList(league, currentFrame));
				currentFrame.getContentPane().revalidate();
	
			}
		});
		btnDelete.setBounds(295, 193, 117, 29);
		add(btnDelete);

	}
}
