package soccerLeagueUI;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import soccerLeagueDAO.emDAO;
import soccerLeaguePD.League;
import soccerLeaguePD.Location;
import soccerLeaguePD.Player;
import soccerLeaguePD.Team;

import javax.persistence.EntityTransaction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JComboBox;

public class LocationEdit extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	JComboBox<Team> comboBox;

	/**
	 * Create the panel.
	 */
	public LocationEdit(Location location, League league, JFrame currentFrame, boolean isAdd) {
		
		emDAO.getEM().refresh(league);
		if (!isAdd) emDAO.getEM().refresh(location);
		
		setLayout(null);
		
		JLabel lblTeamEdit = new JLabel("Location Edit");
		lblTeamEdit.setBounds(170, 31, 84, 16);
		add(lblTeamEdit);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(47, 65, 61, 16);
		add(lblName);
		
		textField = new JTextField(location.getName());
		textField.setBounds(161, 59, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblCoach = new JLabel("Address :");
		lblCoach.setBounds(47, 105, 61, 16);
		add(lblCoach);
		
		textField_1 = new JTextField(location.getAddress());
		textField_1.setBounds(161, 99, 134, 28);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EntityTransaction userTransaction = emDAO.getEM().getTransaction();
				userTransaction.begin();
				location.setName(textField.getText());
				location.setAddress(textField_1.getText());
				location.setHomeTeam((Team) comboBox.getSelectedItem());
				if (isAdd) {
					location.setLeague(league);
					league.addLocation(location);
				}
				userTransaction.commit();
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new LocationSelectionList(league,currentFrame));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(90, 242, 117, 29);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new LocationSelectionList(league,currentFrame));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(219, 242, 117, 29);
		add(btnCancel);
		
		comboBox = new JComboBox();
		for (Team team : league.getTeams())
		comboBox.addItem(team);
		if (!isAdd) comboBox.setSelectedItem(location.getHomeTeam());
		comboBox.setBounds(161, 141, 129, 27);
		add(comboBox);
		
		JLabel lblHomeTeam = new JLabel("Home Team :");
		lblHomeTeam.setBounds(47, 145, 102, 16);
		add(lblHomeTeam);
	}
}

