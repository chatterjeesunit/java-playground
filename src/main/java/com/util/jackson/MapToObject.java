package com.util.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sunitc on 6/8/17.
 */
public class MapToObject {



    public static void main(String[] args) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", Long.valueOf(323232l));
        map.put("firstName", "Sunit" );
        map.put("lastName", "Chatterjee" );
        map.put("joiningDate", new Date() );
        map.put("lookingOut", true );

        System.out.println(map);

        ObjectMapper mapper = new ObjectMapper();
        MyObject obj = mapper.convertValue(map, MyObject.class);

        System.out.println(obj);
    }

    public static class MyObject {
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
}

