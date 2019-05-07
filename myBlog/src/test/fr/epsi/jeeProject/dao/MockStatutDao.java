package test.fr.epsi.jeeProject.dao;

import java.util.ArrayList;
import java.util.List;

import fr.epsi.jeeProject.beans.Statut;
import fr.epsi.jeeProject.dao.interfaces.IStatutDao;

public class MockStatutDao implements IStatutDao {

	private static List<Statut> listOfStatuts;
	
	@Override
	public Statut getStatut(Integer id) {
		for (Statut s: getAllStatut()) {
			if (s.getId().intValue() == id.intValue()) {
				return s;
			}
		}
		return null;
	}

	@Override
	public List<Statut> getAllStatut() {
		return getPrivateListOfStatuts();
	}

	private List<Statut> getPrivateListOfStatuts() {
		if (listOfStatuts == null) {
			listOfStatuts = new ArrayList<>();
			Statut statut = new Statut();
			statut.setId(1);
			statut.setDescription("Temporaire");
			listOfStatuts.add(statut);
			
			statut = new Statut();
			statut.setId(2);
			statut.setDescription("Publi�");
			listOfStatuts.add(statut);
			
			statut = new Statut();
			statut.setId(3);
			statut.setDescription("Archiv�");
			listOfStatuts.add(statut);
			
			statut = new Statut();
			statut.setId(4);
			statut.setDescription("Annul�");
			listOfStatuts.add(statut);
			
		}
		return listOfStatuts;
	}
}
