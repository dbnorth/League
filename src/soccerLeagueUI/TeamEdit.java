package soccerLeagueUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.persistence.EntityTransaction;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import soccerLeagueDAO.emDAO;
import soccerLeaguePD.League;
import soccerLeaguePD.Location;
import soccerLeaguePD.Player;
import soccerLeaguePD.Team;

public class TeamEdit extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	private JTextField textField_1;
	JComboBox<Location> comboBox;

	/**
	 * Create the panel.
	 */
	public TeamEdit(Team team, League league, JFrame currentFrame, boolean isAdd) {
		
		emDAO.getEM().refresh(league);
		if (!isAdd) emDAO.getEM().refresh(team);
		
		setLayout(null);
		
		JLabel lblTeamEdit = new JLabel("Team Edit");
		lblTeamEdit.setBounds(170, 31, 84, 16);
		add(lblTeamEdit);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(47, 65, 61, 16);
		add(lblName);
		
		textField = new JTextField(team.getName());
		textField.setBounds(120, 59, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblCoach = new JLabel("Coach :");
		lblCoach.setBounds(47, 105, 61, 16);
		add(lblCoach);
		
		textField_1 = new JTextField(team.getCoachName());
		textField_1.setBounds(120, 99, 134, 28);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblHomeField = new JLabel("Home Field :");
		lblHomeField.setBounds(19, 145, 99, 16);
		add(lblHomeField);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EntityTransaction userTransaction = emDAO.getEM().getTransaction();
				userTransaction.begin();
				team.setName(textField.getText());
				team.setCoachName(textField_1.getText());
				team.setHomeLocation((Location) comboBox.getSelectedItem());
				if (isAdd) {
					team.setLeague(league);
					league.addTeam(team);
				}
				userTransaction.commit();
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TeamSelectionList(league,currentFrame));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(90, 242, 117, 29);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TeamSelectionList(league,currentFrame));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(219, 242, 117, 29);
		add(btnCancel);
		
		DefaultListModel listModel = new DefaultListModel();
		for (Player player : team.getPlayers())
		listModel.addElement(player);
		
		JList list = new JList(listModel);
		list.setBounds(276, 65, 134, 103);
//		add(list);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(276, 65, 134, 103);
		scrollPane.setViewportView(list);
		add(scrollPane);

		
		JButton btnAdd = new JButton("Add ");
		btnAdd.setBounds(241, 180, 74, 29);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new PlayerEdit(new Player(),team,league, currentFrame,isAdd, true));
				currentFrame.getContentPane().revalidate();
			}
		});
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(305, 180, 74, 29);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new PlayerEdit((Player) list.getSelectedValue(),team,league, currentFrame,isAdd, false));
				currentFrame.getContentPane().revalidate();
	
			}
		});
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(370, 180, 74, 29);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Player player = (Player) list.getSelectedValue();
					EntityTransaction userTransaction = emDAO.getEM().getTransaction();
					userTransaction.begin();
					team.removePlayer(player);
					userTransaction.commit();
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TeamEdit(team,league, currentFrame,isAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		add(btnDelete);
		
		comboBox = new JComboBox();
		for (Location location : league.getLocations())
		comboBox.addItem(location);
		if (!isAdd) comboBox.setSelectedItem(team.getHomeLocation());
		comboBox.setBounds(120, 141, 129, 27);
		add(comboBox);
	}
}
