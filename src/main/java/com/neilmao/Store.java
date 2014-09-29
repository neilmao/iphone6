package com.neilmao;

import org.codehaus.jackson.JsonNode;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: neil
 * Date: 29/09/14
 * Time: 4:31 PM
 */
public class Store {

    private String name;
    private String code;
    private boolean enabled;

    private static String UPDATED_TIME;

    public Store(String name, String code, boolean enabled) {
        this.name = name;
        this.code = code;
        this.enabled = enabled;
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

    public static String getUPDATED_TIME() {
        return UPDATED_TIME;
    }

    public static void setUPDATED_TIME(String UPDATED_TIME) {
        Store.UPDATED_TIME = UPDATED_TIME;
    }
}
