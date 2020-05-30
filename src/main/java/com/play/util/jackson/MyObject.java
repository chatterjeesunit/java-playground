package com.play.util.jackson;

import java.util.Date;

/**
 * Created by sunitc on 6/8/17.
 */
public class MyObject {
    private long id;
    private String firstName;
    private String lastName;
    private Date joiningDate;
    private boolean lookingOut;

    public MyObject() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public boolean getLookingOut() {
        return lookingOut;
    }

    public void setLookingOut(boolean lookingOut) {
        this.lookingOut = lookingOut;
    }

    @Override
    public String toString() {
        return "MyObject{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", joiningDate=" + joiningDate +
                ", lookingOut=" + lookingOut +
                '}';
    }
}
