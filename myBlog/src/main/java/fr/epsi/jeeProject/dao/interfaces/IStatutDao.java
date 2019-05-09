package fr.epsi.jeeProject.dao.interfaces;

import java.util.List;

import fr.epsi.jeeProject.beans.Statut;

public interface IStatutDao {

	Integer TEMPORAIRE = 1;
	Integer PUBLIE = 2;
	Integer ARCHIVE = 3;
	Integer ANNULE = 3;
	
	Statut getStatut(Integer id) throws ClassNotFoundException;
	List<Statut> getAllStatut();
}
