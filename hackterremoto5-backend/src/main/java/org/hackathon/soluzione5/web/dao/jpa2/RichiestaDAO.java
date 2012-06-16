package org.hackathon.soluzione5.web.dao.jpa2;

import java.util.Date;
import java.util.List;

import org.hackathon.soluzione5.model.Richiesta;

import com.vividsolutions.jts.geom.Point;

public interface RichiestaDAO {

	public Long insert(Richiesta entity);

	public Long remove(Long entityId);

	public Long countItems();

	public Richiesta findById(Long impiantoId);

	public List<Richiesta> findItems(Point center, Double radius, String tipologia,
			Integer postiLettoMin, Date da, Date finoA);

}
