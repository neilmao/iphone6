package com.neilmao;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: neil
 * Date: 29/09/14
 * Time: 3:27 PM
 */
public class Model {

    private Store store;

    private String model;
    private boolean available;
    private String name;
    private boolean important;


    public Model() {
        this.available = false;
        this.important = false;
    }

    public Model(String model, String name) {
        this();
        this.model = model;
        this.name = name;
    }

    public Model(String model, String name, boolean important) {
        this(model, name);
        this.important = important;
    }

    public static Map<String, Model> prepareModels() {
        Map<String, Model> models = new LinkedHashMap();

        // add iphone 6 plus
        models.put("MGAA2X/A", new Model("MGAA2X/A", "Plus Gold 16GB", true));
        models.put("MGAK2X/A", new Model("MGAK2X/A", "Plus Gold 64GB", true));
        models.put("MGAF2X/A", new Model("MGAF2X/A", "Plus Gold 128GB", true));

        models.put("MGA92X/A", new Model("MGA92X/A", "Plus Silver 16GB", true));
        models.put("MGAJ2X/A", new Model("MGAJ2X/A", "Plus Silver 64GB", true));
        models.put("MGAE2X/A", new Model("MGAE2X/A", "Plus Silver 128GB", true));

        models.put("MGA82X/A", new Model("MGA82X/A", "Plus Grey 16GB", true));
        models.put("MGAH2X/A", new Model("MGAH2X/A", "Plus Grey 64GB", true));
        models.put("MGAC2X/A", new Model("MGAC2X/A", "Plus Grey 128GB", true));

        // add iphone 6
        models.put("MG492X/A", new Model("MG492X/A", "Gold 16GB", true));
        models.put("MG4J2X/A", new Model("MG4J2X/A", "Gold 64GB", true));
        models.put("MG4E2X/A", new Model("MG4E2X/A", "Gold 128GB", true));

        models.put("MG472X/A", new Model("MG472X/A", "Grey 16GB"));
        models.put("MG4F2X/A", new Model("MG4F2X/A", "Grey 64GB"));
        models.put("MG4A2X/A", new Model("MG4A2X/A", "Grey 128GB"));

        models.put("MG482X/A", new Model("MG482X/A", "Silver 16GB"));
        models.put("MG4H2X/A", new Model("MG4H2X/A", "Silver 64GB"));
        models.put("MG4C2X/A", new Model("MG4C2X/A", "Silver 128GB"));

        return models;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
