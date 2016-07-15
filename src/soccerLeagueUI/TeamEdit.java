package soccerLeagueUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import soccerLeagueDAO.emDAO;
import soccerLeaguePD.League;
import soccerLeaguePD.Team;

import javax.persistence.EntityTransaction;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TeamEdit extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public TeamEdit(Team team, League league, JFrame currentFrame, boolean isAdd) {
		setLayout(null);
		
		JLabel lblTeamEdit = new JLabel("Team Edit");
		lblTeamEdit.setBounds(170, 31, 84, 16);
		add(lblTeamEdit);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(146, 69, 61, 16);
		add(lblName);
		
		textField = new JTextField(team.getName());
		textField.setBounds(219, 63, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblCoach = new JLabel("Coach :");
		lblCoach.setBounds(146, 109, 61, 16);
		add(lblCoach);
		
		textField_1 = new JTextField(team.getCoachName());
		textField_1.setBounds(219, 103, 134, 28);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblHomeField = new JLabel("Home Field :");
		lblHomeField.setBounds(118, 149, 99, 16);
		add(lblHomeField);
		
		textField_2 = new JTextField();
		textField_2.setBounds(219, 143, 134, 28);
		add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EntityTransaction userTransaction = emDAO.getEM().getTransaction();
				userTransaction.begin();
				team.setName(textField.getText());
				team.setCoachName(textField_1.getText());
				if (isAdd) league.addTeam(team);
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

	}

}
