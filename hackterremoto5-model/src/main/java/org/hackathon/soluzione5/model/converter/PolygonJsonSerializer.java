package org.hackathon.soluzione5.model.converter;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.WKTWriter;



public class PolygonJsonSerializer extends JsonSerializer<Polygon> {

	@Override
	public void serialize(Polygon polygon, JsonGenerator arg1,SerializerProvider arg2) throws IOException,JsonProcessingException {
		WKTWriter d = new WKTWriter();
		
		arg1.writeString(d.write(polygon));
		
	}


}
