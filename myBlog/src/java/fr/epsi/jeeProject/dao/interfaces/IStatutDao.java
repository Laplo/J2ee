package java.fr.epsi.jeeProject.dao.interfaces;

import java.util.List;

import java.fr.epsi.jeeProject.beans.Statut;

public interface IStatutDao {

	public final static Integer TEMPORAIRE = 1;
	public final static Integer PUBLIE = 2;
	public final static Integer ARCHIVE = 3;
	public final static Integer ANNULE = 3;
	
	Statut getStatut(Integer id);
	List<Statut> getListOfStatuts();
}
