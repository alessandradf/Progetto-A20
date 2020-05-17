package model;

import java.util.ArrayList;

/**
 * Rappresenta le due squadre di scopone
 * 
 * @author Andrea
 *
 */
public class Team {

    private String teamName;
    private ArrayList<Player> playersInTeam;

    public Team(String name) {
        this.teamName = name;
    }
}
