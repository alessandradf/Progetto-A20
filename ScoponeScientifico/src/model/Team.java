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
		playersInTeam = new ArrayList<Player>();
	}

	/*
	 * Aggiunge un Player al Team. Controlla prima se � gi� stato aggiunto
	 */

	public boolean addPlayer(Player p) {
		if (!(this.playersInTeam.contains(p))) {
			this.playersInTeam.add(p);
			return true;
		}
		return false;
	}

}
