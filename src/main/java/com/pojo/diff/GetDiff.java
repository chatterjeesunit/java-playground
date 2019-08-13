package com.pojo.diff;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.builder.DiffBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.List;

/*
Requires Libraries

compile group: 'org.projectlombok', name: 'lombok', version: '1.18.8'
    compile group: 'org.javers', name: 'javers-core', version: '5.6.3'

    compile group: 'org.apache.commons', name: 'commons-lang3', version: '3.6'
    
 */
public class GetDiff {

    public static void main(String[] args) throws IllegalAccessException {

        DummyObject d1 = new DummyObject("Apple earpod", null, 598.45);
        DummyObject d2 = new DummyObject("Apple earpod v2.0", LocalDate.parse("2019-12-15"), 598.45);


        System.out.println("\n\n******** Javers Diff ***********");
        getDiffUsingJavers(d1, d2);



        System.out.println("\n\n******** Apache Commons 3 Generic Diff ***********");
        getDiffGenericUsingApacheCommons(d1, d2);

        System.out.println("\n\n******** Apache Commons 3 Diff with Custom Field Names ***********");
        getDiffWithCustomFieldNamesUsingApacheCommons(d1, d2);


    }

    private static void getDiffWithCustomFieldNamesUsingApacheCommons(DummyObject d1, DummyObject d2) {
        List<org.apache.commons.lang3.builder.Diff<?>> diffList =  new DiffBuilder(d1, d2, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("Product Name", d1.getProductName(), d2.getProductName())
                .append("Launch Date", d1.getLaunchDate(), d2.getLaunchDate())
                .append("Price", d1.getPrice(), d2.getPrice())
                .build()
                .getDiffs();


        diffList.forEach(diff -> {
            System.out.println("\t" + diff.getFieldName() + " : " + diff.getLeft() + " -> " + diff.getRight());
        });
    }

    private static void getDiffGenericUsingApacheCommons(DummyObject d1, DummyObject d2) throws IllegalAccessException {
        Field[] fields = d1.getClass().getDeclaredFields();
        DiffBuilder diffBuilder =  new DiffBuilder(d1, d2, ToStringStyle.SHORT_PREFIX_STYLE);
        for(int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            ((DiffBuilder) diffBuilder).append(field.getName(), field.get(d1), field.get(d2));
        }
        ((DiffBuilder) diffBuilder).build().getDiffs().forEach( diff -> {
            System.out.println("\t" + diff.getFieldName() + " : " + diff.getLeft() + " -> " + diff.getRight());
        });
    }

    private static void getDiffUsingJavers(DummyObject d1, DummyObject d2) {
        Javers javers = JaversBuilder.javers().build();
        Diff diff1 = javers.compare(d1, d2);

        System.out.println("\n\n" + diff1);
    }


}

@Data
@AllArgsConstructor
class DummyObject {

    String productName;
    LocalDate launchDate;
    double price;
}
