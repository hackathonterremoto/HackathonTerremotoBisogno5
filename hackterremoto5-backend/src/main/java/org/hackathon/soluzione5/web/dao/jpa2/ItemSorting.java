package org.hackathon.soluzione5.web.dao.jpa2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import flexjson.JSONDeserializer;

public class ItemSorting {

    public final String fieldName;
    public final String direction;

    public ItemSorting(String fieldName, String direction) {
        this.fieldName = fieldName;
        this.direction = direction;
    }

    public static List<ItemSorting> decodeJson(String json) {
        List<ItemSorting> sorts = new ArrayList<ItemSorting>();
        if (json != null) {
            List<HashMap<String,String>> sortList = new JSONDeserializer<ArrayList<HashMap<String,String>>>().deserialize(json);
            for (HashMap<String,String> sort : sortList) {
                String fieldName = sort.get("property");
                String direction = sort.get("direction");
                sorts.add(new ItemSorting(fieldName, direction));
            }
        }
        return sorts;
    }

   

}
