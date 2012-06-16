package org.hackathon.soluzione5.web.dao.jpa2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;

public class ItemFilter {

    private final String fieldName;
    private final String value;

    public ItemFilter(String fieldName, String value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    public static List<ItemFilter> decodeJson(String json) throws JsonParseException, JsonMappingException, IOException {
        List<ItemFilter> itemFilters = new ArrayList<ItemFilter>();
        if (json != null) {
        	ObjectMapper mapper = new ObjectMapper();
        	ArrayList<HashMap<String,String>> filterList = mapper.readValue(json, TypeFactory.collectionType(ArrayList.class, TypeFactory.mapType(HashMap.class, String.class, String.class) ));
           // List<HashMap<String,String>> filterList = new JSONDeserializer<ArrayList<HashMap<String,String>>>().deserialize(json);
            for (HashMap<String,String> filter : filterList) {
                String fieldName = filter.get("property");
                String direction = filter.get("value");
                itemFilters.add(new ItemFilter(fieldName, direction));
            }
        }
        return itemFilters;
    }

	public String getFieldName() {
		return fieldName;
	}

	public String getValue() {
		return value;
	}

   /* public static Predicate[] makeFilterPredicates(Root<Item> from, List<ItemFilter> itemFilters) {
        Predicate[] predicates = new Predicate[0];
        if (itemFilters != null && !itemFilters.isEmpty()) {
            List<Predicate> predicateList = new ArrayList<Predicate>();
            for (ItemFilter itemFilter : itemFilters) {
                if ("description".equals(itemFilter.fieldName)) {
                    predicateList.add(descriptionPredicate(from, itemFilter, predicateList));
                }
            }
            predicates = predicateList.toArray(predicates);
        }
        return predicates;
    }*/

   /* private static Predicate descriptionPredicate(Root<Item> from, ItemFilter itemFilter, List<Predicate> predicateList) {
        Expression<String> path = from.get(itemFilter.fieldName);
        CriteriaBuilder cBuilder = Item.entityManager().getCriteriaBuilder();
        return cBuilder.like(path, "%" + itemFilter.value + "%");
    }*/

}
