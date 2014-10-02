package com.neilmao.iphone6;

import java.util.LinkedList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: neil
 * Date: 29/09/14
 * Time: 4:31 PM
 */
public class Store implements Comparable<Store> {

    private String name;
    private String code;
    private boolean enabled;
    private boolean hasStock;
    private boolean hasImportantStock;
    private int priority;

    private List<Model> modelList;

    private static String UPDATED_TIME;

    public Store(String name, String code, boolean enabled) {
        this.name = name;
        this.code = code;
        this.enabled = enabled;
        this.hasStock = false;
        this.hasImportantStock = false;
        this.modelList = new LinkedList<Model>();
        this.priority = 255;
    }

    public void checkStock() {
        for (Model model : modelList) {
            if (model.isAvailable()) {
                this.hasStock = true;
                if (model.isImportant()) {
                    this.hasImportantStock = true;
                    break;
                }
            }
        }
    }

    @Override
    public int compareTo(Store o) {
        return this.priority - o.priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Model> getModelList() {
        return modelList;
    }

    public void setModelList(List<Model> modelList) {
        this.modelList = modelList;
    }

    public boolean isHasImportantStock() {
        return hasImportantStock;
    }

    public void setHasImportantStock(boolean hasImportantStock) {
        this.hasImportantStock = hasImportantStock;
    }

    public boolean isHasStock() {
        return hasStock;
    }

    public void setHasStock(boolean hasStock) {
        this.hasStock = hasStock;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public static String getUPDATED_TIME() {
        return UPDATED_TIME;
    }

    public static void setUPDATED_TIME(String UPDATED_TIME) {
        Store.UPDATED_TIME = UPDATED_TIME;
    }
}
