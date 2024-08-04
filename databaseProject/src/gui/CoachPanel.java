package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CoachPanel extends JPanel {
    private final LeagueInformationSystem leagueInformationSystem;
    private final JTextField coachNameField;
    private final JTextField telephoneField;
    private final JComboBox<String> teamComboBox; // Populate with teams from the DB
    private final DefaultListModel<String> coachListModel; // Model for the list

    public CoachPanel(LeagueInformationSystem leagueInformationSystem) {
        //Initialize classes and set style
        this.leagueInformationSystem = leagueInformationSystem;
        setLayout(new BorderLayout());

        // Initialize the list model and JList for coaches
        coachListModel = new DefaultListModel<>();
        JList<String> coachList = new JList<>(coachListModel);
        JScrollPane scrollPane = new JScrollPane(coachList);
        scrollPane.setPreferredSize(new Dimension(200, 250));
        add(scrollPane, BorderLayout.NORTH);

        // Populate the coach list with existing coaches from the database
        loadCoaches();


        //Initialize input buttons and set styling
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        add(inputPanel, BorderLayout.CENTER);
        inputPanel.add(new JLabel("Coach Name: "));
        coachNameField = new JTextField();
        inputPanel.add(coachNameField);

        inputPanel.add(new JLabel("Telephone: "));
        telephoneField = new JTextField();
        inputPanel.add(telephoneField);

        teamComboBox = new JComboBox<>();
        populateTeams();
        inputPanel.add(new JLabel("Team:"));
        inputPanel.add(teamComboBox);

        JButton addButton = new JButton("Add Coach");
        addButton.addActionListener(_ -> addCoach());
        inputPanel.add(addButton);
    }

    // Method to fetch all coaches from DB
    private void loadCoaches() {
        List<String> coaches = leagueInformationSystem.getAllCoaches();
        for (String coach : coaches) {
            coachListModel.addElement(coach); // Add each coach to the list model
        }
    }

    // Add the new coach to the display list
    public void addCoach() {
        String coachName = coachNameField.getText();
        String telephone = telephoneField.getText();
        String team = (String) teamComboBox.getSelectedItem();
        coachListModel.addElement(coachName); // Update the list with the new coach
        System.out.println("Added Coach: " + coachName + " Telephone: " + telephone + " Team: " + team);

        // Resets input fields after adding coach
        coachNameField.setText("");
        telephoneField.setText("");
        teamComboBox.setSelectedIndex(-1);
    }

    //function to populate teams from db to dropdown box in TeamComboBox
    public void populateTeams() {
        List<String> teams = leagueInformationSystem.getAllTeams();
        for(String team : teams) {
            teamComboBox.addItem(team);
        }
    }
}