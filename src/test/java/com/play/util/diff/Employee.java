package com.play.util.diff;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by "Sunit Chatterjee" created on 31/05/20
 */
@AllArgsConstructor
@Data
@Builder
public class Employee {

    private int personId;
    private String name;
    private LocalDate joiningDate;
    private List<Address> addresses;
    private List<String> skills;
}

@Data
@Builder
class Address {
    private String street;
    private String city;
    private String zip;
    private AddressType addressType;

}

enum AddressType {
    PERMANENT, LOCAL, CORRESPONDENCE
}
