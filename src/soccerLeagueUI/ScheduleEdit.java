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
import soccerLeaguePD.Game;
import soccerLeaguePD.League;
import soccerLeaguePD.Location;
import soccerLeaguePD.Player;
import soccerLeaguePD.Schedule;
import soccerLeaguePD.Team;

public class ScheduleEdit extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JTextField textField;
	private JTextField textField_1;
	JComboBox<Location> comboBox;
	private JTextField textField_2;

	/**
	 * Create the panel.
	 */
	public ScheduleEdit(Schedule schedule, League league, JFrame currentFrame, boolean isAdd) {
		
		emDAO.getEM().refresh(league);
		if (!isAdd) emDAO.getEM().refresh(schedule);
		
		setLayout(null);
		
		JLabel lblTeamEdit = new JLabel("Schedule Edit");
		lblTeamEdit.setBounds(170, 31, 84, 16);
		add(lblTeamEdit);
		
		JLabel lblName = new JLabel("Season :");
		lblName.setBounds(20, 65, 61, 16);
		add(lblName);
		
		textField = new JTextField(schedule.getSeason());
		textField.setBounds(93, 59, 134, 28);
		add(textField);
		textField.setColumns(10);

		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EntityTransaction userTransaction = emDAO.getEM().getTransaction();
				userTransaction.begin();
				schedule.setSeason(textField.getText());
				schedule.setStartDate(textField_2.getText());
				if (isAdd) {
					schedule.setLeague(league);
					league.addSchedule(schedule);
				}
				userTransaction.commit();
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ScheduleSelectionList(league,currentFrame));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnSave.setBounds(98, 242, 117, 29);
		add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ScheduleSelectionList(league,currentFrame));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnCancel.setBounds(227, 242, 117, 29);
		add(btnCancel);
		
		DefaultListModel listModel = new DefaultListModel();
		if (schedule.getGames() != null)
		{
			for (Game game : schedule.getGames())
				listModel.addElement(game);
		}
		
		JList list = new JList(listModel);
		list.setBounds(250, 65, 180, 103);
//		add(list);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(250, 65, 180, 103);
		scrollPane.setViewportView(list);
		add(scrollPane);

		JButton btnAdd = new JButton("Add ");
		btnAdd.setBounds(241, 180, 74, 29);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new GameEdit(new Game(),schedule,league, currentFrame,isAdd, true));
				currentFrame.getContentPane().revalidate();
			}
		});
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(305, 180, 74, 29);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new GameEdit((Game) list.getSelectedValue(),schedule,league, currentFrame,isAdd, false));
				currentFrame.getContentPane().revalidate();
	
			}
		});
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(370, 180, 74, 29);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game game = (Game) list.getSelectedValue();
					EntityTransaction userTransaction = emDAO.getEM().getTransaction();
					userTransaction.begin();
					schedule.removeGame(game);
					userTransaction.commit();
				
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new ScheduleEdit(schedule,league, currentFrame,isAdd));
					currentFrame.getContentPane().revalidate();
			}
		});
		add(btnDelete);
		
		textField_2 = new JTextField(schedule.getStartDateString());
		textField_2.setBounds(93, 99, 134, 26);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblStartDate = new JLabel("Start Date :");
		lblStartDate.setBounds(6, 104, 85, 16);
		add(lblStartDate);
		
		JButton btnGenerateGames = new JButton("Generate Games");
		btnGenerateGames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EntityTransaction userTransaction = emDAO.getEM().getTransaction();
				userTransaction.begin();
				schedule.generateSchedule();
				userTransaction.commit();
			
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ScheduleEdit(schedule,league, currentFrame,isAdd));
				currentFrame.getContentPane().revalidate();
				
			}
		});
		btnGenerateGames.setBounds(56, 159, 134, 29);
		add(btnGenerateGames);
		
	}
}

