package com.neilmao.iphone6;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: neil
 * Date: 29/09/14
 * Time: 3:27 PM
 */
public class Model implements Comparable<Model> {

    private String code;
    private boolean available;
    private String name;
    private boolean important;
    private int priority;


    public Model() {
        this.available = false;
        this.important = false;
    }

    public Model(String code, String name) {
        this();
        this.code = code;
        this.name = name;
    }

    public Model(String code, String name, boolean important, int priority) {
        this(code, name);
        this.important = important;
        this.priority = priority;
    }

    public static Map<String, Model> prepareModels() {
        Map<String, Model> models = new LinkedHashMap();

        // add iphone 6 plus
        models.put("MGAA2X/A", new Model("MGAA2X/A", "Plus Gold 16GB", false, 7));
        models.put("MGAK2X/A", new Model("MGAK2X/A", "Plus Gold 64GB", true, 1));
        models.put("MGAF2X/A", new Model("MGAF2X/A", "Plus Gold 128GB", true, 4));

        models.put("MGA92X/A", new Model("MGA92X/A", "Plus Silver 16GB", false, 8));
        models.put("MGAJ2X/A", new Model("MGAJ2X/A", "Plus Silver 64GB", true, 2));
        models.put("MGAE2X/A", new Model("MGAE2X/A", "Plus Silver 128GB", true, 5));

        models.put("MGA82X/A", new Model("MGA82X/A", "Plus Grey 16GB", false, 9));
        models.put("MGAH2X/A", new Model("MGAH2X/A", "Plus Grey 64GB", true, 3));
        models.put("MGAC2X/A", new Model("MGAC2X/A", "Plus Grey 128GB", true, 6));

        // add iphone 6
        models.put("MG492X/A", new Model("MG492X/A", "Gold 16GB", false, 12));
        models.put("MG4J2X/A", new Model("MG4J2X/A", "Gold 64GB", false, 10));
        models.put("MG4E2X/A", new Model("MG4E2X/A", "Gold 128GB", false, 11));

        models.put("MG472X/A", new Model("MG472X/A", "Grey 16GB", false, 18));
        models.put("MG4F2X/A", new Model("MG4F2X/A", "Grey 64GB", false, 14));
        models.put("MG4A2X/A", new Model("MG4A2X/A", "Grey 128GB", false, 16));

        models.put("MG482X/A", new Model("MG482X/A", "Silver 16GB", false, 17));
        models.put("MG4H2X/A", new Model("MG4H2X/A", "Silver 64GB", false, 13));
        models.put("MG4C2X/A", new Model("MG4C2X/A", "Silver 128GB", false, 15));

        return models;
    }

    @Override
    public int compareTo(Model o) {
        return this.priority - o.priority;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
