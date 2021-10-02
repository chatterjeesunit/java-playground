package com.util.jaxb.customer;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by sunitc on 6/2/17.
 */
@XmlRootElement
public class Job {
    private String company;
    private String designation;
    private String tenure;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    @Override
    public String toString() {
        return "Job{" +
                "company='" + company + '\'' +
                ", designation='" + designation + '\'' +
                ", tenure='" + tenure + '\'' +
                '}';
    }
}
