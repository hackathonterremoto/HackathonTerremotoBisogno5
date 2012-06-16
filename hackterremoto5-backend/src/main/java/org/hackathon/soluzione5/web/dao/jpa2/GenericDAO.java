package org.hackathon.soluzione5.web.dao.jpa2;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

public class GenericDAO {
	
	private final static Logger logger = Logger
	.getLogger(GenericDAO.class.getName());
	
	protected <T> T findItemById(Class<T> clazz, EntityManager em,Long id) {
		return em.find(clazz, id);
	}
	
	protected <T> List<T> findItems(Class<T> clazz, EntityManager em, int firstResult, int maxResults,List<ItemSorting> itemSortings,  Predicate[] predicates) {
	    CriteriaBuilder cBuilder = em.getCriteriaBuilder();
	    CriteriaQuery<T> itemQuery = cBuilder.createQuery(clazz);
	    Root<T> from = itemQuery.from(clazz);
//	    // make predicates
//	    Predicate[] predicates = ItemFilter.makeFilterPredicates(from, itemFilters);
//	    // make orders
	    List<Order> orders = new ArrayList<Order>();
	    if (itemSortings != null && !itemSortings.isEmpty()) {
            for (ItemSorting itemSorting : itemSortings) {
                orders.add("DESC".equals(itemSorting.direction) ? cBuilder.desc(from.get(itemSorting.fieldName)) : cBuilder.asc(from.get(itemSorting.fieldName)));
                logger.info("ORDER BY "+itemSorting.fieldName+ " "+ itemSorting.direction);
            }
        }

	    if ((predicates != null) && (predicates.length > 0) )
	    	itemQuery.select(from).where(predicates).orderBy(orders);
	    else
	    	itemQuery.select(from).orderBy(orders);
	    
	    TypedQuery<T> itemTypedQuery = em.createQuery(itemQuery);
	    if (firstResult < 0)
	    	firstResult = 0;
	    
	    if (maxResults > 0)
	    	return itemTypedQuery.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
	    else
	    	return itemTypedQuery.setFirstResult(firstResult).getResultList();
	}
	
	
	protected <T> List<T> findItems(CriteriaQuery<T> itemQuery,Root<T> from , EntityManager em, int firstResult, int maxResults,List<ItemSorting> itemSortings,  Predicate[] predicates) {
	    try {
	    	CriteriaBuilder cBuilder = em.getCriteriaBuilder();
	  	  
		    List<Order> orders = new ArrayList<Order>();
		    if (itemSortings != null && !itemSortings.isEmpty()) {
	            for (ItemSorting itemSorting : itemSortings) {
	                orders.add("DESC".equals(itemSorting.direction) ? cBuilder.desc(from.get(itemSorting.fieldName)) : cBuilder.asc(from.get(itemSorting.fieldName)));
	                logger.info("ORDER BY "+itemSorting.fieldName+ " "+ itemSorting.direction);
	            }
	        }

		    if ((predicates != null) && (predicates.length > 0) )
		    	itemQuery.select(from).where(predicates).orderBy(orders);
		    else
		    	itemQuery.select(from).orderBy(orders);
		    
		    TypedQuery<T> itemTypedQuery = em.createQuery(itemQuery);
		    if (firstResult < 0)
		    	firstResult = 0;
		    
		    if (maxResults > 0)
		    	return itemTypedQuery.setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
		    else
		    	return itemTypedQuery.setFirstResult(firstResult).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	
	public <T> Long countItems(Class<T> clazz, EntityManager em, Predicate[] predicates) {
	    CriteriaBuilder cBuilder = em.getCriteriaBuilder();
	    CriteriaQuery<Long> totalQuery = cBuilder.createQuery(Long.class);
	    Root<T> from = totalQuery.from(clazz);
	    // make predicates
//	    Predicate[] predicates = ItemFilter.makeFilterPredicates(from, itemFilters);
	    // get total number of items.
	    totalQuery.select(cBuilder.count(from));
	    if ((predicates != null) && (predicates.length > 0) )
	    	totalQuery.where(predicates);
	    TypedQuery<Long> longTypedQuery = em.createQuery(totalQuery);
	    return longTypedQuery.getSingleResult();
	}

	
	public <T> Long countItems(CriteriaQuery<Long> totalQuery,Root<T> from, EntityManager em, Predicate[] predicates) {
	    CriteriaBuilder cBuilder = em.getCriteriaBuilder();
	
	    
	    totalQuery.select(cBuilder.count(from));
	    if ((predicates != null) && (predicates.length > 0) )
	    	totalQuery.where(predicates);
	    TypedQuery<Long> longTypedQuery = em.createQuery(totalQuery);
	    return longTypedQuery.getSingleResult();
	}
	
}
