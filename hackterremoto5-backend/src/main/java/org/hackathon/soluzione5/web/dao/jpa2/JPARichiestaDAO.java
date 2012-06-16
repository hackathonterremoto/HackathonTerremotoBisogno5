package org.hackathon.soluzione5.web.dao.jpa2;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.hackathon.soluzione5.model.Offerta;
import org.hackathon.soluzione5.model.Richiesta;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

@Component
public class JPARichiestaDAO extends GenericDAO implements RichiestaDAO {

	@PersistenceContext
	private EntityManager em;

	private final static Logger logger = Logger.getLogger(JPARichiestaDAO.class
			.getName());

	@Override
	@Transactional
	public Long insert(Richiesta entity) {
		em.persist(entity);
		return entity.getEntityId();
	}

	@Override
	@Transactional
	public Long remove(Long entityId) {
		if (entityId == null)
			return -1L;
		Richiesta entity = em.find(Richiesta.class, entityId);
		if (entity == null)
			return -1L;

		em.remove(entity);
		return entityId;
	}

	@Override
	@Transactional
	public Long countItems() {
		return super.countItems(Richiesta.class, em, null);
	}

	@Override
	public Richiesta findById(Long impiantoId) {
		return super.findItemById(Richiesta.class, em, impiantoId);
	}

	@Override
	public List<Richiesta> findItems(Point center, Double radius,
			String tipologia, Integer postiLettoMin, Date da, Date finoA) {

		Session session = (Session) em.getDelegate();

		Criteria testCriteria = session.createCriteria(Richiesta.class);

		String poly = "POLYGON((" +

		Double.toString(center.getX() - radius) + " "
				+ Double.toString(center.getY() - radius) + "," +

				Double.toString(center.getX() + radius) + " "
				+ Double.toString(center.getY() - radius) + "," +

				Double.toString(center.getX() + radius) + " "
				+ Double.toString(center.getY() + radius) + "," +

				Double.toString(center.getX() - radius) + " "
				+ Double.toString(center.getY() + radius) + "," +

				Double.toString(center.getX() - radius) + " "
				+ Double.toString(center.getY() - radius) +

				")) ";

		WKTReader fromText = new WKTReader();

		Geometry bbox = null;

		try {

			bbox = fromText.read(poly);

		} catch (ParseException e) {

			e.printStackTrace();

			return null;

		}

		testCriteria
				.add(

				Restrictions.and(
						Restrictions
								.sqlRestriction("Intersects( GeomFromText('"
										+ poly
										+ "'),  StartPoint( ExteriorRing(location))  ) "),
						Restrictions
								.sqlRestriction("( 6371*2*ATAN2( "
										+

										"                                                                                                                                                                                                                       SQRT( POWER( SIN((  X(StartPoint(ExteriorRing(location))) - abs("
										+ center.getX()
										+ ")   ) * pi()/180/2), 2) + COS(X(StartPoint(ExteriorRing(location))) * pi() / 180) * COS(abs("
										+ center.getX()
										+ ")*pi()/180 ) * POWER(SIN((Y(StartPoint(ExteriorRing(location))) - "
										+ center.getY()
										+ ")*pi()/180/2),2)), "
										+

										"SQRT( 1 -  POWER( SIN((  X(StartPoint(ExteriorRing(location))) - abs("
										+ center.getX()
										+ ")   ) * pi()/180/2), 2) + COS(X(StartPoint(ExteriorRing(location))) * pi() / 180) * COS(abs("
										+ center.getX()
										+ ")*pi()/180 ) * POWER(SIN((Y(StartPoint(ExteriorRing(location))) - "
										+ center.getY()
										+ ")*pi()/180/2),2))"
										+

										"                                                                                                                                                                                                                        ) ) < "
										+ radius)));

		
		
		if ((tipologia != null) && (!tipologia.equals("")))
			testCriteria.add(Restrictions.eq("tipologia", tipologia));

		if (postiLettoMin != null)
			testCriteria.add(Restrictions.ge("postiLetto", postiLettoMin));
		
		if (da != null)
			testCriteria.add(Restrictions.le("disponibileDa", da));
		if (finoA != null)
			testCriteria.add(Restrictions.ge("disponibileFino", finoA));
		List<Richiesta> results = testCriteria.list();

		return results;

//		CriteriaBuilder cb = em.getCriteriaBuilder();
//		// create predicate for filtering
//		CriteriaQuery<Offerta> itemQuery = cb.createQuery(Offerta.class);
//		Root<Offerta> from = itemQuery.from(Offerta.class);

//		List<Predicate> predicates = new ArrayList<Predicate>();
//		
//		if (postiLettoMin != null)
//			predicates.add(cb.greaterThanOrEqualTo(
//					from.get("postiLetto").as(Integer.class), postiLettoMin));
//
//		if (da != null)
//			predicates.add(cb.lessThanOrEqualTo(
//					from.get("disponibileDa").as(Date.class), da));
//		if (finoA != null)
//			predicates.add(cb.greaterThanOrEqualTo(from.get("disponibileFino")
//					.as(Date.class), finoA));
//
//		
//		return results;
		
	
	}

}
