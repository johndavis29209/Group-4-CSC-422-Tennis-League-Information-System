package gui;

import javax.swing.*;
import java.awt.*;

//Frame Containing panes for team, coach and player tables.
public class MainFrame extends JFrame {

    public MainFrame() {
        //invoke instance of Information System and styling for JFrame
        LeagueInformationSystem leagueInformationSystem = new LeagueInformationSystem();
        //styling for frame
        setTitle("Team 4 Assignment 4 CSC 422 League Information System");
        setSize(900, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("src/gui/img/icon.png");
        setIconImage(img.getImage());

        //Tables are displayed using tabbed panes for each table
        JTabbedPane tabbedPane = new JTabbedPane();

        tabbedPane.add("Teams", new TeamPanel(leagueInformationSystem));         // Teams Pane
        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);

        tabbedPane.add("Players", new PlayerPanel(leagueInformationSystem));        //Players Pane
        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);

        tabbedPane.add("Coaches", new CoachPanel(leagueInformationSystem));        //Coaches Pane
        add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }
}