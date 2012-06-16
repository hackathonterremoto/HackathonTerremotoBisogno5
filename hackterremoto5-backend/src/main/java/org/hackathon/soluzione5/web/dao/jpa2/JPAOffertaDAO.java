
package org.hackathon.soluzione5.web.dao.jpa2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.hackathon.soluzione5.model.Offerta;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class JPAOffertaDAO extends GenericDAO implements OffertaDAO {

	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger
			.getLogger(JPAOffertaDAO.class.getName());

	@Override
	@Transactional
	public Long insert(Offerta entity) {
		em.persist(entity);
		return entity.getEntityId();
	}

	@Override
	@Transactional
	public Long remove(Long entityId) {
		if (entityId == null)
			return -1L;
		Offerta entity = em.find(Offerta.class, entityId);
		if (entity == null)
			return -1L;

		em.remove(entity);
		return entityId;
	}

	@Override
	@Transactional
	public Long countItems() {
		return super.countItems(Offerta.class,em,null);
	}
	
	@Override
	public Offerta findById(Long impiantoId) {
		return super.findItemById(Offerta.class,em,impiantoId);
	}

	@Override
	public List<Offerta> findItems(int firstResult, int maxResults,
			List<ItemSorting> sorting) {
		// TODO Auto-generated method stub
		return null;
	}

}
