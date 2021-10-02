package com.corejava.enumtest.pkg1;

/**
 * Created by sunitc on 6/26/17.
 */
public enum StatusEnum {
    ACTIVE(0),
    INACTIVE(1),
    RETIRED(2),
    ON_LEAVE(3);

    private int statusCode;

    StatusEnum(int statusCode) {
        this.statusCode = statusCode;
    }

    public static StatusEnum getEnum(Integer code) {
        switch (code) {
            case 0 : return ACTIVE;
            case 1 : return INACTIVE;
            case 2 : return RETIRED;
            case 3 : return ON_LEAVE;
            default: throw new IllegalArgumentException("Unsupported enum status code");
        }
    }
}
