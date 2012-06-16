package org.hackathon.soluzione5.model.converter;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;

public class PolygonJsonDeserializer extends JsonDeserializer<Polygon> {

	@Override
	public Polygon deserialize(JsonParser arg0, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		
	
		String polyString = arg0.getText();
		
		WKTReader fromText = new WKTReader();
		Polygon polygon = null;
		try {
			polygon = (Polygon) fromText.read( polyString );
			return polygon;
		} catch (ParseException e) {
			throw new IOException(e.getMessage());
		}
	}

}
