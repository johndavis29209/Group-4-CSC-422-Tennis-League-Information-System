package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TeamPanel extends JPanel {
    private final JTextField teamNameField;
    private final JTextField cityField;
    private final JTextField managerNameField;
    private final DefaultListModel<String> teamListModel;
    private final LeagueInformationSystem leagueInformationSystem;

    public TeamPanel(LeagueInformationSystem leagueInformationSystem) {
        //invoke classes and styling for JPanel
        this.leagueInformationSystem = leagueInformationSystem;
        setLayout(new BorderLayout());

        //Displays Teams on top and styles element
        teamListModel = new DefaultListModel<>();
        JList<String> teamList = new JList<>(teamListModel);
        JScrollPane scrollPane = new JScrollPane(teamList);
        add(scrollPane, BorderLayout.CENTER);

        //create input fields to make record changes
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Team Name:"));       //team name input
        teamNameField = new JTextField();
        inputPanel.add(teamNameField);
        inputPanel.add(new JLabel("City:"));            //team city input
        cityField = new JTextField();
        inputPanel.add(cityField);
        inputPanel.add(new JLabel("Manager Name:"));    //team manager input
        managerNameField = new JTextField();
        inputPanel.add(managerNameField);

        //finalize button
        JButton addButton = new JButton("Add Team");
        addButton.addActionListener(_ -> addTeam());
        inputPanel.add(addButton);

        //Load team data into JList
        JButton loadButton = new JButton("Load Teams");
        loadButton.addActionListener(_ -> loadTeams());
        inputPanel.add(loadButton);
        add(inputPanel, BorderLayout.SOUTH);
    }

    private void loadTeams() {
        List<String> teams = leagueInformationSystem.getAllTeams();
        teamListModel.clear();
        for (String team : teams) {
            teamListModel.addElement(team);
        }
    }

    public void addTeam() {
        String teamName = teamNameField.getText();
        String city = cityField.getText();
        String managerName = managerNameField.getText();

        System.out.println("Added Team: " + teamName + " from " + city + " managed by " + managerName);
    }
}