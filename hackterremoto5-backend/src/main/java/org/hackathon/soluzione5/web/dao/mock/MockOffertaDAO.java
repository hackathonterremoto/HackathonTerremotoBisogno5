package org.hackathon.soluzione5.web.dao.mock;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hackathon.soluzione5.model.Offerta;
import org.hackathon.soluzione5.web.dao.jpa2.OffertaDAO;
import org.springframework.stereotype.Component;

import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

@Component
public class MockOffertaDAO implements OffertaDAO {

	@Override
	public Offerta findById(Long impiantoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countItems() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long remove(Long entityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long insert(Offerta entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Offerta> findItems(Point center, Double radius,String tipologia, Integer postiLettoMin, Date da, Date finoA) {

		ArrayList<Offerta> offerte = new ArrayList<Offerta>();


		WKTReader fromText = new WKTReader();


		Point point;
		try {
			point = (Point) fromText.read("POINT(44.486709 11.340637)");
			Offerta offerta1 = new Offerta(point, read("/app.jpg"), read("/app.jpg"), read("/app.jpg"), "appartmento", 2, new Date(), new Date(), "internet", true, "test");
			Offerta offerta2 = new Offerta(point, read("/app.jpg"), read("/app.jpg"), read("/app.jpg"), "appartmento2", 5, new Date(), new Date(), "idro massaggio e migliarini come servo", false, "test2");
			
			
			offerte.add(offerta1);	
			offerte.add(offerta2);
			
			return offerte;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	
	}



	protected byte[] read(String classPathFile) throws Exception {

		ByteArrayOutputStream ous = null;
		InputStream ios = null;
		try {
			byte []buffer = new byte[4096];
			ous = new ByteArrayOutputStream();
			int read = 0;
			ios = this.getClass().getResourceAsStream(classPathFile);
			while ( (read = ios.read(buffer)) != -1 ) {
				ous.write(buffer, 0, read);
			}
		} finally { 
			try {
				if ( ous != null ) 
					ous.close();
			} catch ( IOException e) {
			}

			try {
				if ( ios != null ) 
					ios.close();
			} catch ( IOException e) {
			}
		}
		return ous.toByteArray();
	}

}
