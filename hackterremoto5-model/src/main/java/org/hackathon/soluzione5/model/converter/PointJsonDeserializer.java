package org.hackathon.soluzione5.model.converter;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;



public class PointJsonDeserializer extends JsonDeserializer<Point> {

	@Override
	public Point deserialize(JsonParser arg0, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		String pointString = arg0.getText();

		WKTReader fromText = new WKTReader();
		Point point = null;
		try {
			point = (Point) fromText.read( pointString );
			return point;
		} catch (ParseException e) {
			throw new IOException(e.getMessage());
		}
	}

}
