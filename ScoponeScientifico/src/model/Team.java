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
    
    /*
     * Aggiunge un Player al Team.
     * Controlla prima se è già stato aggiunto
     */
    public boolean addPlayer(Player p) {
    	if(!this.playersInTeam.contains(p)) {
    		this.playersInTeam.add(p);
    		return true;
    	}
    	return false;
    }	
}
