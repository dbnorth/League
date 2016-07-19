package soccerLeagueUI;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import soccerLeaguePD.League;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create the panel.
	 */
	public MainPanel(League league) {
		Box vbox = Box.createVerticalBox();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		vbox.add(Box.createVerticalGlue());
		Box hbox = Box.createHorizontalBox();
		hbox.add(Box.createHorizontalGlue());
		
		JLabel lblNewLabel = new JLabel(league.getName());
//		lblNewLabel.setBounds(112, 112, 230, 16);
		hbox.add(lblNewLabel);
		
		hbox.add(Box.createHorizontalGlue());
		vbox.add(hbox);
		vbox.add(Box.createVerticalGlue());
		
		add(vbox);
	}
}
