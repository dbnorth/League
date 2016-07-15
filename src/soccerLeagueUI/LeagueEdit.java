package soccerLeagueUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import soccerLeagueDAO.emDAO;
import soccerLeaguePD.League;

import javax.persistence.EntityTransaction;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LeagueEdit extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public LeagueEdit(League league, JFrame currentFrame) {
		setLayout(null);
		
		JLabel lblLeagueEdit = new JLabel("League Edit");
		lblLeagueEdit.setBounds(178, 28, 92, 16);
		add(lblLeagueEdit);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setBounds(161, 78, 49, 16);
		add(lblName);
		
		textField = new JTextField(league.getName());
		textField.setBounds(215, 72, 217, 28);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblCommisioner = new JLabel("Commisioner :");
		lblCommisioner.setBounds(115, 106, 92, 16);
		add(lblCommisioner);
		
		textField_1 = new JTextField(league.getCommissioner());
		textField_1.setBounds(215, 100, 207, 22);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EntityTransaction userTransaction = emDAO.getEM().getTransaction();
				userTransaction.begin();
				league.setName(textField.getText());
				league.setCommissioner(textField_1.getText());
				userTransaction.commit();
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new MainPanel(league));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(106, 200, 117, 29);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new MainPanel(league));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(232, 200, 117, 29);
		add(btnCancel);

	}
}
