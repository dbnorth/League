package soccerLeagueUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import soccerLeagueDAO.emDAO;
import soccerLeaguePD.League;
import soccerLeaguePD.Team;

import javax.swing.JLabel;
import javax.swing.JList;

import java.util.Map.Entry;

import javax.persistence.EntityTransaction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeamSelectionList extends JPanel {

	/**
	 * Create the panel.
	 */
	public TeamSelectionList(League league, JFrame currentFrame) {
		emDAO.getEM().refresh(league);
		setLayout(null);
	
		JLabel lblTeamSelectionList = new JLabel("Team Selection List");
		lblTeamSelectionList.setBounds(148, 26, 138, 16);
		add(lblTeamSelectionList);
		
		DefaultListModel listModel = new DefaultListModel();
		for (Team team : league.getTeams())
		listModel.addElement(team);
		
		JList list = new JList(listModel);
		list.setBounds(148, 66, 138, 102);
//		add(list);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(148, 66, 138, 102);
		scrollPane.setViewportView(list);
		add(scrollPane);
		
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
				{
					EntityTransaction userTransaction = emDAO.getEM().getTransaction();
					userTransaction.begin();
					league.removeTeam(team);
					userTransaction.commit();
				}
					
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TeamSelectionList(league, currentFrame));
				currentFrame.getContentPane().revalidate();
	
			}
		});
		btnDelete.setBounds(295, 193, 117, 29);
		add(btnDelete);

	}
}
