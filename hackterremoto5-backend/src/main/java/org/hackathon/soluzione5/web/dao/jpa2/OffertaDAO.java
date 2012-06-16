package org.hackathon.soluzione5.web.dao.jpa2;

import java.util.Date;
import java.util.List;

import org.hackathon.soluzione5.model.Offerta;

import com.vividsolutions.jts.geom.Point;

public interface OffertaDAO {

	public Offerta findById(Long impiantoId);

	public Long countItems();

	public Long remove(Long entityId);

	public Long insert(Offerta entity);

	public List<Offerta> findItems(Point center, Double radius, String tipologia,
			Integer postiLettoMin, Date da, Date finoA, Boolean confermata);

}
