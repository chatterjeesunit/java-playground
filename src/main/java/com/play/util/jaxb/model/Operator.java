package com.play.util.jaxb.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sunitc on 6/2/17.
 */
@XmlRootElement(name = "operator")
public enum Operator {
    EQ,
    LIKE,
    GT,
    GTE,
    LT,
    LTE
}
