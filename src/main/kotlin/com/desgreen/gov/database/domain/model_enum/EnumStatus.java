package com.desgreen.gov.database.domain.model_enum;

/**
 * The Status Enum class
 *
 * @author ibrahim KARAYEL
 * @version 1.0
 * Date 4/27/2018.
 */
public enum EnumStatus {
    ACTIVE("ACTIVE"), PASSIVE("PASSIVE");
    private String value;

    EnumStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
