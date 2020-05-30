package com.play.util.jaxb.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sunitc on 6/2/17.
 */
@XmlRootElement(name = "dataType")
public enum DataType {
    STRING,
    INTEGER,
    LONG,
    DATE
}
