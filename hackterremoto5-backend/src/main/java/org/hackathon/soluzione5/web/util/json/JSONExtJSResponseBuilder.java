package org.hackathon.soluzione5.web.util.json;

import java.util.Map;

/**
 * @author fulvio
 *
 */
public class JSONExtJSResponseBuilder {
	
	public static String buildJSONObject(Map<String, Object> properties) throws JSONException {
		JSONStringer out = new JSONStringer();
		out.object();
		for (Map.Entry<String, Object> property : properties.entrySet()) {
			out.key(property.getKey()).value(property.getValue());
		}
		out.endObject();
		return out.toString();
	}

}
