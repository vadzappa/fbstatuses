package com.gts.fb;

/**
 * <p><b> 2013 </b></p>
 *
 * @author Vadim
 */
public class SearchParameter {
    private String name;
    private String value;

    public SearchParameter(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
