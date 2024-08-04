package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayerPanel extends JPanel {
    private final DefaultListModel<String> playerListModel; // Model for the player list
    private final LeagueInformationSystem leagueInformationSystem; // Reference to the model
    private final JTextField leagueIDField;
    private final JTextField playerNameField;
    private final JTextField playerAgeField;

    public PlayerPanel(LeagueInformationSystem leagueInformationSystem) {
        this.leagueInformationSystem = leagueInformationSystem;
        setLayout(new BorderLayout());

        // Initialize the player list model and JList
        playerListModel = new DefaultListModel<>();

        // List to display players
        JList<String> playerList = new JList<>(playerListModel);
        JScrollPane scrollPane = new JScrollPane(playerList);
        add(scrollPane, BorderLayout.CENTER);


        // Load Players Button
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton loadPlayersButton = new JButton("Load Players");
        loadPlayersButton.addActionListener(_ -> loadPlayers());
        buttonPanel.add(loadPlayersButton);
        add(buttonPanel, BorderLayout.SOUTH);

        //Add Player button implemented w/ JPanel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        add(inputPanel, BorderLayout.SOUTH);
        JButton addPlayersButton = new JButton("Add Player");
        addPlayersButton.addActionListener(_ -> addPlayer());
        buttonPanel.add(addPlayersButton);
        add(buttonPanel, BorderLayout.EAST);


        //add player id
        inputPanel.add(new JLabel("League ID: "));
        leagueIDField = new JTextField();
        inputPanel.add(leagueIDField);

        //add player name
        inputPanel.add(new JLabel("Name: "));
        playerNameField = new JTextField();
        inputPanel.add(playerNameField);

        //add player age
        inputPanel.add(new JLabel("Age: "));
        playerAgeField = new JTextField();
        inputPanel.add(playerAgeField);
    }

    private void loadPlayers() {
        // Fetch all players from the league information system
        List<String> players = leagueInformationSystem.getAllPlayers();
        playerListModel.clear(); // Clear any existing players
        // Add fetched players to the list model
        for (String player : players) {
            playerListModel.addElement(player);
        }
    }

    public void addPlayer() {
        leagueIDField.getText();
        String playerName = playerNameField.getText();
        playerAgeField.getText();
        System.out.println("Added Player " + playerName + " to League.");
    }
}