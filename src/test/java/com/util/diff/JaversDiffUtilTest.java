package com.util.diff;

import org.javers.core.diff.Diff;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

class JaversDiffUtilTest {


    @Test
    public void shouldCompareTwoPersonsAndGenerateDiff() {


        Employee p1 = Employee.builder()
                .personId(1001)
                .name("John Doe")
                .joiningDate(LocalDate.now().minusDays(7))
                .skills(List.of("People Management", "Technical Lead", "Devops Guru", "Backend Developer"))
                .addresses(List.of(
                        Address.builder().street("101 Janpath").city("New Delhi").addressType(AddressType.PERMANENT).zip("110098").build(),
                        Address.builder().street("2400 South Avenue").city("San Francisco").addressType(AddressType.LOCAL).zip("94065").build()
                    )
                ).build();

        Employee p2 = Employee.builder()
                .personId(1002)
                .name("John Doe")
                .joiningDate(LocalDate.now().minusDays(15))
                .skills(List.of("Technical Lead", "Backend Developer", "Mentor" ))
                .addresses(List.of(
                        Address.builder().street("2400 Bridge Parkway").city("San Francisco").addressType(AddressType.LOCAL).zip("94065").build(),
                        Address.builder().street("2511 Dulles Road").city("Washington DC").addressType(AddressType.LOCAL).zip("34221").build()
                    )
                ).build();

        JaversDiffUtil diffUtil = new JaversDiffUtil();

        Diff diff = diffUtil.generateDiff(p1, p2);

//        System.out.println(diff.changesSummary()); // changes - ValueChange:9 ListChange:1

        System.out.println(diff.getChanges().prettyPrint());
    }
}