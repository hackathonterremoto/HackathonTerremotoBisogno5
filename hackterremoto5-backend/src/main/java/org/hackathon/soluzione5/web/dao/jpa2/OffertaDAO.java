package org.hackathon.soluzione5.web.dao.jpa2;

import java.util.List;

import org.hackathon.soluzione5.model.Offerta;

public interface OffertaDAO {

	public List<Offerta> findItems(int firstResult, int maxResults,
			List<ItemSorting> sorting);

	public Offerta findById(Long impiantoId);

	public Long countItems();

	public Long remove(Long entityId);

	public Long insert(Offerta entity);

}
