package gui;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeagueInformationSystem {

    //invoke connection
    private Connection con;
    public LeagueInformationSystem() {
        createConnection();
    }

    //establish jdbc connection
    void createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TennisLeague", "john", "green123");
            System.out.println("Database connection established");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(LeagueInformationSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //function to fetch data from db to populate TeamPanel
    public List<String> getAllTeams() {
        List<String> teams = new ArrayList<>();
        String query = "SELECT * FROM Team";

        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String teamName = rs.getString("Name");
                String city = rs.getString("City");
                String managerName = rs.getString("ManagerName");
                teams.add(teamName + " (" + city + "), Manager: " + managerName);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeagueInformationSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return teams;
    }

    //function to fetch data from db to populate PlayerPanel
    public List<String> getAllPlayers() {
        List<String> players = new ArrayList<>();
        String query = "SELECT p.PlayerID, p.Name AS PlayerName, t.Name AS TeamName " + "FROM Player p " + "JOIN PlayerTeamAssociation pta ON p.PlayerID = pta.PlayerID " + "JOIN Team t ON pta.TeamNumber = t.TeamNumber"; // Joining tables
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                // Fetching the player name and team name from the result set
                String playerName = rs.getString("PlayerName");
                String teamName = rs.getString("TeamName");
                players.add(playerName + " (Team: " + teamName + ")");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeagueInformationSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return players;
    }

    //function to fetch data from db to populate CoachPanel
    public List<String> getAllCoaches() {
        List<String> coaches = new ArrayList<>();
        String query = "SELECT * FROM Coach";

        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int coachID = rs.getInt("CoachID");
                String name = rs.getString("Name");
                String phone = rs.getString("TelephoneNumber");
                int teamNumber = rs.getInt("TeamNumber");
                coaches.add(coachID + ": " + name + " (Phone: " + phone + "), TeamNumber: " + teamNumber);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LeagueInformationSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
        return coaches;
    }
}