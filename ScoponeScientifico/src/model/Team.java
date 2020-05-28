package model;

import java.util.ArrayList;

/**
 * Rappresenta le due squadre di scopone
 * 
 * @author Stefano
 *
 */
public class Team {

	private String teamName;
	private ArrayList<Player> playersInTeam;

	public Team(String name) {
		this.teamName = name;
		playersInTeam = new ArrayList<Player>();
	}

	/**
	 * Aggiunge un Player al Team. Controlla prima se � gi� stato aggiunto
	 */

	public boolean addPlayer(Player p) {
		/*
		 * Nota: il metodo contains restituisce vero se esiste p tale che p.equals(e) == true
		 * ma il metodo equals di Player NON È stato ridefinito, quindi questo controllo restituisce true se l'indirizzo
		 * dell'istanza che viene passata già esiste nell'ArrayList 
		 * -il vostro pedante Andrea
		 */
		if (!(this.playersInTeam.contains(p))) {
			this.playersInTeam.add(p);
			return true;
		}
		return false;
	}

	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * @return the playersInTeam
	 */
	public ArrayList<Player> getPlayersInTeam() {
		return playersInTeam;
	}
	
	
}
