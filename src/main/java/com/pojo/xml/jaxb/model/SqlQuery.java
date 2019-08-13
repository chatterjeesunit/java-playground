package com.pojo.xml.jaxb.model;

//import com.sun.xml.internal.txw2.annotation.XmlCDATA;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sunitc on 6/8/17.
 */
@XmlRootElement(name = "sql-query")
public class SqlQuery {

    private String query;

    public SqlQuery() {
    }

//    @XmlCDATA
    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "SqlQuery{" +
                "query='" + query + '\'' +
                '}';
    }
}
