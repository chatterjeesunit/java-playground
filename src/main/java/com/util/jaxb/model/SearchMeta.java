package com.util.jaxb.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by sunitc on 6/2/17.
 */
@XmlRootElement
public class SearchMeta {

    private String resourceName;
    private String sqlQuery;
    private List<SearchableField> searchableFields;
    private List<SortableField> sortableFields;

    public SearchMeta() {
    }

    @XmlAttribute(name = "resourceName")
    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }


    @XmlElement(name = "sql-query")
    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    @XmlElement(name = "searchableField")
    public List<SearchableField> getSearchableFields() {
        return searchableFields;
    }

    public void setSearchableFields(List<SearchableField> searchableFields) {
        this.searchableFields = searchableFields;
    }

    @XmlElement(name = "sortableField")
    public List<SortableField> getSortableFields() {
        return sortableFields;
    }

    public void setSortableFields(List<SortableField> sortableFields) {
        this.sortableFields = sortableFields;
    }


    @Override
    public String toString() {
        return "SearchMeta{" +
                "resourceName='" + resourceName + '\'' +
                ", sqlQuery='" + sqlQuery + '\'' +
                ", searchableFields=" + searchableFields +
                ", sortableFields=" + sortableFields +
                '}';
    }
}
