package com.pojo.xml.jaxb.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by sunitc on 6/2/17.
 */
@XmlRootElement(name = "searchableField")
public class SearchableField {

    private String fieldName;
    private String tableAlias;
    private List<Operator> supportedOperators;
    private DataType dataType;

    public SearchableField() {
        super();
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

    @XmlElement(name = "operator")
    public List<Operator> getSupportedOperators() {
        return supportedOperators;
    }

    public void setSupportedOperators(List<Operator> supportedOperators) {
        this.supportedOperators = supportedOperators;
    }

    @XmlAttribute(name = "dataType")
    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }


    @Override
    public String toString() {
        return "SearchableField{" +
                "fieldName='" + fieldName + '\'' +
                ", tableAlias='" + tableAlias + '\'' +
                ", supportedOperators=" + supportedOperators +
                ", dataType=" + dataType +
                '}';
    }
}

