package com.neilmao;


import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: neil
 * Date: 29/09/14
 * Time: 2:34 PM
 */
public class JSONParser
{

    private Map<String, Model> models;

    private Map<String, Store> stores;


    public JSONParser() {
       models = Model.prepareModels();
    }

    public void parseStores(InputStream inputStream) throws IOException {

        stores = new LinkedHashMap<String, Store>();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = mapper.readTree(inputStream);

        JsonNode nodeUpdateTime = rootNode.path("updatedTime");
        Store.setUPDATED_TIME(nodeUpdateTime.getTextValue());

        JsonNode nodeStores = rootNode.path("stores");

        Iterator<JsonNode> iterator = nodeStores.getElements();
        while (iterator.hasNext()) {
            JsonNode storeNode = iterator.next();
            String code = storeNode.path("storeNumber").getTextValue();
            String name = storeNode.path("storeName").getTextValue();
            boolean enabled = storeNode.path("storeEnabled").getBooleanValue();

            stores.put(code, new Store(name, code, enabled));
        }
    }


    public Map<String, Model> parseAvailability(InputStream inputStream) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = mapper.readTree(inputStream);

        Set<String> storeKeys = stores.keySet();

        for (String storeKey : storeKeys) {

            JsonNode node = rootNode.path(storeKey);

            Iterator<Map.Entry<String,JsonNode>> iterator = node.getFields();

            while (iterator.hasNext()) {
                Map.Entry<String, JsonNode> entry = iterator.next();
                Model model = models.get(entry.getKey());
                model.setAvailable(entry.getValue().getBooleanValue());
                model.setStore(stores.get(storeKey));
            }
        }

        return models;
    }
}
