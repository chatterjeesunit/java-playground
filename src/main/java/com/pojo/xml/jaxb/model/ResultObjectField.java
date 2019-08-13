package com.pojo.xml.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sunitc on 6/8/17.
 */
@XmlRootElement(name = "field")
public class ResultObjectField {

    private int index;
    private String fieldName;

    public ResultObjectField() {
    }


    @XmlElement
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @XmlElement
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public String toString() {
        return "ResultObjectField{" +
                "index=" + index +
                ", fieldName='" + fieldName + '\'' +
                '}';
    }
}
