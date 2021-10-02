package com.util.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by sunitc on 6/8/17.
 */
@XmlRootElement(name = "result-object")
public class ResultObject {


    private String className;

    private List<ResultObjectField> fields;


    public ResultObject() {
    }


    @XmlElement
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @XmlElement(name = "field")
    public List<ResultObjectField> getFields() {
        return fields;
    }

    public void setFields(List<ResultObjectField> fields) {
        this.fields = fields;
    }


    @Override
    public String toString() {
        return "ResultObject{" +
                "className='" + className + '\'' +
                ", fields=" + fields +
                '}';
    }
}
