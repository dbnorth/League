package soccerLeagueUI;

import javax.swing.JPanel;
import javax.swing.JTextField;

import soccerLeaguePD.League;
import javax.swing.JLabel;

public class MainPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainPanel(League league) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel(league.getName());
		lblNewLabel.setBounds(112, 112, 230, 16);
		add(lblNewLabel);

	}
}
