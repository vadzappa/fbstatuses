package com.gts.fb;

/**
 * <p><b> 2013 </b></p>
 *
 * @author Vadim
 */
public enum FacebookSearchType {
    POST("post"), USER("user"), EVENT("event"), GROUP("group"), CHECK_IN("checkin"), LOCATION("location");

    private String name;

    private FacebookSearchType(String name) {
        this.name = name;
    }

    public static FacebookSearchType findByName(String name) {
        for (FacebookSearchType searchType : values()) {
            if (searchType.name.equalsIgnoreCase(name)) {
                return searchType;
            }
        }
        return POST;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
