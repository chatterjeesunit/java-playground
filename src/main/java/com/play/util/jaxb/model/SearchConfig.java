package com.play.util.jaxb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by sunitc on 6/2/17.
 */
@XmlRootElement
public class SearchConfig {

    private List<SearchMeta> searchMeta;

    public SearchConfig() {
    }

    @XmlElement
    public List<SearchMeta> getSearchMeta() {
        return searchMeta;
    }

    public void setSearchMeta(List<SearchMeta> searchMeta) {
        this.searchMeta = searchMeta;
    }

    @Override
    public String toString() {
        return "SearchConfig{" +
                "searchMeta=" + searchMeta +
                '}';
    }
}
