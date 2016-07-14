package soccerLeagueUI;

import javax.swing.JLabel;
import javax.swing.JPanel;

import soccerLeaguePD.League;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
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
