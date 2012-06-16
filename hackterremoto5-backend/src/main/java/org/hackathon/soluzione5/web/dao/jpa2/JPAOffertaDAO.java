
package org.hackathon.soluzione5.web.dao.jpa2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
	public List<Offerta> findItems(String tipologia, Integer postiLettoMin, Date da, Date finoA) {
		

		CriteriaBuilder cb = em.getCriteriaBuilder();
		//create predicate for filtering
		CriteriaQuery<Offerta> itemQuery = cb.createQuery(Offerta.class);
		Root<Offerta> from = itemQuery.from(Offerta.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if ((tipologia != null) && (! tipologia.equals("")) )
			predicates.add(cb.equal(from.get("tipologia"), tipologia));

		return super.findItems(itemQuery,from,em, 0,0,null,predicates.toArray(new Predicate[0]));
		
	}

}
