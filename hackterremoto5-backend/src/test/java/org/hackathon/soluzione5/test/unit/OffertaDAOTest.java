package org.hackathon.soluzione5.test.unit;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import junit.framework.Assert;

import org.hackathon.soluzione5.model.Offerta;
import org.hackathon.soluzione5.web.dao.jpa2.OffertaDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.geom.impl.CoordinateArraySequence;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/META-INF/spring/applicationContext.xml" })
public class OffertaDAOTest {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	OffertaDAO offertaDAO;

	@Test
	public void testInjection() {
		Assert.assertNotNull(em);
		Assert.assertNotNull(offertaDAO);
	}

	@Test
	public void testInsertionAndRemoval() throws ParseException{
		Calendar today = new GregorianCalendar();
		Calendar nextMonth = new GregorianCalendar();
		nextMonth.add(Calendar.HOUR, 24*30);
		/*GeometryFactory factory = new GeometryFactory(new PrecisionModel(100.0));
	    CoordinateArraySequence coords = new CoordinateArraySequence(new Coordinate[] {new Coordinate(
	            12.3456, 34567.3456)});
	    Point location = new Point(coords, factory);*/
	    

		WKTReader fromText = new WKTReader();


		Point  location = (Point) fromText.read("POINT(44.486709 11.340637)");
	    
		Offerta offerta = new Offerta(location, "v. parigi 11, bologna", new byte[]{'1','2','3'}, new byte[]{'1','2','3'}, new byte[]{'1','2','3'}, "APPARTAMENTO", new Integer(5), today.getTime(), nextMonth.getTime(), "CUCINA", false, "Note");
		Offerta offertaConfermata = new Offerta(location,  "v. parigi 11, bologna",new byte[]{'1','2','3'}, new byte[]{'1','2','3'}, new byte[]{'1','2','3'}, "APPARTAMENTO", new Integer(5), today.getTime(), nextMonth.getTime(), "CUCINA", false, "Note");
		offertaConfermata.setConfermata(true);
		
		offertaDAO.insert(offerta);
		offertaDAO.insert(offertaConfermata);
		
		List<Offerta> offerte = offertaDAO.findItems(location, new Double(30000), null, null, null, null, null);
		
		Assert.assertNotNull(offerte);
		Assert.assertEquals(offerte.size(), 2);
		
		offertaDAO.remove(offerte.get(0).getEntityId());
		offertaDAO.remove(offerte.get(1).getEntityId());
		
		offerte = offertaDAO.findItems(location, new Double(30000), null, null, null, null, null);
		Assert.assertNotNull(offerte);
		Assert.assertEquals(offerte.size(), 0);
		
		
		
	}
}
