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

public class PlayerEdit extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	/**
	 * Create the panel.
	 */
	public PlayerEdit(Player player, Team team, League league, JFrame currentFrame, boolean teamIsAdd, boolean isAdd) {
		
		if (!isAdd) emDAO.getEM().refresh(player);
		
		setLayout(null);
		
		JLabel lblTeamEdit = new JLabel("Player Edit");
		lblTeamEdit.setBounds(170, 31, 84, 16);
		add(lblTeamEdit);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(47, 65, 61, 16);
		add(lblName);
		
		textField = new JTextField(player.getName());
		textField.setBounds(120, 59, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNumber = new JLabel("Number :");
		lblNumber.setBounds(47, 93, 61, 16);
		add(lblNumber);
		
		textField_1 = new JTextField(player.getNumber());
		textField_1.setBounds(120, 87, 134, 28);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblPosition = new JLabel("Position :");
		lblPosition.setBounds(47, 121, 61, 16);
		add(lblPosition);
		
		textField_2 = new JTextField(player.getPosition());
		textField_2.setBounds(120, 115, 134, 28);
		add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EntityTransaction userTransaction = emDAO.getEM().getTransaction();
				userTransaction.begin();
				player.setName(textField.getText());
				player.setNumber(textField_1.getText());
				player.setPosition(textField_2.getText());
				if (isAdd) {
					player.setTeam(team);
					team.addPlayer(player);
				}
				userTransaction.commit();
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TeamEdit(team,league,currentFrame,teamIsAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(90, 242, 117, 29);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TeamEdit(team,league,currentFrame,teamIsAdd));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(219, 242, 117, 29);
		add(btnCancel);
		

	}
}


