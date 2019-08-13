package com.pojo.xml.jaxb.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sunitc on 6/2/17.
 */
@XmlRootElement(name = "sortableField")
public class SortableField {

    private String fieldName;
    private String tableAlias;

    public SortableField() {
    }

    @XmlAttribute(name = "fieldName")
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @XmlAttribute(name = "tableAlias")
    public String getTableAlias() {
        return tableAlias;
    }

    public void setTableAlias(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    @Override
    public String toString() {
        return "SortableField{" +
                "fieldName='" + fieldName + '\'' +
                ", tableAlias='" + tableAlias + '\'' +
                '}';
    }
}
