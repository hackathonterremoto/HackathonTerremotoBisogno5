package org.hackathon.soluzione5.model.converter;



import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;

public class PolygonXstreamConverter implements Converter {

	public void marshal(Object value, HierarchicalStreamWriter writer, MarshallingContext arg2) {


		WKTWriter d = new WKTWriter();
		Polygon polygon = (Polygon) value;
		writer.setValue( d.write(polygon) );

	}

	public Object unmarshal(HierarchicalStreamReader reader,UnmarshallingContext arg1) {

		WKTReader fromText = new WKTReader();
		Polygon bbox = null;
		try {
			bbox = (Polygon) fromText.read( reader.getValue() );
			return bbox;
		} catch (ParseException e) {

			e.printStackTrace();
			return null;
		}


	}

	public boolean canConvert(Class clazz) {
		return clazz.equals(Polygon.class);
	}

}
